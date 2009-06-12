/**
 * 
 */
package org.inbio.m3s.dto.agent;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class InstitutionLiteDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 456870559838502944L;

	private String institutionKey;

	private String name;

	/**
	 * 
	 */
	public InstitutionLiteDTO() {
		super();
	}

	/**
	 * @param institutionKey
	 * @param name
	 */
	public InstitutionLiteDTO(String institutionKey, String name) {
		super();
		this.institutionKey = institutionKey;
		this.name = name;
	}
	
	@Override
	public String toString(){
		return "El Institution Lite DTO tiene:" +
		"\n\tKey : " + this.getInstitutionKey() +
		"\n\tName: "+ this.getName() +
		"";
	}
	
	/**
	 * @return the institutionKey
	 */
	public String getInstitutionKey() {
		return institutionKey;
	}

	/**
	 * @param institutionKey the institutionKey to set
	 */
	public void setInstitutionKey(String institutionKey) {
		this.institutionKey = institutionKey;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


}
