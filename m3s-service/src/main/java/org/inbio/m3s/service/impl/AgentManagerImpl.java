/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.InstitutionDAO;
import org.inbio.m3s.dao.core.PersonDAO;
import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.InstitutionLiteDTOFactory;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTOFactory;
import org.inbio.m3s.exception.InstitutionNotFoundException;
import org.inbio.m3s.exception.PersonNotFoundException;
import org.inbio.m3s.model.general.Institution;
import org.inbio.m3s.model.general.Person;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.autocomplete.AutoCompleteManager;

/**
 * @author jgutierrez
 * 
 */
public class AgentManagerImpl implements AgentManager, AutoCompleteManager {

	protected static Log logger = LogFactory.getLog(AgentManagerImpl.class);
	
	// DAO's
	private InstitutionDAO institutionDAO;
	private PersonDAO personDAO;

	// DTO Factories
	private InstitutionLiteDTOFactory institutionLiteDTOFactory;
	private PersonLiteDTOFactory personLiteDTOFactory;
	

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AgentManager#getInstitutionLiteByName(java.lang.String)
	 */
	public InstitutionLiteDTO getInstitutionLiteByName(String institutionName) throws InstitutionNotFoundException {
		
		Institution i = (Institution) institutionDAO.findByName(institutionName);
		if(i==null)
			throw new InstitutionNotFoundException("The institution ["+institutionName+"] cannot be found the database", null, institutionName);
		return (InstitutionLiteDTO) institutionLiteDTOFactory.createDTO(i);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AgentManager#getInstitutionLite(java.lang.String)
	 */
	public InstitutionLiteDTO getInstitutionLite(String institutionKey)
			throws IllegalArgumentException {
		
		Institution i = (Institution) institutionDAO.findById(Institution.class, new Integer(institutionKey));
		return (InstitutionLiteDTO) institutionLiteDTOFactory.createDTO(i);
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AgentManager#getAllLite()
	 */
	public List<InstitutionLiteDTO> getAllInstitutionLite() throws IllegalArgumentException {
		
		List<Institution> iList = institutionDAO.findAll(Institution.class);
		return (List<InstitutionLiteDTO>) institutionLiteDTOFactory.createDTOList(iList);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AgentManager#getPersonLiteByName(java.lang.String)
	 */
	public PersonLiteDTO getPersonLiteByName(String name) throws PersonNotFoundException {
		
		logger.debug("getPersonLiteByName["+name+"]");
		
		List<Person> pList = personDAO.findAll(Person.class);
		if(pList==null)
			throw new PersonNotFoundException("The person ["+name+"] cannot be found the database", null, name);
		List<PersonLiteDTO> pLiteDTOList = personLiteDTOFactory.createDTOList(pList);
		
		for(PersonLiteDTO plDTO : pLiteDTOList){
			if(name.compareTo(plDTO.getName()) == 0)
				return plDTO;
		}
		
		throw new PersonNotFoundException("The person ["+name+"] cannot be found the database", null, name);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AgentManager#getPersonLite(java.lang.String)
	 */
	public PersonLiteDTO getPersonLite(String personKey)
			throws IllegalArgumentException {
		
		Person p = (Person) personDAO.findById(Person.class, new Integer(personKey));
		return (PersonLiteDTO) personLiteDTOFactory.createDTO(p);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AgentManager#getAllLite()
	 */
	public List<PersonLiteDTO> getAllPersonLite() throws IllegalArgumentException {
		
		List<Person> pList = personDAO.findAll(Person.class);
		return (List<PersonLiteDTO>) personLiteDTOFactory.createDTOList(pList);
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AutoCompleteManager#getAutoCompleteOptions(java.lang.String)
	 */
	public Map<Integer, String> getAutoCompleteOptions(String value) {
		
		List<Person> pList = personDAO.findAllByPartialNamePaginated("%"+value+"%", 20);
		Map<Integer, String> results = new HashMap<Integer, String>();
		
		for(Person p: pList)
			results.put(p.getPersonId(), p.getFirstName() + " " +p.getLastName());
		
		return results;
	}

	

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AgentManager#getAllGatheringResponsibleLite()
	 */
	public List<PersonLiteDTO> getAllGatheringResponsibleLite() throws IllegalArgumentException {
		
		List<Person> pList = personDAO.findAllGatheringResponsible();
		return (List<PersonLiteDTO>) personLiteDTOFactory.createDTOList(pList);
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AgentManager#getGatheringResposibleLiteByName(java.lang.String)
	 */
	public PersonLiteDTO getGatheringResposibleLiteByName(String personName)
			throws IllegalArgumentException {
		
		List<Person> pList = personDAO.findAllGatheringResponsible();
		List<PersonLiteDTO> pLiteDTOList = personLiteDTOFactory.createDTOList(pList);
		
		for(PersonLiteDTO plDTO : pLiteDTOList){
			if(personName.compareTo(plDTO.getName()) == 0)
				return plDTO;
		}
		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AgentManager#getGatheringResposiblePersonLite(java.lang.String)
	 */
	public PersonLiteDTO getGatheringResposiblePersonLite(String personKey)
			throws IllegalArgumentException {
		
		Person p = (Person) personDAO.findGatheringResposibleById(new Integer(personKey));
		return (PersonLiteDTO) personLiteDTOFactory.createDTO(p);
	}


	/**
	 * @param institutionDAO the institutionDAO to set
	 */
	public void setInstitutionDAO(InstitutionDAO institutionDAO) {
		this.institutionDAO = institutionDAO;
	}

	/**
	 * @return the institutionDAO
	 */
	public InstitutionDAO getInstitutionDAO() {
		return institutionDAO;
	}

	/**
	 * @return the institutionLiteDTOFactory
	 */
	public InstitutionLiteDTOFactory getInstitutionLiteDTOFactory() {
		return institutionLiteDTOFactory;
	}

	/**
	 * @param institutionLiteDTOFactory the institutionLiteDTOFactory to set
	 */
	public void setInstitutionLiteDTOFactory(
			InstitutionLiteDTOFactory institutionLiteDTOFactory) {
		this.institutionLiteDTOFactory = institutionLiteDTOFactory;
	}

	/**
	 * @param personLiteDTOFactory the personLiteDTOFactory to set
	 */
	public void setPersonLiteDTOFactory(PersonLiteDTOFactory personLiteDTOFactory) {
		this.personLiteDTOFactory = personLiteDTOFactory;
	}

	/**
	 * @return the personLiteDTOFactory
	 */
	public PersonLiteDTOFactory getPersonLiteDTOFactory() {
		return personLiteDTOFactory;
	}

	/**
	 * @param personDAO the personDAO to set
	 */
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	/**
	 * @return the personDAO
	 */
	public PersonDAO getPersonDAO() {
		return personDAO;
	}


}
