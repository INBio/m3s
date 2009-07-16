/**
 * 
 */
package org.inbio.m3s.gwt.client.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author jgutierrez
 *
 */
public class UsePolicyGWTDTO implements IsSerializable{

	private String usePolicyKey;
	
	private String name;

	/**
	 * 
	 */
	public UsePolicyGWTDTO() {
	}
	
	

	/**
	 * @param usePolicyKey
	 * @param name
	 */
	public UsePolicyGWTDTO(String usePolicyKey, String name) {
		this.usePolicyKey = usePolicyKey;
		this.name = name;
	}



	@Override
	public String toString(){
		return "El Use Policiy DTO tiene:" +
		"\n\tKey : " + this.getUsePolicyKey() +
		"\n\tName: '"+ this.getName() +"'";
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
