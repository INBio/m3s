/**
 * 
 */
package org.inbio.m3s.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author jgutierrez
 *
 */
public class InsertPageController extends AbstractController{
		
	
	public InsertPageController(){}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		return new ModelAndView("insertStep1","data",null);
	}


}
