package io.inventiv.critic.model;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

public class User {

    @SerializedName("id")
	private Long id;
	@SerializedName("email")
	private String email;
	@SerializedName("encrypted_password")
	private String encryptedPassword;
	@SerializedName("reset_password_token")
	private String resetPasswordToken;
	@SerializedName("reset_password_sent_at")
	private Date resetPasswordSentAt;
	@SerializedName("remember_created_at")
	private Date rememberCreatedAt;
	@SerializedName("sign_in_count")
	private Long signInCount;
	@SerializedName("current_sign_in_at")
	private Date currentSignInAt;
	@SerializedName("last_sign_in_at")
	private Date lastSignInAt;
	@SerializedName("current_sign_in_ip")
	private String currentSignInIp;
	@SerializedName("last_sign_in_ip")
	private String lastSignInIp;
	@SerializedName("confirmation_token")
	private String confirmationToken;
	@SerializedName("confirmed_at")
	private Date confirmedAt;
	@SerializedName("confirmation_sent_at")
	private Date confirmationSentAt;
	@SerializedName("unconfirmed_email")
	private String unconfirmedEmail;
	@SerializedName("failed_attempts")
	private Long failedAttempts;
	@SerializedName("unlock_token")
	private String unlockToken;
	@SerializedName("locked_at")
	private Date lockedAt;
	@SerializedName("created_at")
	private Date createdAt;
	@SerializedName("updated_at")
	private Date updatedAt;
	@SerializedName("is_administrator")
	private String isAdministrator;
	@SerializedName("invitation_token")
	private String invitationToken;
	@SerializedName("invitation_created_at")
	private Date invitationCreatedAt;
	@SerializedName("invitation_sent_at")
	private Date invitationSentAt;
	@SerializedName("invitation_accepted_at")
	private Date invitationAcceptedAt;
	@SerializedName("invitation_limit")
	private Long invitationLimit;
	@SerializedName("invited_by_type")
	private String invitedByType;
	@SerializedName("invited_by_id")
	private Long invitedById;
	@SerializedName("invitations_count")
	private Long invitationsCount;
	@SerializedName("jti")
	private String jti;
	private String password;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public Date getResetPasswordSentAt() {
		return resetPasswordSentAt;
	}

	public void setResetPasswordSentAt(Date resetPasswordSentAt) {
		this.resetPasswordSentAt = resetPasswordSentAt;
	}

	public Date getRememberCreatedAt() {
		return rememberCreatedAt;
	}

	public void setRememberCreatedAt(Date rememberCreatedAt) {
		this.rememberCreatedAt = rememberCreatedAt;
	}

	public Long getSignInCount() {
		return signInCount;
	}

	public void setSignInCount(Long signInCount) {
		this.signInCount = signInCount;
	}

	public Date getCurrentSignInAt() {
		return currentSignInAt;
	}

	public void setCurrentSignInAt(Date currentSignInAt) {
		this.currentSignInAt = currentSignInAt;
	}

	public Date getLastSignInAt() {
		return lastSignInAt;
	}

	public void setLastSignInAt(Date lastSignInAt) {
		this.lastSignInAt = lastSignInAt;
	}

	public String getCurrentSignInIp() {
		return currentSignInIp;
	}

	public void setCurrentSignInIp(String currentSignInIp) {
		this.currentSignInIp = currentSignInIp;
	}

	public String getLastSignInIp() {
		return lastSignInIp;
	}

	public void setLastSignInIp(String lastSignInIp) {
		this.lastSignInIp = lastSignInIp;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getConfirmedAt() {
		return confirmedAt;
	}

	public void setConfirmedAt(Date confirmedAt) {
		this.confirmedAt = confirmedAt;
	}

	public Date getConfirmationSentAt() {
		return confirmationSentAt;
	}

	public void setConfirmationSentAt(Date confirmationSentAt) {
		this.confirmationSentAt = confirmationSentAt;
	}

	public String getUnconfirmedEmail() {
		return unconfirmedEmail;
	}

	public void setUnconfirmedEmail(String unconfirmedEmail) {
		this.unconfirmedEmail = unconfirmedEmail;
	}

	public Long getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(Long failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	public String getUnlockToken() {
		return unlockToken;
	}

	public void setUnlockToken(String unlockToken) {
		this.unlockToken = unlockToken;
	}

	public Date getLockedAt() {
		return lockedAt;
	}

	public void setLockedAt(Date lockedAt) {
		this.lockedAt = lockedAt;
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

	public String getIsAdministrator() {
		return isAdministrator;
	}

	public void setIsAdministrator(String isAdministrator) {
		this.isAdministrator = isAdministrator;
	}

	public String getInvitationToken() {
		return invitationToken;
	}

	public void setInvitationToken(String invitationToken) {
		this.invitationToken = invitationToken;
	}

	public Date getInvitationCreatedAt() {
		return invitationCreatedAt;
	}

	public void setInvitationCreatedAt(Date invitationCreatedAt) {
		this.invitationCreatedAt = invitationCreatedAt;
	}

	public Date getInvitationSentAt() {
		return invitationSentAt;
	}

	public void setInvitationSentAt(Date invitationSentAt) {
		this.invitationSentAt = invitationSentAt;
	}

	public Date getInvitationAcceptedAt() {
		return invitationAcceptedAt;
	}

	public void setInvitationAcceptedAt(Date invitationAcceptedAt) {
		this.invitationAcceptedAt = invitationAcceptedAt;
	}

	public Long getInvitationLimit() {
		return invitationLimit;
	}

	public void setInvitationLimit(Long invitationLimit) {
		this.invitationLimit = invitationLimit;
	}

	public String getInvitedByType() {
		return invitedByType;
	}

	public void setInvitedByType(String invitedByType) {
		this.invitedByType = invitedByType;
	}

	public Long getInvitedById() {
		return invitedById;
	}

	public void setInvitedById(Long invitedById) {
		this.invitedById = invitedById;
	}

	public Long getInvitationsCount() {
		return invitationsCount;
	}

	public void setInvitationsCount(Long invitationsCount) {
		this.invitationsCount = invitationsCount;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    /**
     * Wrapper for User objects to use prior to serialization of JSON payloads in some Retrofit web requests.
     */
	public static class Wrapper {

		private User user;

        public Wrapper(User user) {
            setUser(user);
        }

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
	}
}
