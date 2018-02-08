package io.inventiv.critic.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Organization {

    @SerializedName("id")
	private Long id;
	@SerializedName("name")
	private String name;
	@SerializedName("created_at")
	private Date createdAt;
	@SerializedName("updated_at")
	private Date updatedAt;
	@SerializedName("user_id")
	private Long userId;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

    /**
     * Wrapper for Organization objects to use prior to serialization of JSON payloads in some Retrofit web requests.
     */
	public static class Wrapper {

		private Organization organization;

        public Wrapper(Organization organization) {
            setOrganization(organization);
        }

		public Organization getOrganization() {
			return organization;
		}

		public void setOrganization(Organization organization) {
			this.organization = organization;
		}
	}
}
