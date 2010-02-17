/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.metadata.MetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataDTO;
import org.inbio.m3s.dto.metadata.TechnicalMetadataItemDTO;
import org.inbio.m3s.dto.metadata.util.MediaAttributeEntity;
import org.inbio.m3s.dto.search.SearchCriteriaTripletDTO;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.MetadataManager;
import org.inbio.m3s.service.SearchManager;

/**
 * @author jgutierrez
 *
 */
@SuppressWarnings("unused")
public class SearchManagerTest extends AbstractServiceTest{

	
	
	public void testUpdateMetadata(){
		SearchManager sm = (SearchManager) getBean(Properties.SEARCH_MANAGER);	
		logger.info("Usando el Search Manger: "+Properties.SEARCH_MANAGER);
		
		Integer searchFilterId = Integer.valueOf("8");
		Integer searchCriteriaId = Integer.valueOf("1");
		String value = "Aislamiento";
		
		List<SearchCriteriaTripletDTO> searchCriteria = new ArrayList<SearchCriteriaTripletDTO>();
		SearchCriteriaTripletDTO scTriplet = new SearchCriteriaTripletDTO(searchFilterId,searchCriteriaId,value);
		searchCriteria.add(scTriplet);
		
		for(SearchCriteriaTripletDTO sctDTO : searchCriteria){
			logger.info(sctDTO.toString());
		}
		
		int total = sm.getTotalResults(searchCriteria);
		
		logger.info("total de resultados: " + total);
	}

}
