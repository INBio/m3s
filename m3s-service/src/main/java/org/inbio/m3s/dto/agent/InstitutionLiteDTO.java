/**
 * 
 */
package org.inbio.m3s.dto.agent;



import org.inbio.m3s.dto.BaseDTO;

/**
 * @author jgutierrez
 *
 */
public class InstitutionLiteDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3490523251217605436L;

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
