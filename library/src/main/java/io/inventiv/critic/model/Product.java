package io.inventiv.critic.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Product {

    @SerializedName("id")
	private Long id;
	@SerializedName("name")
	private String name;
	@SerializedName("organization_id")
	private Long organizationId;
	@SerializedName("created_at")
	private Date createdAt;
	@SerializedName("updated_at")
	private Date updatedAt;
	@SerializedName("access_token")
	private String accessToken;

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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
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

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

    /**
     * Wrapper for Product objects to use prior to serialization of JSON payloads in some Retrofit web requests.
     */
	public static class Wrapper {

		private Product product;

        public Wrapper(Product product) {
            setProduct(product);
        }

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}
	}
}
