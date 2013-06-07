package com.pedroalmir.athena.common.validation;

import java.util.HashMap;
import java.util.Map;

import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.ioc.Component;

/**
 * Object that manages the error messages returned in the request
 * 
 * @author Pedro Oliveira
 */
@Component
public class MessageResult {

	/**
	 * Prefix used for the validation keys
	 */
	private static final String PREFIX_VALIDATION = "validation.";

	/**
	 * Messages stored
	 */
	private Map<String, String> messages;

	/**
	 * Object responsible for fetching the messages contained in the file
	 * message.properties
	 */
	private Localization localization;

	public MessageResult(Localization localization) {
		this.messages = new HashMap<String, String>();
		this.localization = localization;
	}

	/**
	 * Adds a new message to the return of the request, 
	 * and this message related to a particular field on the screen.
	 * 
	 * @param fieldsView
	 *            Field on the screen that is related to this post
	 * @param key
	 *            Key file messages.properties internationalization
	 * @param params
	 *            A varchar containing any objects to be used as parameters to String.Format
	 */
	public void add(String fieldsView, String key, Object... params) {

		String message = localization.getMessage(PREFIX_VALIDATION + key);

		if (message != null) {

			message = String.format(message, params);

			for (String fieldName : fieldsView.split(",")) {
				if (messages.containsKey(fieldName)) {
					messages.put(fieldName, String.format("%s,%s",
							messages.get(fieldName), message));
				} else {
					messages.put(fieldName, message);
				}
			}

		}
	}

	/**
	 * @return messages
	 */
	public Map<String, String> getMessages() {
		return messages;
	}

	/**
	 * @return true if has messages
	 */
	public boolean hasMessages() {
		return !messages.isEmpty();
	}

	/**
	 * @param messages
	 */
	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}

	/**
	 * Main for tests
	 * @param args
	 */
	public static void main(String[] args) {
		new MessageResult(null).add("asds", "sdsd", "asd", "asdfasd");

	}

}
