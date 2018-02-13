package io.inventiv.critic.client;

import android.content.Context;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.inventiv.critic.Critic;
import io.inventiv.critic.model.Report;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class ReportCreator {

    private String description;
    private JsonObject metadata;
    private List<File> attachments;

    public Report create(Context context) throws ReportCreationException {

        if(description() == null || description().length() == 0) {
            throw new AssertionError("You need to provide a description to continue.");
        }
        if(metadata() == null) {
            metadata(new JsonObject());
        }
        Critic.addStandardMetadata(metadata());

        List<MultipartBody.Part> parts = new ArrayList();
        parts.add( MultipartBody.Part.createFormData("report[product_access_token]", Critic.getProductAccessToken() ) );
        parts.add( MultipartBody.Part.createFormData("report[description]", description() ) );
        parts.add( MultipartBody.Part.createFormData("report[metadata]", metadata().toString() ) );

        try {
            if (attachments() != null && attachments().size() > 0) {
                for (File file : attachments()) {
                    String filename = file.getName();
                    String contentType = "text/plain";
                    if (filename.endsWith("bmp") || filename.endsWith("jpeg") || filename.endsWith("jpg") || filename.endsWith("png")) {
                        contentType = "image/*";
                    }
                    parts.add(MultipartBody.Part.createFormData("report[attachments][]", file.getName(), RequestBody.create(MediaType.parse(contentType), file)));
                }
            }
        }
        catch(Exception e){
            throw new ReportCreationException("Encountered a problem attaching files: " + e.getMessage(), e);
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
