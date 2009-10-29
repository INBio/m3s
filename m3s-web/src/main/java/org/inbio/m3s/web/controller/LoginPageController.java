/**
 * 
 */
package org.inbio.m3s.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author jgutierrez
 *
 */
public class LoginPageController extends AbstractController{
	
	private String viewName;
	
	private String formActionValue;
	
	
	private String metadataUserName;
	private String metadataPassword;
	
	
	protected static Log logger = LogFactory.getLog(LoginPageController.class);
	
	public LoginPageController(){}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView(viewName);
		//mav.addObject("formAction", formActionValue);
		
		return mav;
	}

	/**
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}

	/**
	 * @param viewName the viewName to set
	 */
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	/**
	 * @return the formActionValue
	 */
	public String getFormActionValue() {
		return formActionValue;
	}

	/**
	 * @param formActionValue the formActionValue to set
	 */
	public void setFormActionValue(String formActionValue) {
		this.formActionValue = formActionValue;
	}

	/**
	 * @return the metadataUserName
	 */
	public String getMetadataUserName() {
		return metadataUserName;
	}

	/**
	 * @param metadataUserName the metadataUserName to set
	 */
	public void setMetadataUserName(String metadataUserName) {
		this.metadataUserName = metadataUserName;
	}

	/**
	 * @return the metadataPassword
	 */
	public String getMetadataPassword() {
		return metadataPassword;
	}

	/**
	 * @param metadataPassword the metadataPassword to set
	 */
	public void setMetadataPassword(String metadataPassword) {
		this.metadataPassword = metadataPassword;
	}


}
