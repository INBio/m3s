/**
 * 
 */
package org.inbio.m3s.web.controller.ajax;

import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.service.autocomplete.AutoCompleteManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;


/**
 * @author jgutierrez
 *
 */
public class AutoCompleteController extends AbstractController {
	
	/* The AutoComplete Options provider must implement this interface*/
	private AutoCompleteManager autoCompleteManager;
	/* For the yahoo auto complete javascript this value always is = "query"*/
	private String query = "query";
 
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ServletOutputStream out = response.getOutputStream(); // binary output
		response.setCharacterEncoding("UTF-8");
		
		
		String queryValue = request.getParameter(query);
		Map<Integer, String> options = autoCompleteManager.getAutoCompleteOptions(queryValue);
		
		if(options!=null){
			for (Integer key : options.keySet()) {
				String value = options.get(key);
				out.println(value+"\t"+key.toString());
			}
		}
		
		out.flush();
		out.close();
		
		return null;
	}

	/**
	 * @return the autoCompleteManager
	 */
	public AutoCompleteManager getAutoCompleteManager() {
		return autoCompleteManager;
	}

	/**
	 * @param autoCompleteManager the autoCompleteManager to set
	 */
	public void setAutoCompleteManager(AutoCompleteManager autoCompleteManager) {
		this.autoCompleteManager = autoCompleteManager;
	}

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	
}
