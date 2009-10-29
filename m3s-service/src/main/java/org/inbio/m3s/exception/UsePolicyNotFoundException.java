/**
 * 
 */
package org.inbio.m3s.exception;

/**
 * When the system looks for an Use Policy..
 * 
 * @author jgutierrez
 *
 */
public class UsePolicyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2940758270206728616L;
	
	private String notFoundUsePolicy;

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param notFoundTaxonName
	 */
	public UsePolicyNotFoundException(String message, Throwable cause, String notFoundUsePolicy) {
		super(message, cause);
		this.setNotFoundUsePolicy(notFoundUsePolicy);
	}

	/**
	 * @param notFoundUsePolicy the notFoundUsePolicy to set
	 */
	public void setNotFoundUsePolicy(String notFoundUsePolicy) {
		this.notFoundUsePolicy = notFoundUsePolicy;
	}

	/**
	 * @return the notFoundUsePolicy
	 */
	public String getNotFoundUsePolicy() {
		return notFoundUsePolicy;
	};
	
}
