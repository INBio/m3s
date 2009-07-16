/**
 * 
 */
package org.inbio.m3s.dto.metadata;

import org.inbio.m3s.dto.BaseDTO;

/**
 * @author jgutierrez
 *
 */
public class UsePolicyDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String usePolicyKey;
	
	private String name;

	@Override
	public String toString(){
		return "El Use Policiy DTO tiene:" +
		"\n\tKey : " + this.getUsePolicyKey() +
		"\n\tName: '"+ this.getName() +"'";
	}
	
	/**
	 * 
	 */
	public UsePolicyDTO() {
	}
	
	/**
	 * @param usePolicyId
	 * @param name
	 */
	public UsePolicyDTO(Integer usePolicyId, String name) {
		this.usePolicyKey = String.valueOf(usePolicyId);
		this.name = name;
	}
	
	/**
	 * @param usePolicyKey
	 * @param name
	 */
	public UsePolicyDTO(String usePolicyKey, String name) {
		this.usePolicyKey = usePolicyKey;
		this.name = name;
	}

	/**
	 * @return the usePolicyKey
	 */
	public String getUsePolicyKey() {
		return usePolicyKey;
	}

	/**
	 * @param usePolicyKey the usePolicyKey to set
	 */
	public void setUsePolicyKey(String usePolicyKey) {
		this.usePolicyKey = usePolicyKey;
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
