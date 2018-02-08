package io.inventiv.critic.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewReportRequest {

    @SerializedName("product_access_token")
    private String productAccessToken;
    @SerializedName("description")
    private String description;
    @SerializedName("metadata")
    private String metadata;
    @SerializedName("attachments")
    private List<Attachment> attachments;

    public String getProductAccessToken() {
        return productAccessToken;
    }

    public void setProductAccessToken(String productAccessToken) {
        this.productAccessToken = productAccessToken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public List<Attachment> getAttachments() { return attachments; }

    public void setAttachments(List<Attachment> attachments) { this.attachments = attachments; }

    /**
     * Wrapper for NewReportRequest objects to use prior to serialization of JSON payloads in some Retrofit web requests.
     */
    public static class Wrapper {

        @SerializedName("report")
        private NewReportRequest report;

        public Wrapper(NewReportRequest report) {
            setReport(report);
        }

        public NewReportRequest getReport() {
            return report;
        }

        public void setReport(NewReportRequest report) {
            this.report = report;
        }
    }
}
