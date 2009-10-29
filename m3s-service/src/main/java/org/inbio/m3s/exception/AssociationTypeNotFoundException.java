/**
 * 
 */
package org.inbio.m3s.exception;

/**
 * When the system looks for an association type..
 * 
 * @author jgutierrez
 *
 */
public class AssociationTypeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2940758270206728616L;
	
	private String notFoundAssociationType;

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param notFoundTaxonName
	 */
	public AssociationTypeNotFoundException(String message, Throwable cause, String notFoundAssociationType) {
		super(message, cause);
		this.setNotFoundAssociationType(notFoundAssociationType);
	}

	/**
	 * @param notFoundAssociationType the notFoundAssociationType to set
	 */
	public void setNotFoundAssociationType(String notFoundAssociationType) {
		this.notFoundAssociationType = notFoundAssociationType;
	}

	/**
	 * @return the notFoundAssociationType
	 */
	public String getNotFoundAssociationType() {
		return notFoundAssociationType;
	};
	
}
