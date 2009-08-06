/**
 * 
 */
package org.inbio.m3s.exception;

/**
 * When the system looks for a particular Taxon Name..
 * 
 * @author jgutierrez
 *
 */
public class TaxonNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2940758270206728616L;
	
	private String notFoundTaxonName;

	/**
	 * 
	 * @param message
	 * @param cause
	 * @param notFoundTaxonName
	 */
	public TaxonNotFoundException(String message, Throwable cause, String notFoundTaxonName) {
		super(message, cause);
		this.setNotFoundTaxonName(notFoundTaxonName);
	}

	/**
	 * @param notFoundTaxonName the notFoundTaxonName to set
	 */
	public void setNotFoundTaxonName(String notFoundTaxonName) {
		this.notFoundTaxonName = notFoundTaxonName;
	}

	/**
	 * @return the notFoundTaxonName
	 */
	public String getNotFoundTaxonName() {
		return notFoundTaxonName;
	};
	
}
