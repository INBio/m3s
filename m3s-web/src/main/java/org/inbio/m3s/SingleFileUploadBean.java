/**
 * 
 */
package org.inbio.m3s;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author jgutierrez
 *
 */
public class SingleFileUploadBean {

	private MultipartFile file;

	/**
	 * @return the file
	 */
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
