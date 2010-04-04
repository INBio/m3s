package org.inbio.m3s.model.core;


import java.util.Date;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class UsePolicy extends LogGenericEntity {

	private static final long serialVersionUID = -8209822821365291765L;

	private Integer usePolicyId;

	private Integer textByDescriptionTextId;

	private Integer textByNameTextId;

	private String name;

	private String description;


	public UsePolicy() {
	}

	public UsePolicy(Integer textByNameTextId) {
		this.textByNameTextId = textByNameTextId;
	}

	public UsePolicy(Integer textByDescriptionTextId, Integer textByNameTextId,
			Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy, String name, String description) {
		this.textByDescriptionTextId = textByDescriptionTextId;
		this.textByNameTextId = textByNameTextId;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		this.name = name;
		this.description = description;
	}

	public Integer getUsePolicyId() {
		return this.usePolicyId;
	}

	public void setUsePolicyId(Integer usePolicyId) {
		this.usePolicyId = usePolicyId;
	}

	public Integer getTextByDescriptionTextId() {
		return this.textByDescriptionTextId;
	}

	public void setTextByDescriptionTextId(Integer textByDescriptionTextId) {
		this.textByDescriptionTextId = textByDescriptionTextId;
	}

	public Integer getTextByNameTextId() {
		return this.textByNameTextId;
	}

	public void setTextByNameTextId(Integer textByNameTextId) {
		this.textByNameTextId = textByNameTextId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
