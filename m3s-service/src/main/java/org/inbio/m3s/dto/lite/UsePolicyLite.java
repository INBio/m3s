/**
 * 
 */
package org.inbio.m3s.dto.lite;

import java.io.Serializable;

/**
 * @author jgutierrez
 *
 */
public class UsePolicyLite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer usePolicyId;
	
	private String name;

	/**
	 * 
	 */
	public UsePolicyLite() {
		super();
	}

	/**
	 * @param usePolicyId
	 * @param name
	 */
	public UsePolicyLite(Integer usePolicyId, String name) {
		super();
		this.usePolicyId = usePolicyId;
		this.name = name;
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

	/**
	 * @return the usePolicyId
	 */
	public Integer getUsePolicyId() {
		return usePolicyId;
	}

	/**
	 * @param usePolicyId the usePolicyId to set
	 */
	public void setUsePolicyId(Integer usePolicyId) {
		this.usePolicyId = usePolicyId;
	}
}
