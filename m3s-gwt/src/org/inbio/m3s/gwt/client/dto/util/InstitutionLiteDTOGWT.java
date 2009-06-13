/**
 * 
 */
package org.inbio.m3s.gwt.client.dto.util;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 
 * Este objeto debería ser igual que el que viene de la capa de service, pero
 * por ignorancia, no se como heredar el otro objeto y que GWT lo pueda usar,
 * tengo que crear este enmascaramiento, güeiso. 
 * @author jgutierrez
 *
 */
public class InstitutionLiteDTOGWT implements IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String institutionKey;

	private String name;

	/**
	 * 
	 */
	public InstitutionLiteDTOGWT() {
		super();
	}

	/**
	 * @param institutionKey
	 * @param name
	 */
	public InstitutionLiteDTOGWT(String institutionKey, String name) {
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
