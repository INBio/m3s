/**
 * 
 */
package org.inbio.m3s;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author jgutierrez
 *
 */
public class FileUploadBean {

	private MultipartFile file;
	
	 public void setFile(MultipartFile file) {
     this.file = file;
 }

 public MultipartFile getFile() {
     return file;
 }

}
