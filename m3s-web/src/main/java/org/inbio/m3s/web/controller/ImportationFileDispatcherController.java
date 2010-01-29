/**
 * 
 */
package org.inbio.m3s.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author jgutierrez
 *
 */
public class ImportationFileDispatcherController extends AbstractController {
	
	private String contentTypeMetadata;// ="application/vnd.ms-excel"
	private String fileIdMetadata;// ="id"
	private String importationPath;// = ${importationFilesPath};
	
	
	
	/**
	 * Returns a importation file
	 * 
	 * The servlet receives a list of parameters in HttpServletRequest method
	 * parameter, the way each parameter is used will be explain here:
	 * 
	 * id : the importation file identifier that is stored in the field SystemFileName of the ImportControl object
	 * 
	 * Example of use: http://m3s.inbio.ac.cr/getImportationFile?id=1234.xml
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//parameters
		String fileId =request.getParameter(fileIdMetadata);
		
		//find the file and open it
		File file = new File(importationPath+fileId);
	
		//output...
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));
		int contentLength = input.available();

	  //output
		ServletOutputStream out = response.getOutputStream(); // binary output
		// has to be gotten from the fileMiMEType metadata value
		response.setContentType(contentTypeMetadata);

	
	  //	 Write file contents to response.
	  while (contentLength-- > 0) {
	  	out.write(input.read());
	  }
	  
	  out.flush();
	  out.close();
	  
	  return null;
	}



	/**
	 * @return the contentTypeMetadata
	 */
	public String getContentTypeMetadata() {
		return contentTypeMetadata;
	}



	/**
	 * @param contentTypeMetadata the contentTypeMetadata to set
	 */
	public void setContentTypeMetadata(String contentTypeMetadata) {
		this.contentTypeMetadata = contentTypeMetadata;
	}



	/**
	 * @return the fileIdMetadata
	 */
	public String getFileIdMetadata() {
		return fileIdMetadata;
	}



	/**
	 * @param fileIdMetadata the fileIdMetadata to set
	 */
	public void setFileIdMetadata(String fileIdMetadata) {
		this.fileIdMetadata = fileIdMetadata;
	}



	/**
	 * @return the importationPath
	 */
	public String getImportationPath() {
		return importationPath;
	}



	/**
	 * @param importationPath the importationPath to set
	 */
	public void setImportationPath(String importationPath) {
		this.importationPath = importationPath;
	}

	
	
}
