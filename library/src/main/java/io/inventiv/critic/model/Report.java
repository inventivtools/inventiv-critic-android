package io.inventiv.critic.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Report {

    @SerializedName("id")
	private Long id;
	@SerializedName("description")
	private String description;
	@SerializedName("created_at")
	private Date createdAt;
	@SerializedName("updated_at")
	private Date updatedAt;
	@SerializedName("product_id")
	private Long productId;
	@SerializedName("metadata")
	private String metadata;
	@SerializedName("attachments")
	private List<Attachment> attachments;

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

	public List<Attachment> getAttachments() { return attachments; }

	public void setAttachments(List<Attachment> attachments) { this.attachments = attachments; }

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
