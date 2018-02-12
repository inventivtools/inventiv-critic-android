package io.inventiv.critic.client;

import android.content.Context;
import android.content.Intent;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.inventiv.critic.FeedbackReportActivity;
import io.inventiv.critic.model.Report;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class ReportCreator {

    private String productAccessToken;
    private String description;
    private JsonObject metadata;
    private List<File> attachments;

    public static void showDefaultActivity(Context context, String productAccessToken) {
        Intent intent = new Intent(context, FeedbackReportActivity.class);
        intent.putExtra("productAccessToken", productAccessToken);
        context.startActivity(intent);
    }

    public Report create() throws ReportCreationException {

        if(productAccessToken() == null || productAccessToken().length() == 0 || productAccessToken().equals("YOUR_PRODUCT_ACCESS_TOKEN")) {
            throw new AssertionError("You need to provide a valid Product Access Token to create a Report. See the Critic Getting Started Guide at https://inventiv.io/critic/critic-integration-getting-started/.");
        }

        if(description() == null || description().length() == 0) {
            throw new AssertionError("You need to provide a description to continue.");
        }

        if(metadata() == null) {
            metadata(new JsonObject());
        }

        List<MultipartBody.Part> parts = new ArrayList();
        parts.add( MultipartBody.Part.createFormData("report[product_access_token]", productAccessToken() ) );
        parts.add( MultipartBody.Part.createFormData("report[description]", description() ) );
        parts.add( MultipartBody.Part.createFormData("report[metadata]", metadata().toString() ) );

        if(attachments() != null && attachments().size() > 0 ) {
            for (File file : attachments()) {
                String filename = file.getName();
                String contentType = "text/plain";
                if (filename.endsWith("bmp") || filename.endsWith("jpeg") || filename.endsWith("jpg") || filename.endsWith("png")) {
                    contentType = "image/*";
                }
                parts.add(MultipartBody.Part.createFormData("report[attachments][]", file.getName(), RequestBody.create(MediaType.parse(contentType), file)));
            }
        }

        Report report = null;
        try {
            Response<Report> reportResponse = Client.reportService().create(parts).execute();
            if(reportResponse.code() != 201) {
                throw new ReportCreationException("Invalid response code: " + reportResponse.code());
            }
            report = reportResponse.body();
        } catch (IOException e) {
            throw new ReportCreationException("Invalid response from server: " + e.getMessage(), e);

        }

        if(report == null) {
            throw new ReportCreationException("No report returned from server.");
        }

        return report;
    }


    String productAccessToken() {
        return productAccessToken;
    }

    public ReportCreator productAccessToken(String productAccessToken) {
        this.productAccessToken = productAccessToken;
        return this;
    }

    String description() {
        return description;
    }

    public ReportCreator description(String description) {
        this.description = description;
        return this;
    }

    JsonObject metadata() {
        return metadata;
    }

    public ReportCreator metadata(JsonObject metadata) {
        this.metadata = metadata;
        return this;
    }

    List<File> attachments() {
        return attachments;
    }

    public ReportCreator attachments(List<File> attachments) {
        this.attachments = attachments;
        return this;
    }

    public static class ReportCreationException extends RuntimeException {

        public ReportCreationException(String message) {
            super(message);
        }

        public ReportCreationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
