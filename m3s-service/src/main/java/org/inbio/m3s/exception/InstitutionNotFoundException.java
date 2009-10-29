/**
 * 
 */
package org.inbio.m3s.exception;

/**
 * When the system looks for a institution..
 * 
 * @author jgutierrez
 *
 */
public class InstitutionNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2940758270206728616L;
	
	private String notFoundInstitution;

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param notFoundInstitution
	 */
	public InstitutionNotFoundException(String message, Throwable cause, String notFoundInstitution) {
		super(message, cause);
		this.setNotFoundInstitution(notFoundInstitution);
	}

	/**
	 * @param notFoundInstitution the notFoundInstitution to set
	 */
	public void setNotFoundInstitution(String notFoundInstitution) {
		this.notFoundInstitution = notFoundInstitution;
	}

	/**
	 * @return the notFoundInstitution
	 */
	public String getNotFoundInstitution() {
		return notFoundInstitution;
	};
	
}
