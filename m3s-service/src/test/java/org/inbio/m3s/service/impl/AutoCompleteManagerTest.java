/**
 * 
 */
package org.inbio.m3s.service.impl;


import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.autocomplete.AutoCompleteManager;

/**
 * @author jgutierrez
 *
 */
public class AutoCompleteManagerTest extends AbstractServiceTest{
	
	protected static Log logger = LogFactory.getLog(AutoCompleteManagerTest.class);

	public void testGetAutoCompleteOptionsTaxonomy(){

		logger.info("Usando el AutoCompleteManager del TaxonomyManager: "+Properties.TAXONOMY_MANAGER);
		AutoCompleteManager acm = (AutoCompleteManager) getBean(Properties.TAXONOMY_MANAGER);
		
		Map<Integer, String> results = acm.getAutoCompleteOptions("ma");
		
		for(Integer key : results.keySet()){
			logger.info("key: '"+key+"' value: '"+results.get(key)+"'");
		}
		
		assertTrue( true );
	}
	

	public void testGetAutoCompleteOptionsMediaAuthor(){

		logger.info("Usando el AutoCompleteManager del AgentManager: "+Properties.AGENT_MANAGER);
		AutoCompleteManager acm = (AutoCompleteManager) getBean(Properties.AGENT_MANAGER);
		
		Map<Integer, String> results = acm.getAutoCompleteOptions("i");
		
		for(Integer key : results.keySet()){
			logger.info("key: '"+key+"' value: '"+results.get(key)+"'");
		}
		
		assertTrue( true );
	}


}

