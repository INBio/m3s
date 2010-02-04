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

	private Text textByDescriptionTextId;

	private Text textByNameTextId;

	private String name;

	private String description;


	public UsePolicy() {
	}

	public UsePolicy(Text textByNameTextId) {
		this.textByNameTextId = textByNameTextId;
	}

	public UsePolicy(Text textByDescriptionTextId, Text textByNameTextId,
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

	public Text getTextByDescriptionTextId() {
		return this.textByDescriptionTextId;
	}

	public void setTextByDescriptionTextId(Text textByDescriptionTextId) {
		this.textByDescriptionTextId = textByDescriptionTextId;
	}

	public Text getTextByNameTextId() {
		return this.textByNameTextId;
	}

	public void setTextByNameTextId(Text textByNameTextId) {
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
