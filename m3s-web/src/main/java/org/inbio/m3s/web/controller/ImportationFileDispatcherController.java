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

import org.inbio.m3s.dto.importcontrol.ImportControlDTOFull;
import org.inbio.m3s.service.ImportationManager;
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

	// managers
	private ImportationManager importationManager;

	/**
	 * Returns a importation file
	 * 
	 * The servlet receives a list of parameters in HttpServletRequest method
	 * parameter, the way each parameter is used will be explain here:
	 * 
	 * id : the importation file identifier that is stored in the field
	 * SystemFileName of the ImportControl object
	 * 
	 * Example of use: http://m3s.inbio.ac.cr/getImportationFile?id=1234.xml
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			// parameters
			String systemFileName = request.getParameter(fileIdMetadata);

			ImportControlDTOFull icDTO = importationManager
					.getImportControlDTOFull(systemFileName);

			// find the file and open it
			File file = new File(importationPath + systemFileName);

			// output...
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(
					file));
			int contentLength = input.available();

			// output
			ServletOutputStream out = response.getOutputStream(); // binary output
			// has to be gotten from the fileMiMEType metadata value
			response.setContentType(contentTypeMetadata);
			response.setHeader("Content-Disposition", "attachment; filename="
					+ icDTO.getUserFileName());

			// Write file contents to response.
			while (contentLength-- > 0) {
				out.write(input.read());
			}

			out.flush();
			out.close();

			return null;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error404");
		}

	}

	/**
	 * @return the contentTypeMetadata
	 */
	public String getContentTypeMetadata() {
		return contentTypeMetadata;
	}

	/**
	 * @param contentTypeMetadata
	 *          the contentTypeMetadata to set
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
	 * @param fileIdMetadata
	 *          the fileIdMetadata to set
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
	 * @param importationPath
	 *          the importationPath to set
	 */
	public void setImportationPath(String importationPath) {
		this.importationPath = importationPath;
	}

	/**
	 * @return the importationManager
	 */
	public ImportationManager getImportationManager() {
		return importationManager;
	}

	/**
	 * @param importationManager
	 *          the importationManager to set
	 */
	public void setImportationManager(ImportationManager importationManager) {
		this.importationManager = importationManager;
	}

}
