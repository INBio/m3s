/*
 * UsesAndCopyrightsDTO.java
 *
 * Created on February 12, 2007, 3:40 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.inbio.m3s.dto;

import java.io.Serializable;
import java.util.List;

import org.inbio.m3s.dto.lite.MediaUseLite;

/**
 * This class is intended to be useful in the metadata parameters pass beetwen
 * the usecases package and the gwt package.
 * 
 * This class will had DB/Hibernate type objects in order to be usefull in the
 * persitance process. This objects are Integer, String, etc and may vary
 * depending on the entity class
 * 
 * @author jgutierrez
 */
public class UsesAndCopyrightsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer mediaId;

	private Integer authorId;

	private Integer institutionOwnerId;

	private Integer personOwnerId;

	private Integer UsePolicyID;

	// list of integers
	//private List mediaUsesIds;
	private List<MediaUseLite> mediaUsesList;

	private Character isBackup;

	private Character isPublic;

	
	
	/*
select new org.inbio.m3s.dto.UsesAndCopyrightsDTO(" +
						" m.authorPersonId, m.ownerPersonId, m.ownerInstitutionId, "
						+ "m.usePolicy.usePolicyId, m.isBackup, m.isPublic)
	 */
	

	/**
	 * @param authorId
	 * @param personOwnerId
	 * @param institutionOwnerId
	 * @param usePolicyID
	 * @param isBackup
	 * @param isPublic
	 */
	public UsesAndCopyrightsDTO(Integer authorId, Integer personOwnerId,
			Integer institutionOwnerId, Integer usePolicyID, Character isBackup,
			Character isPublic) {
		this.authorId = authorId;
		this.personOwnerId = personOwnerId;
		this.institutionOwnerId = institutionOwnerId;
		UsePolicyID = usePolicyID;
		this.isBackup = isBackup;
		this.isPublic = isPublic;
	}

	/**
	 * @param mediaId
	 * @param authorId
	 * @param institutionOwnerId
	 * @param personOwnerId
	 * @param usePolicyID
	 * @param mediaUsesList
	 * @param isBackup
	 * @param isPublic
	 */
	public UsesAndCopyrightsDTO(Integer mediaId, Integer authorId,
			Integer institutionOwnerId, Integer personOwnerId, Integer usePolicyID,
			List<MediaUseLite> mediaUsesList, Character isBackup, Character isPublic) {
		this.mediaId = mediaId;
		this.authorId = authorId;
		this.institutionOwnerId = institutionOwnerId;
		this.personOwnerId = personOwnerId;
		UsePolicyID = usePolicyID;
		this.mediaUsesList = mediaUsesList;
		this.isBackup = isBackup;
		this.isPublic = isPublic;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString(){
		return "El Uses and Copyrights Metadata DTO tiene:" +
				"\n\tMedia id: " + this.getMediaId() +
				"\n\tAuthor id: " + this.getAuthorId() +
				"\n\tInstitution Owner id: " + this.getInstitutionOwnerId() +
				"\n\tPerson Owner id: " + this.getPersonOwnerId() +
				"\n\tUse Policy Id: " + this.getUsePolicyID() +
				"\n\tAssociated Media Uses: "+ this.getMediaUsesList().size() +
				"\n\tIs backup: "+ this.getIsBackup() +
				"\n\tIs public: "+ this.getIsPublic() +
				"";
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @return the mediaId
	 */
	public Integer getMediaId() {
		return mediaId;
	}

	/**
	 * Creates a new instance of UsesAndCopyrightsDTO
	 */
	public UsesAndCopyrightsDTO() {
	}

	/**
	 * @param authorId
	 *            the authorId to set
	 */
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	/**
	 * @return the authorId
	 */
	public Integer getAuthorId() {
		return authorId;
	}

	/**
	 * @param institutionOwnerId
	 *            the institutionOwnerId to set
	 */
	public void setInstitutionOwnerId(Integer institutionOwnerId) {
		this.institutionOwnerId = institutionOwnerId;
	}

	/**
	 * @return the institutionOwnerId
	 */
	public Integer getInstitutionOwnerId() {
		return institutionOwnerId;
	}

	/**
	 * @param personOwnerId
	 *            the personOwnerId to set
	 */
	public void setPersonOwnerId(Integer personOwnerId) {
		this.personOwnerId = personOwnerId;
	}

	/**
	 * @return the personOwnerId
	 */
	public Integer getPersonOwnerId() {
		return personOwnerId;
	}

	/**
	 * @param usePolicyID
	 *            the usePolicyID to set
	 */
	public void setUsePolicyID(Integer usePolicyID) {
		UsePolicyID = usePolicyID;
	}

	/**
	 * @return the usePolicyID
	 */
	public Integer getUsePolicyID() {
		return UsePolicyID;
	}


	/**
	 * @param isBackup
	 *            the isBackup to set
	 */
	public void setIsBackup(Character isBackup) {
		this.isBackup = isBackup;
	}

	/**
	 * @return the isBackup
	 */
	public Character getIsBackup() {
		return isBackup;
	}

	/**
	 * @param isPublic
	 *            the isPublic to set
	 */
	public void setIsPublic(Character isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * @return the isPublic
	 */
	public Character getIsPublic() {
		return isPublic;
	}

	/**
	 * @return the mediaUsesList
	 */
	public List<MediaUseLite> getMediaUsesList() {
		return mediaUsesList;
	}

	/**
	 * @param mediaUsesList the mediaUsesList to set
	 */
	public void setMediaUsesList(List<MediaUseLite> mediaUsesList) {
		this.mediaUsesList = mediaUsesList;
	}

}
