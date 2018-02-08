package io.inventiv.critic.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class Membership {

    @SerializedName("id")
	private Long id;
	@SerializedName("member_type")
	private String memberType;
	@SerializedName("member_id")
	private Long memberId;
	@SerializedName("role")
	private String role;
	@SerializedName("created_at")
	private Date createdAt;
	@SerializedName("updated_at")
	private Date updatedAt;
	@SerializedName("product_id")
	private Long productId;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

    /**
     * Wrapper for Membership objects to use prior to serialization of JSON payloads in some Retrofit web requests.
     */
	public static class Wrapper {

		private Membership membership;

        public Wrapper(Membership membership) {
            setMembership(membership);
        }

		public Membership getMembership() {
			return membership;
		}

		public void setMembership(Membership membership) {
			this.membership = membership;
		}
	}
}
