/**
 * m3s-web
 *
 * Copyright (C) 2010  INBio (Instituto Nacional de Biodiversidad)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.inbio.m3s.web.controller.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.common.web.controller.XMLResponseMultiActionController;
import org.jdom.Document;
import org.jdom.Element;
import org.springframework.security.userdetails.UserDetailsManager;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jgutierrez
 *
 */
public class AdminAjaxController extends XMLResponseMultiActionController {

	UserDetailsManager userDetailsManager;
	/*
  <bean id="myUserDetailsService" class="org.inbio.m3s.service.impl.UserDetailsManagerImpl" >
  <property name="systemUserDAO" ref="systemUserDAO"/>
</bean>
*/
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rootElementName = "m3s-response";
		String userElementName = "user";
		String fullNameElementName = "fullname";
		String userNameElementName = "username";
		String rolesNameElementName = "roles";
		String projectsNameElementName = "projects";
		
	  //document creation
		Element root = new Element(rootElementName);

		
		Element dcLabelElement;
		Element dcValueElement;
		//process the dcElements
		/*
		for (String dcElementName : validElements) {

			if (validElements.contains(dcElementName)) {
				
				//add a new Element with the dcElementName tag
				dcLabelElement = new Element(dcElementName);
				root.addContent(dcLabelElement);

				for (ElementTypeDTO etDTO : dcDTO.getElementValues(dcElementName)) {
					//sets the values fot the dcElement tag
					dcValueElement = new Element(valueElement);
					dcLabelElement.addContent(dcValueElement);
					dcValueElement.setText(etDTO.getValue());
					if (StringUtils.isNotEmpty(etDTO.getLanguage()))
						dcValueElement.setAttribute(langElement, etDTO.getLanguage());

				}

			}
		}
*/		
		return writeXMLDocumentOnResponse(request, response, new Document());	
	}
	
}
