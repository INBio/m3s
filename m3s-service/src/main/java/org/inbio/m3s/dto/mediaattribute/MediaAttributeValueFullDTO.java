/**
 * 
 */
package org.inbio.m3s.dto.mediaattribute;

import java.util.Date;

import org.inbio.m3s.dto.BaseDTO;


/**
 * @author jgutierrez
 *
 */
public class MediaAttributeValueFullDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1086031833302472302L;

	private Integer mediaId;
	
	private Integer mediaAttributeId;

	private String valueVarchar;

	private Integer valueId;

	private Integer valueNumber;

	private Date valueDate;
	
	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	/**
	 * 
	 */
	public MediaAttributeValueFullDTO() {
	}

	/**
	 * @param mediaAttributeId
	 * @param mediaId
	 * @param valueVarchar
	 * @param valueId
	 * @param valueNumber
	 * @param valueDate
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 */
	public MediaAttributeValueFullDTO(Integer mediaAttributeId, Integer mediaId, String valueVarchar, Integer valueId, Integer valueNumber, Date valueDate, Date creationDate, String createdBy, Date lastModificationDate, String lastModificationBy) {
		this.mediaAttributeId = mediaAttributeId;
		this.mediaId = mediaId;
		this.valueVarchar = valueVarchar;
		this.valueId = valueId;
		this.valueNumber = valueNumber;
		this.valueDate = valueDate;
		this.creationDate = creationDate;
		this.createdBy = createdBy;
		this.lastModificationDate = lastModificationDate;
		this.lastModificationBy = lastModificationBy;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the lastModificationBy
	 */
	public String getLastModificationBy() {
		return lastModificationBy;
	}

	/**
	 * @param lastModificationBy the lastModificationBy to set
	 */
	public void setLastModificationBy(String lastModificationBy) {
		this.lastModificationBy = lastModificationBy;
	}

	/**
	 * @return the lastModificationDate
	 */
	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	/**
	 * @param lastModificationDate the lastModificationDate to set
	 */
	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * @return the mediaAttributeId
	 */
	public Integer getMediaAttributeId() {
		return mediaAttributeId;
	}

	/**
	 * @param mediaAttributeId the mediaAttributeId to set
	 */
	public void setMediaAttributeId(Integer mediaAttributeId) {
		this.mediaAttributeId = mediaAttributeId;
	}

	/**
	 * @return the mediaId
	 */
	public Integer getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @return the valueDate
	 */
	public Date getValueDate() {
		return valueDate;
	}

	/**
	 * @param valueDate the valueDate to set
	 */
	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	/**
	 * @return the valueId
	 */
	public Integer getValueId() {
		return valueId;
	}

	/**
	 * @param valueId the valueId to set
	 */
	public void setValueId(Integer valueId) {
		this.valueId = valueId;
	}

	/**
	 * @return the valueNumber
	 */
	public Integer getValueNumber() {
		return valueNumber;
	}

	/**
	 * @param valueNumber the valueNumber to set
	 */
	public void setValueNumber(Integer valueNumber) {
		this.valueNumber = valueNumber;
	}

	/**
	 * @return the valueVarchar
	 */
	public String getValueVarchar() {
		return valueVarchar;
	}

	/**
	 * @param valueVarchar the valueVarchar to set
	 */
	public void setValueVarchar(String valueVarchar) {
		this.valueVarchar = valueVarchar;
	}
}
