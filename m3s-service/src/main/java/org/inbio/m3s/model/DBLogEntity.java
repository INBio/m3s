/**
 * 
 */
package org.inbio.m3s.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jgutierrez
 * 
 * Entity for database log records
 * 
 */
public class DBLogEntity {

	//@deprecated
	private String userName = "m3s";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;

	/**
	 * For create rergistry purposes
	 * 
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 * @throws
	 */
	public void setSaveValues() {
		Date actualDate = getLogDate();
		//String user = UserProfile.getUsername();
		String user =userName;
		this.setCreationDate(actualDate);
		this.setCreatedBy(user);
		this.setLastModificationDate(actualDate);
		this.setLastModificationBy(user);
	}

	/**
	 * For update purposes
	 * 
	 * @param lastModificationDate
	 * @param lastModificationBy
	 */
	public void setUpdateValues() {
		this.setLastModificationDate(getLogDate());
		this.setLastModificationBy(userName);
	}

	/**
	 * 
	 * @return
	 */
	private Date getLogDate() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String literalDate = dateFormat.format(new java.util.Date())
					.toString();
			Date date = dateFormat.parse(literalDate);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("ERROR escribiendo la bitacora");
			return new Date();
		}
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param lastModificationDate
	 *            the lastModificationDate to set
	 */
	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * @return the lastModificationDate
	 */
	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	/**
	 * @param lastModificationBy
	 *            the lastModificationBy to set
	 */
	public void setLastModificationBy(String lastModificationBy) {
		this.lastModificationBy = lastModificationBy;
	}

	/**
	 * @return the lastModificationBy
	 */
	public String getLastModificationBy() {
		return lastModificationBy;
	}

}
