package org.inbio.m3s.model.core;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.inbio.m3s.model.LogGenericEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class MetadataStandard extends LogGenericEntity {

	/** NPI */
	private static final long serialVersionUID = -2248409637935449645L;

	private Integer metadataStandardId;

	private String name;

	private String description;

	private String metadataStandardImplementationClass;

	private Set<MediaAttributeType> mediaAttributeTypes = new HashSet<MediaAttributeType>(
			0);

	public MetadataStandard() {
	}

	public MetadataStandard(String name) {
		this.name = name;
	}

	public MetadataStandard(String name, String description,
			String metadataStandardImplementationClass, Date creationDate,
			String createdBy, Date lastModificationDate,
			String lastModificationBy,
			Set<MediaAttributeType> mediaAttributeTypes) {
		this.name = name;
		this.description = description;
		this.metadataStandardImplementationClass = metadataStandardImplementationClass;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		this.mediaAttributeTypes = mediaAttributeTypes;
	}

	public Integer getMetadataStandardId() {
		return this.metadataStandardId;
	}

	public void setMetadataStandardId(Integer metadataStandardId) {
		this.metadataStandardId = metadataStandardId;
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

	public String getMetadataStandardImplementationClass() {
		return this.metadataStandardImplementationClass;
	}

	public void setMetadataStandardImplementationClass(
			String metadataStandardImplementationClass) {
		this.metadataStandardImplementationClass = metadataStandardImplementationClass;
	}

	public Set<MediaAttributeType> getMediaAttributeTypes() {
		return this.mediaAttributeTypes;
	}

	public void setMediaAttributeTypes(
			Set<MediaAttributeType> mediaAttributeTypes) {
		this.mediaAttributeTypes = mediaAttributeTypes;
	}


}
