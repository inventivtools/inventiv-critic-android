package io.inventiv.critic.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Attachment {

    @SerializedName("id")
	private Long id;
	@SerializedName("report_id")
	private Long reportId;
	@SerializedName("created_at")
	private Date createdAt;
	@SerializedName("updated_at")
	private Date updatedAt;
	@SerializedName("file_file_name")
	private String fileFileName;
	@SerializedName("file_content_type")
	private String fileContentType;
	@SerializedName("file_file_size")
	private Long fileFileSize;
	@SerializedName("file_updated_at")
	private Date fileUpdatedAt;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
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

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public Long getFileFileSize() {
		return fileFileSize;
	}

	public void setFileFileSize(Long fileFileSize) {
		this.fileFileSize = fileFileSize;
	}

	public Date getFileUpdatedAt() {
		return fileUpdatedAt;
	}

	public void setFileUpdatedAt(Date fileUpdatedAt) {
		this.fileUpdatedAt = fileUpdatedAt;
	}

    /**
     * Wrapper for Attachment objects to use prior to serialization of JSON payloads in some Retrofit web requests.
     */
	public static class Wrapper {

		private Attachment attachment;

        public Wrapper(Attachment attachment) {
            setAttachment(attachment);
        }

		public Attachment getAttachment() {
			return attachment;
		}

		public void setAttachment(Attachment attachment) {
			this.attachment = attachment;
		}
	}
}
