/**
 * 
 */
package org.inbio.m3s.service;


import java.util.List;

import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;

/**
 * @author jgutierrez
 * 
 */
public interface AgentManager {

	/**
	 * 
	 * @param institutionName
	 * @return
	 * @throws IllegalArgumentException
	 * 
	 */
	public InstitutionLiteDTO getInstitutionLiteByName(String institutionName) throws IllegalArgumentException;
	
	public InstitutionLiteDTO getInstitutionLite(String institutionKey) throws IllegalArgumentException;
	
	public List<InstitutionLiteDTO> getAllInstitutionLite() throws IllegalArgumentException;
	
	public PersonLiteDTO getPersonLiteByName(String name) throws IllegalArgumentException;
	
	public PersonLiteDTO getPersonLite(String personKey) throws IllegalArgumentException;
	
	public List<PersonLiteDTO> getAllPersonLite() throws IllegalArgumentException;

	public PersonLiteDTO getGatheringResposibleLiteByName(String personName)	throws IllegalArgumentException;
	
	public PersonLiteDTO getGatheringResposiblePersonLite(String personKey)	throws IllegalArgumentException;
	
	public List<PersonLiteDTO> getAllGatheringResponsibleLite() throws IllegalArgumentException;
	



}
