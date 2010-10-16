/**
 * m3s-web
 * 
 * Copyright (C) 2010 INBio (Instituto Nacional de Biodiversidad)
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.inbio.common.web.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.Document;
import org.jdom.output.XMLOutputter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author jgutierrez
 * 
 */
public class XMLResponseMultiActionController extends MultiActionController {

	/* the charset to decode the url. Default is UTF-8. */
	private String urlCharset = "UTF-8";

	/**
	 * 
	 * @param request
	 * @param response
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public ModelAndView writeXMLDocumentOnResponse(HttpServletRequest request,
			HttpServletResponse response, Document doc) throws Exception {

		// return response XML
		XMLOutputter xmlOutputter = new XMLOutputter();

		response.setCharacterEncoding(urlCharset);
		response.setContentType("text/xml;charset=" + urlCharset);
		ServletOutputStream out = response.getOutputStream(); // binary output

		xmlOutputter.output(doc, out);
		out.flush();
		out.close();

		return null;
	}

}
