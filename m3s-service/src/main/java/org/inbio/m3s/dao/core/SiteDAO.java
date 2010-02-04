package org.inbio.m3s.dao.core;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.model.general.Site;

/**
 * 
 * @author jgutierrez
 *
 */
public interface SiteDAO extends GenericBaseDAO<Site, Integer>{
	
	@Deprecated
	public String getSiteDBIdFromSpecimenNumber(Integer specimenDBId) throws IllegalArgumentException;
	
	@Deprecated
	public String getiteDBIdFromObservationNumber(Integer observationNumber) throws IllegalArgumentException;
	
	
	

}