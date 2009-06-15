/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.util.List;

import org.inbio.m3s.dao.core.InstitutionDAO;
import org.inbio.m3s.dao.core.PersonDAO;
import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.InstitutionLiteDTOFactory;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTOFactory;
import org.inbio.m3s.model.general.Institution;
import org.inbio.m3s.model.general.Person;
import org.inbio.m3s.service.AgentManager;

/**
 * @author jgutierrez
 * 
 */
public class AgentManagerImpl implements AgentManager {

	private InstitutionDAO institutionDAO;
	
	private PersonDAO personDAO;
	
	private InstitutionLiteDTOFactory institutionLiteDTOFactory;
	
	private PersonLiteDTOFactory personLiteDTOFactory;
	

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AgentManager#getInstitutionLiteByName(java.lang.String)
	 */
	public InstitutionLiteDTO getInstitutionLiteByName(String institutionName)
			throws IllegalArgumentException {
		
		Institution i = (Institution) institutionDAO.findByName(institutionName);
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
	@SuppressWarnings("unchecked")
	public List<InstitutionLiteDTO> getAllInstitutionLite() throws IllegalArgumentException {
		
		List<Object> iList = institutionDAO.findAll(Institution.class);
		return (List<InstitutionLiteDTO>) institutionLiteDTOFactory.createDTOList(iList);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AgentManager#getPersonLiteByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public PersonLiteDTO getPersonLiteByName(String name)
			throws IllegalArgumentException {
		
		List<Object> pList = personDAO.findAll(Person.class);
		List<PersonLiteDTO> pLiteDTOList = personLiteDTOFactory.createDTOList(pList);
		
		for(PersonLiteDTO plDTO : pLiteDTOList){
			if(name.compareTo(plDTO.getName()) == 0)
				return plDTO;
		}
		
		return null;
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
	@SuppressWarnings("unchecked")
	public List<PersonLiteDTO> getAllPersonLite() throws IllegalArgumentException {
		
		List<Object> pList = personDAO.findAll(Person.class);
		return (List<PersonLiteDTO>) personLiteDTOFactory.createDTOList(pList);
	}
	

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AgentManager#getAllGatheringResponsibleLite()
	 */
	@SuppressWarnings("unchecked")
	public List<PersonLiteDTO> getAllGatheringResponsibleLite() throws IllegalArgumentException {
		
		List<Person> pList = personDAO.findAllGatheringResponsible();
		return (List<PersonLiteDTO>) personLiteDTOFactory.createDTOList(pList);
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AgentManager#getGatheringResposibleLiteByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
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