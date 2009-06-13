/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.metadata.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Uses and Copyrigths Textual Value
 * 
 * A get/set class for managing the UsesAndCopyrigths metadata display on the
 * graphical interface
 * 
 * @author jgutierrez
 * 
 */
public class UsesAndCopyrightsTV implements IsSerializable {

	private Integer mediaId;

	private String author;

	private int ownerType;

	private String owner;

	private String usePolicy;

	// String of multimedia uses with TextDelmiter
	private String multimediaUses;

	private boolean isBackup;

	private boolean isPublic;

	/**
	 * @param mediaId
	 *            the mediaId to set
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
	 * Empty constructor
	 * 
	 */
	public UsesAndCopyrightsTV() {
	}

	/**
	 * @param multimediaUses
	 *            the multimediaUses to set
	 */
	public void setMultimediaUses(String multimediaUses) {
		this.multimediaUses = multimediaUses;
	}

	/**
	 * @return the multimediaUses
	 */
	public String getMultimediaUses() {
		return multimediaUses;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param ownerType
	 *            the ownerType to set
	 */
	public void setOwnerType(int ownerType) {
		this.ownerType = ownerType;
	}

	/**
	 * @return the ownerType
	 */
	public int getOwnerType() {
		return ownerType;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param usePolicy
	 *            the usePolicy to setUsesAndCopyrigths.AFIRMATIVE
	 */
	public void setUsePolicy(String usePolicy) {
		this.usePolicy = usePolicy;
	}

	/**
	 * @return the usePolicy
	 */
	public String getUsePolicy() {
		return usePolicy;
	}

	/**
	 * @param isBackup
	 *            the isBackup to set
	 */
	public void setIsBackup(boolean isBackup) {
		this.setBackup(isBackup);
	}

	/**
	 * @return the isBackup
	 */
	public boolean getIsBackup() {
		return isBackup();
	}

	/**
	 * @param isPublic
	 *            the isPublic to set
	 */
	public void setIsPublic(boolean isPublic) {
		this.setPublic(isPublic);
	}

	/**
	 * @return the isPublic
	 */
	public boolean getIsPublic() {
		return isPublic();
	}

	/**
	 * @param isBackup
	 *            the isBackup to set
	 */
	public void setBackup(boolean isBackup) {
		this.isBackup = isBackup;
	}

	/**
	 * @return the isBackup
	 */
	public boolean isBackup() {
		return isBackup;
	}

	/**
	 * @param isPublic
	 *            the isPublic to set
	 */
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * @return the isPublic
	 */
	public boolean isPublic() {
		return isPublic;
	}

}
