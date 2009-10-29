/**
 * 
 */
package org.inbio.m3s.exception;

/**
 * When the system looks for a keyword..
 * 
 * @author jgutierrez
 *
 */
public class KeywordNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2940758270206728616L;
	
	private String notFoundKeyword;

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param notFoundKeyword
	 */
	public KeywordNotFoundException(String message, Throwable cause, String notFoundKeyword) {
		super(message, cause);
		this.setNotFoundKeyword(notFoundKeyword);
	}

	/**
	 * @param notFoundKeyword the notFoundKeyword to set
	 */
	public void setNotFoundKeyword(String notFoundKeyword) {
		this.notFoundKeyword = notFoundKeyword;
	}

	/**
	 * @return the notFoundKeyword
	 */
	public String getNotFoundKeyword() {
		return notFoundKeyword;
	};
	
}
