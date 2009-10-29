/**
 * 
 */
package org.inbio.m3s.exception;

/**
 * When the system looks for an Media Use..
 * 
 * @author jgutierrez
 *
 */
public class MediaUseNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2940758270206728616L;
	
	private String notFoundMediaUse;

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param notFoundTaxonName
	 */
	public MediaUseNotFoundException(String message, Throwable cause, String notFoundMediaUse) {
		super(message, cause);
		this.setNotFoundMediaUse(notFoundMediaUse);
	}

	/**
	 * @param notFoundMediaUse the notFoundMediaUse to set
	 */
	public void setNotFoundMediaUse(String notFoundMediaUse) {
		this.notFoundMediaUse = notFoundMediaUse;
	}

	/**
	 * @return the notFoundMediaUse
	 */
	public String getNotFoundMediaUse() {
		return notFoundMediaUse;
	};
	
}
