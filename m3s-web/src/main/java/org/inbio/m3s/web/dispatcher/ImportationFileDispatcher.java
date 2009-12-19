package org.inbio.m3s.web.dispatcher;

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
 * 
 * @author jgutierrez
 *
 */
public class ImportationFileDispatcher extends AbstractController {

	public String MEDIA_REAL_BASE_ADDRESS= "/mnt/m3sImages/INBio/MEDIA/IMPORTATION_FILES/";
	
	private ImportationManager importationManager;
	
	/** input values */
	private String usernameMetadata = "username";
	private String systemFileNameMetadata = "systemFileName";
	
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        
        ServletOutputStream out = httpServletResponse.getOutputStream(); // binary output
        String username = httpServletRequest.getParameter(usernameMetadata);
        String systemFileName = httpServletRequest.getParameter(systemFileNameMetadata);
        
        int contentLength = 0;
    	BufferedInputStream input = null;
    	
    	ImportControlDTOFull icDTOFull = importationManager.getImportControlDTOFull(username, systemFileName);

    	String fileAddress = MEDIA_REAL_BASE_ADDRESS +  icDTOFull.getCreationDate().toString();
    	fileAddress = fileAddress.concat(File.separator + icDTOFull.getSystemFileName());
    
    	// Open  file and prepare file object.
    	File file = new File(fileAddress);
    	input = new BufferedInputStream(new FileInputStream(file));
    	contentLength = input.available();
    	

    	//has to be gotten from the fileMiMEType metadata value
    	httpServletResponse.setContentType("application/excel");
	
  		//	 Write file contents to response.
    	while (contentLength-- > 0) {
    		out.write(input.read());
    	}
    		
    	out.flush();
    	out.close();
    		
    	return null;
    }
    

	/**
	 * @return the importationManager
	 */
	public ImportationManager getImportationManager() {
		return importationManager;
	}


	/**
	 * @param importationManager the importationManager to set
	 */
	public void setImportationManager(ImportationManager importationManager) {
		this.importationManager = importationManager;
	}


	/**
	 * @return the systemFileNameMetadata
	 */
	public String getSystemFileNameMetadata() {
		return systemFileNameMetadata;
	}


	/**
	 * @param systemFileNameMetadata the systemFileNameMetadata to set
	 */
	public void setSystemFileNameMetadata(String systemFileNameMetadata) {
		this.systemFileNameMetadata = systemFileNameMetadata;
	}


	/**
	 * @return the mEDIA_REAL_BASE_ADDRESS
	 */
	public String getMEDIA_REAL_BASE_ADDRESS() {
		return MEDIA_REAL_BASE_ADDRESS;
	}


	/**
	 * @param media_real_base_address the mEDIA_REAL_BASE_ADDRESS to set
	 */
	public void setMEDIA_REAL_BASE_ADDRESS(String media_real_base_address) {
		MEDIA_REAL_BASE_ADDRESS = media_real_base_address;
	}


	/**
	 * @return the usernameMetadata
	 */
	public String getUsernameMetadata() {
		return usernameMetadata;
	}


	/**
	 * @param usernameMetadata the usernameMetadata to set
	 */
	public void setUsernameMetadata(String usernameMetadata) {
		this.usernameMetadata = usernameMetadata;
	}
  	

}