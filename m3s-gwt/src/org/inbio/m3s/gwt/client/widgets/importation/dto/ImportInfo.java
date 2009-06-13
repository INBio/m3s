/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.importation.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author jgutierrez
 * 
 */
public class ImportInfo implements IsSerializable {

	private String fileName;

	private String status;

	private String date;

	private String downloadLink;

	/**
	 * Empty constructor
	 * 
	 */
	public ImportInfo() {

	}

	public ImportInfo(String fileName, String status, String date,
			String downloadLink) {
		this.setFileName(fileName);
		this.setStatus(status);
		this.setDate(date);
		this.setDownloadLink(downloadLink);
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param downloadLink the downloadLink to set
	 */
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	/**
	 * @return the downloadLink
	 */
	public String getDownloadLink() {
		return downloadLink;
	}

}
