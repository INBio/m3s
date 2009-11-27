package org.inbio.m3s.web.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.FileUploadBean;
import org.inbio.m3s.util.ImageMagickAPI;


import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;



/**
 * 
 * @author jgutierrez
 *@deprecated
 */
public class ImageUploadController extends SimpleFormController {

	public String filePath = "/usr/share/tomcat5.5/webapps/m3sINBioFiles/TEMP_MEDIA_DIR/";
	public String fileName = "laPrueba2.jpg";
	
	protected ModelAndView onSubmit(
      HttpServletRequest request,
      HttpServletResponse response,
      Object command,
      BindException errors) throws ServletException, IOException, Exception {
		
	   // cast the bean
    FileUploadBean bean = (FileUploadBean) command;

    //let's see if there's content there
    MultipartFile file = bean.getFile();
    if (file == null) {
         // hmm, that's strange, the user did not upload anything
    } else{
    	file.transferTo(new File(filePath+fileName));
    	
    	ImageMagickAPI.createThumb(filePath+fileName, filePath+"thumb-"+fileName);
    }
    //return new ModelAndView("home","medias",mediaLiteList);
    return new ModelAndView(this.getSuccessView(), "fileName", fileName);
     // well, let's do nothing with the bean for now and return
    //return super.onSubmit(request, response, command, errors);
	}

	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
    throws ServletException {
    // to actually be able to convert Multipart instance to byte[]
    // we have to register a custom editor
    binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    // now Spring knows how to handle multipart object and convert them
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}

