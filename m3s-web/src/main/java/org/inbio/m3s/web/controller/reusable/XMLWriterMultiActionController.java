/**
 * 
 */
package org.inbio.m3s.web.controller.reusable;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author jgutierrez
 * @deprecated
 * @use org.inbio.common.web.controller.XMLResponseMultiActionController
 */
public class XMLWriterMultiActionController extends MultiActionController {

	private String urlCharset = "UTF-8";
	
	/**
	 * Method that writes XML Strings to the output.
	 * 
	 * @param request
	 * @param response
	 * @param xml
	 * @return
	 * @throws Exception
	 * @deprecated
	 * @use writeXMLDocumentOnResponse
	 */
	public ModelAndView writeXMLOnResponse(HttpServletRequest request,
			HttpServletResponse response, String xml) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		ServletOutputStream out = response.getOutputStream(); // binary output
	  
	  out.println(xml);
				
		out.flush();
		out.close();
		
		return null;
	}
	
}
