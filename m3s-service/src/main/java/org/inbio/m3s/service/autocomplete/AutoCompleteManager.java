/**
 * 
 */
package org.inbio.m3s.service.autocomplete;

import java.util.Map;


/**
 * @author jgutierrez
 *
 */
public interface AutoCompleteManager {

	
	/**
	 * Todos los manager que quieran usar autocomplete deben implementar este método
	 * 
	 * @param value autocomplete paramater value
	 * @return the map to be returned will have the Key as an integer and the name of the
	 *  option as an String
	 */
	public Map<Integer, String> getAutoCompleteOptions(String value);
}
