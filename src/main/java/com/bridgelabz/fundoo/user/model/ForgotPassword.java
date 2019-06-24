package com.bridgelabz.fundoo.user.model;

public class ForgotPassword {

	private String confirmPassword;
	private String newPassword;
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	@Override
	public String toString() {
		return "ForgotPassword [confirmPassword=" + confirmPassword + ", newPassword=" + newPassword + "]";
	}

	
}
