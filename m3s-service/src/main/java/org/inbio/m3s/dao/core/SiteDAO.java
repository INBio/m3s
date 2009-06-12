package org.inbio.m3s.dao.core;

/**
 * 
 * @author jgutierrez
 *
 */
public interface SiteDAO {
	
	public String getSiteDBIdFromSpecimenNumber(Integer specimenDBId) throws IllegalArgumentException;
	
	public String getiteDBIdFromObservationNumber(Integer observationNumber) throws IllegalArgumentException;
	
	
	

}