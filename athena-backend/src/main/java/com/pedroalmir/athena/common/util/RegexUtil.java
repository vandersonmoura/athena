package com.pedroalmir.athena.common.util;

/**
 * @author EasyTeam, created by Pedro Oliveira
 *
 */
public class RegexUtil {

	/**
	 * REGEX_EMAIL
	 */
	private static final String REGEX_EMAIL = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	/**
	 * REGEX_PHONE
	 */
	private static final String REGEX_PHONE = "\\([0-9]{2}\\)[ ]([0-9]{4}||[0-9]{5})\\-[0-9]{4}";

	/**
	 * Validate mail
	 * @param email
	 * @return true for correct mails
	 */
	public static boolean validEmail(String email) {
		if (email != null && !email.trim().isEmpty()) {
			return email.trim().matches(REGEX_EMAIL);
		}
		return false;
	}

	/**
	 * Validate phone
	 * @param phone
	 * @return true for correct mails
	 */
	public static boolean validPhone(String phone) {
		if (phone != null && !phone.trim().isEmpty()) {
			return phone.trim().matches(REGEX_PHONE);
		}
		return false;
	}
}
