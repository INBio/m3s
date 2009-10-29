/**
 * 
 */
package org.inbio.m3s.exception;

/**
 * When the system looks for a media type..
 * 
 * @author jgutierrez
 *
 */
public class MediaTypeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2940758270206728616L;
	
	private String notFoundMediaType;

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param notFoundMediaType
	 */
	public MediaTypeNotFoundException(String message, Throwable cause, String notFoundMediaType) {
		super(message, cause);
		this.setNotFoundMediaType(notFoundMediaType);
	}

	/**
	 * @param notFoundMediaType the notFoundMediaType to set
	 */
	public void setNotFoundMediaType(String notFoundMediaType) {
		this.notFoundMediaType = notFoundMediaType;
	}

	/**
	 * @return the notFoundMediaType
	 */
	public String getNotFoundMediaType() {
		return notFoundMediaType;
	};
	
}
