/**
 * 
 */
package org.inbio.m3s.exception;

/**
 * When the system looks for a person..
 * 
 * @author jgutierrez
 *
 */
public class PersonNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2940758270206728616L;
	
	private String notFoundPerson;

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param notFoundPerson
	 */
	public PersonNotFoundException(String message, Throwable cause, String notFoundPerson) {
		super(message, cause);
		this.setNotFoundPerson(notFoundPerson);
	}

	/**
	 * @param notFoundPerson the notFoundPerson to set
	 */
	public void setNotFoundPerson(String notFoundPerson) {
		this.notFoundPerson = notFoundPerson;
	}

	/**
	 * @return the notFoundPerson
	 */
	public String getNotFoundPerson() {
		return notFoundPerson;
	};
	
}
