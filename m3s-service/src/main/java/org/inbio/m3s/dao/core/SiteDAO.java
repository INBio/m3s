package org.inbio.m3s.dao.core;

import org.inbio.m3s.dao.BaseDAO;

/**
 * 
 * @author jgutierrez
 *
 */
public interface SiteDAO extends BaseDAO{
	
	@Deprecated
	public String getSiteDBIdFromSpecimenNumber(Integer specimenDBId) throws IllegalArgumentException;
	
	@Deprecated
	public String getiteDBIdFromObservationNumber(Integer observationNumber) throws IllegalArgumentException;
	
	
	

}