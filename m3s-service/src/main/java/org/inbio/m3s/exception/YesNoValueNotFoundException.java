/**
 * 
 */
package org.inbio.m3s.exception;

/**
 * When the system looks for a yes or no value..
 * 
 * @author jgutierrez
 *
 */
public class YesNoValueNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2940758270206728616L;
	
	private String notFoundYesNoValue;

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param notFoundTaxonName
	 */
	public YesNoValueNotFoundException(String message, Throwable cause, String notFoundYesNoValue) {
		super(message, cause);
		this.setNotFoundYesNoValue(notFoundYesNoValue);
	}

	/**
	 * @param notFoundYesNoValue the notFoundYesNoValue to set
	 */
	public void setNotFoundYesNoValue(String notFoundYesNoValue) {
		this.notFoundYesNoValue = notFoundYesNoValue;
	}

	/**
	 * @return the notFoundYesNoValue
	 */
	public String getNotFoundYesNoValue() {
		return notFoundYesNoValue;
	};
	
}
