package io.inventiv.critic.client;

import android.content.Context;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.inventiv.critic.Critic;
import io.inventiv.critic.model.BugReport;
import io.inventiv.critic.util.Logs;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class BugReportCreator {

    private String description;
    private JsonObject metadata;
    private List<File> attachments;

    public BugReport create(Context context) throws ReportCreationException {

        if(description() == null || description().length() == 0) {
            throw new AssertionError("You need to provide a description to continue.");
        }

        JsonObject metadata = Critic.getProductMetadata();
        if(metadata == null) {
            metadata = new JsonObject();
        }

        List<MultipartBody.Part> parts = new ArrayList();
        parts.add( MultipartBody.Part.createFormData("api_token", Critic.getProductAccessToken() ) );
        parts.add( MultipartBody.Part.createFormData("app_install[id]", Long.toString( Critic.getAppInstallId() ) ) );
        parts.add( MultipartBody.Part.createFormData("bug_report[description]", description() ) );
        parts.add( MultipartBody.Part.createFormData("bug_report[metadata]", metadata.toString() ) );
        parts.add( MultipartBody.Part.createFormData("device_status",Critic.getDeviceStatusJson().toString() ) );

        try {
            File logcat = Logs.readLogcat(context);
            if(logcat != null) {
                if(attachments() == null) {
                    attachments(new ArrayList<File>());
                }
                attachments().add(logcat);
            }
        } catch(IOException e) {
            Log.e(BugReportCreator.class.getSimpleName(), "Could not read from logcat!", e);
        }

        try {
            if (attachments() != null && attachments().size() > 0) {
                for (File file : attachments()) {
                    String filename = file.getName();

                    // determine Content-Type to send for file.
                    String contentType = null;
                    final String extension = filename.substring(filename.lastIndexOf("."));
                    if (extension != null) {
                        contentType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.toLowerCase());
                    }
                    if (contentType == null) {
                        if("log".equalsIgnoreCase(extension)) {
                            contentType = "text/plain";
                        }
                        else {
                            contentType = "*/*";
                        }
                    }

                    parts.add(MultipartBody.Part.createFormData("bug_report[attachments][]", file.getName(), RequestBody.create(MediaType.parse(contentType), file)));
                }
            }
        }
        catch(Exception e){
            throw new ReportCreationException("Encountered a problem attaching files: " + e.getMessage(), e);
        }

        BugReport bugReport = null;
        try {
            Response<BugReport> reportResponse = Client.bugReportService().create(parts).execute();
            if(reportResponse.code() != 201) {
                throw new ReportCreationException("Invalid response code: " + reportResponse.code());
            }
            bugReport = reportResponse.body();
        } catch (IOException e) {
            throw new ReportCreationException("Invalid response from server: " + e.getMessage(), e);

        }

        if(bugReport == null) {
            throw new ReportCreationException("No report returned from server.");
        }

        return bugReport;
    }

    String description() {
        return description;
    }

    public BugReportCreator description(String description) {
        this.description = description;
        return this;
    }

    List<File> attachments() {
        return attachments;
    }

    public BugReportCreator attachments(List<File> attachments) {
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
