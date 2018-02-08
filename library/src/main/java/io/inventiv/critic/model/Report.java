package io.inventiv.critic.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Report {

    @SerializedName("id")
	private Long id;
	@SerializedName("description")
	private String description;
	@SerializedName("created_at")
	private Date createdAt;
	@SerializedName("updated_at")
	private Date updatedAt;
	@SerializedName("attachment_file_name")
	private String attachmentFileName;
	@SerializedName("attachment_content_type")
	private String attachmentContentType;
	@SerializedName("attachment_file_size")
	private Long attachmentFileSize;
	@SerializedName("attachment_updated_at")
	private Date attachmentUpdatedAt;
	@SerializedName("product_id")
	private Long productId;
	@SerializedName("metadata")
	private String metadata;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public String getAttachmentContentType() {
		return attachmentContentType;
	}

	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	public Long getAttachmentFileSize() {
		return attachmentFileSize;
	}

	public void setAttachmentFileSize(Long attachmentFileSize) {
		this.attachmentFileSize = attachmentFileSize;
	}

	public Date getAttachmentUpdatedAt() {
		return attachmentUpdatedAt;
	}

	public void setAttachmentUpdatedAt(Date attachmentUpdatedAt) {
		this.attachmentUpdatedAt = attachmentUpdatedAt;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getMetadata() {
		return metadata;
	}

	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

    /**
     * Wrapper for Report objects to use prior to serialization of JSON payloads in some Retrofit web requests.
     */
	public static class Wrapper {

		private Report report;

        public Wrapper(Report report) {
            setReport(report);
        }

		public Report getReport() {
			return report;
		}

		public void setReport(Report report) {
			this.report = report;
		}
	}
}
