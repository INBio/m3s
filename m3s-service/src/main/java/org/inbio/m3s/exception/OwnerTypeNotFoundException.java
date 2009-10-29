/**
 * 
 */
package org.inbio.m3s.exception;

/**
 * When the system looks for an owner type..
 * 
 * @author jgutierrez
 *
 */
public class OwnerTypeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2940758270206728616L;
	
	private String notFoundOwnerType;

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param notFoundTaxonName
	 */
	public OwnerTypeNotFoundException(String message, Throwable cause, String notFoundOwnerType) {
		super(message, cause);
		this.setNotFoundOwnerType(notFoundOwnerType);
	}

	/**
	 * @param notFoundOwnerType the notFoundOwnerType to set
	 */
	public void setNotFoundOwnerType(String notFoundOwnerType) {
		this.notFoundOwnerType = notFoundOwnerType;
	}

	/**
	 * @return the notFoundOwnerType
	 */
	public String getNotFoundOwnerType() {
		return notFoundOwnerType;
	};
	
}
