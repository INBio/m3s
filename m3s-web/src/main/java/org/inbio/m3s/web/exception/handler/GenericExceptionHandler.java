/**
 * 
 */
package org.inbio.m3s.web.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.web.exception.ValidationException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jgutierrez
 *
 */
public class GenericExceptionHandler implements HandlerExceptionResolver {

	protected static Log logger = LogFactory.getLog(GenericExceptionHandler.class);
	
	private String errorMessageKey = "errorMessageKey";
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {

		ModelAndView errorMav;
		
		logger.debug("resolving Exception");
		
		if (ex instanceof ValidationException){
			
			logger.debug("is a ValidationException");
			
			ValidationException ve = (ValidationException) ex;
			errorMav = new ModelAndView(ve.getViewName());
			errorMav.addObject(errorMessageKey, ve.getErrorMessageKey());
			errorMav.addAllObjects(ve.getModelElements());
			return errorMav;
			
	  }else if (ex instanceof IllegalArgumentException){
					errorMav = new ModelAndView("editStep2");
		 			errorMav.addObject("error", "ERROR: "+ex.getMessage());
					return errorMav;
	      //return new ModelAndView("arraystoreView");
		 } else
	      return null;

	}

}
