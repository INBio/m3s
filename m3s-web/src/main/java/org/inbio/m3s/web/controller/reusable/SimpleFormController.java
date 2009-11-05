/**
 * 
 */
package org.inbio.m3s.web.controller.reusable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author jgutierrez
 * 
 * Based ConfigurableContentController written by dmartin for the Gbif Portal
 * @see org.gbif.portal.web.controller.ConfigurableContentController
 *
 */
public class SimpleFormController extends SimpleController{

	private String formActionValue;
	
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView mav = super.handleRequestInternal(request, response);
		
		mav.addObject("formAction", formActionValue);
		
		
		return mav;
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

	

}
