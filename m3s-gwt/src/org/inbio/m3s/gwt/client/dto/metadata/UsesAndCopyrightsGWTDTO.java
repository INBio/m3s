/**
 * 
 */
package org.inbio.m3s.gwt.client.dto.metadata;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author jgutierrez
 *
 */
public class UsesAndCopyrightsGWTDTO implements IsSerializable{

	private String mediaKey;

	private String authorKey;

	private String personOwnerKey;
	
	private String institutionOwnerKey;

	private String usePolicyKey;

	private List<MediaUseGWTDTO> mediaUsesList;

	private boolean isBackup;

	private boolean isPublic;

	
	/**
	 * 
	 */
	public UsesAndCopyrightsGWTDTO() {
	}


	/**
	 * @param mediaKey
	 * @param authorKey
	 * @param personOwnerKey
	 * @param institutionOwnerKey
	 * @param usePolicyKey
	 * @param mediaUsesList
	 * @param isBackup
	 * @param isPublic
	 */
	public UsesAndCopyrightsGWTDTO(String mediaKey, String authorKey,
			String personOwnerKey, String institutionOwnerKey, String usePolicyKey,
			List<MediaUseGWTDTO> mediaUsesList, boolean isBackup, boolean isPublic) {
		this.mediaKey = mediaKey;
		this.authorKey = authorKey;
		this.personOwnerKey = personOwnerKey;
		this.institutionOwnerKey = institutionOwnerKey;
		this.usePolicyKey = usePolicyKey;
		this.mediaUsesList = mediaUsesList;
		this.isBackup = isBackup;
		this.isPublic = isPublic;
	}

	/**
	 * 
	 */
	@Override
	public String toString(){
		return "El Uses and Copyrights Metadata GWT/DTO tiene:" +
				"\n\tMedia id: " + this.getMediaKey() +
				"\n\tAuthor id: " + this.getAuthorKey() +
				"\n\tInstitution Owner id: " + this.getInstitutionOwnerKey() +
				"\n\tPerson Owner id: " + this.getPersonOwnerKey() +
				"\n\tUse Policy Id: " + this.getUsePolicyKey() +
				"\n\tAssociated Media Uses: "+ this.getMediaUsesList().size() +
				"\n\tIs backup: "+ this.getIsBackup() +
				"\n\tIs public: "+ this.getIsPublic() +
				"";
	}

	/**
	 * @return the mediaKey
	 */
	public String getMediaKey() {
		return mediaKey;
	}


	/**
	 * @param mediaKey the mediaKey to set
	 */
	public void setMediaKey(String mediaKey) {
		this.mediaKey = mediaKey;
	}


	/**
	 * @return the authorKey
	 */
	public String getAuthorKey() {
		return authorKey;
	}


	/**
	 * @param authorKey the authorKey to set
	 */
	public void setAuthorKey(String authorKey) {
		this.authorKey = authorKey;
	}


	/**
	 * @return the personOwnerKey
	 */
	public String getPersonOwnerKey() {
		return personOwnerKey;
	}


	/**
	 * @param personOwnerKey the personOwnerKey to set
	 */
	public void setPersonOwnerKey(String personOwnerKey) {
		this.personOwnerKey = personOwnerKey;
	}


	/**
	 * @return the institutionOwnerKey
	 */
	public String getInstitutionOwnerKey() {
		return institutionOwnerKey;
	}


	/**
	 * @param institutionOwnerKey the institutionOwnerKey to set
	 */
	public void setInstitutionOwnerKey(String institutionOwnerKey) {
		this.institutionOwnerKey = institutionOwnerKey;
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
	 * @return the mediaUsesList
	 */
	public List<MediaUseGWTDTO> getMediaUsesList() {
		return mediaUsesList;
	}


	/**
	 * @param mediaUsesList the mediaUsesList to set
	 */
	public void setMediaUsesList(List<MediaUseGWTDTO> mediaUsesList) {
		this.mediaUsesList = mediaUsesList;
	}


	/**
	 * @return the isBackup
	 */
	public boolean getIsBackup() {
		return isBackup;
	}


	/**
	 * @param isBackup the isBackup to set
	 */
	public void setIsBackup(boolean isBackup) {
		this.isBackup = isBackup;
	}


	/**
	 * @return the isPublic
	 */
	public boolean getIsPublic() {
		return isPublic;
	}


	/**
	 * @param isPublic the isPublic to set
	 */
	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	
	
}
