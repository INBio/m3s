/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.GatheringMediaDAO;
import org.inbio.m3s.dao.core.ObservationMediaDAO;
import org.inbio.m3s.dao.core.PersonDAO;
import org.inbio.m3s.dao.core.SpecimenDAO;
import org.inbio.m3s.dao.core.SpecimenMediaDAO;
import org.inbio.m3s.dao.core.TaxonDAO;
import org.inbio.m3s.dao.core.TaxonMediaDAO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTOFactory;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;
import org.inbio.m3s.dto.taxonomy.ObservationLiteDTO;
import org.inbio.m3s.dto.taxonomy.ObservationLiteDTOFactory;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTOFactory;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTOFactory;
import org.inbio.m3s.dto.taxonomy.util.TaxonomicalRangeEntity;
import org.inbio.m3s.exception.TaxonNotFoundException;
import org.inbio.m3s.model.general.Specimen;
import org.inbio.m3s.model.core.GatheringMediaId;
import org.inbio.m3s.model.core.ObservedTaxonMediaId;
import org.inbio.m3s.model.core.SpecimenMediaId;
import org.inbio.m3s.model.core.TaxonMediaId;
import org.inbio.m3s.model.taxonomy.Taxon;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.util.BotanyUtils;

/**
 * @author jgutierrez
 *
 */
public class TaxonomyManagerImpl implements TaxonomyManager {
	
	protected static Log logger = LogFactory.getLog(TaxonomyManagerImpl.class);
	
	//DAO's
	TaxonDAO taxonDAO;
	GatheringMediaDAO gatheringMediaDAO;
	PersonDAO personDAO;
	SpecimenDAO specimenDAO;
	SpecimenMediaDAO specimenMediaDAO;
	ObservationMediaDAO observationMediaDAO;
	TaxonMediaDAO taxonMediaDAO;	
	
	//DTO Factories
	TaxonLiteDTOFactory tlDTOFactory;
	PersonLiteDTOFactory plDTOFactory;
	SpecimenLiteDTOFactory slDTOFactory;
	ObservationLiteDTOFactory olDTOFactory;
	
	//Managers
	AgentManager agentManager;
	
	/**
	 * 
	 */
	public List<TaxonLiteDTO> getTaxonsIncludedIn(String taxonDefaultName,
			TaxonomicalRangeEntity taxonomicalRange) {
		
		Taxon taxon = taxonDAO.findByNameAndRange(taxonDefaultName, taxonomicalRange.getId());
		
		if(taxonomicalRange.equals(TaxonomicalRangeEntity.ORDER))
			return tlDTOFactory.createDTOList(taxonDAO.findByOrder(taxon.getTaxonId()));
		else if(taxonomicalRange.equals(TaxonomicalRangeEntity.FAMILY))
			return tlDTOFactory.createDTOList(taxonDAO.findByFamily(taxon.getTaxonId()));
		else if(taxonomicalRange.equals(TaxonomicalRangeEntity.GENUS))
			return tlDTOFactory.createDTOList(taxonDAO.findByGenus(taxon.getTaxonId()));
		else if(taxonomicalRange.equals(TaxonomicalRangeEntity.SPECIES))
			return tlDTOFactory.createDTOList(taxonDAO.findBySpecies(taxon.getTaxonId()));
		
		return null;
	}

	/**
	 * Ideal option for auto complete graphical elements 
	 * 
	 */
	public List<TaxonLiteDTO> getTaxonsByPatialNameAndTaxonomicalRange(String taxonName,TaxonomicalRangeEntity taxonomicalRange) {
		List<Taxon> taxons =taxonDAO.findAllByRangeAndPartialNamePaginated(taxonomicalRange.getId(),"%"+taxonName+"%");
		if(taxons!=null){
			logger.debug("Total de taxones: "+ taxons.size());
			
			for(Taxon t : taxons){
				logger.debug("Taxon Id: "+ t.getTaxonId());
				logger.debug("Taxonomical Range Id: "+ t.getTaxonomicalRangeId());
				logger.debug("DefaultName: "+ t.getDefaultName());
				logger.debug("");
			}
			
		}
		return tlDTOFactory.createDTOList(taxons);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.TaxonomyManager#getAllGatheringsLiteForMedia(java.lang.String)
	 */
	public List<GatheringLiteDTO> getAllGatheringsLiteForMedia(String mediaKey) {
		
		//El DTO no es exactamente la representacion del objeto gathering media por lo 
		//que no tiene un factory, se construye acá mismo. ¿Quién dijo miedo?
		List<GatheringLiteDTO> gmLiteDTOs = new ArrayList<GatheringLiteDTO>();
		GatheringLiteDTO gmDTO;
		PersonLiteDTO plDTO;
		
		for(GatheringMediaId gmId : gatheringMediaDAO.findAllByMediaId(new Integer(mediaKey))){
			plDTO = (PersonLiteDTO) plDTOFactory.createDTO(personDAO.findGatheringResposibleById(gmId.getGatheringDetailPersonId()));
			gmDTO = new GatheringLiteDTO(gmId.getGatheringNumber().toString(), plDTO.getName());
			gmLiteDTOs.add(gmDTO);
		}

		return gmLiteDTOs;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.TaxonomyManager#getAllSpecimensLiteForMedia(java.lang.String)
	 */
	public List<SpecimenLiteDTO> getAllSpecimensLiteForMedia(String mediaKey) {
		
		List<SpecimenMediaId> smIdList = specimenMediaDAO.findAllByMediaId(new Integer(mediaKey));
		return slDTOFactory.createDTOList(smIdList);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.TaxonomyManager#getAllObservationsLiteForMedia(java.lang.String)
	 */
	public List<ObservationLiteDTO> getAllObservationsLiteForMedia(String mediaKey)
			throws IllegalArgumentException {
		
		List<ObservedTaxonMediaId> otmIdList = observationMediaDAO.findAllByMediaId(new Integer(mediaKey));
		return olDTOFactory.createDTOList(otmIdList);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.TaxonomyManager#getSpecimenLiteForGatheringCode(java.lang.String)
	 */
	public List<SpecimenLiteDTO> getSpecimenLiteForGatheringCode(String gatheringCode) throws IllegalArgumentException {
		logger.debug("getSpecimenLiteForGatheringCode... start");
		GatheringLiteDTO glDTO = BotanyUtils.getGatheringLiteDTOFromCode(gatheringCode);
		logger.debug(glDTO.toString());
		PersonLiteDTO plDTO = agentManager.getGatheringResposibleLiteByName(glDTO.getResponsiblePersonName());
		logger.debug(plDTO.toString());
		
		List<Specimen> sList = specimenDAO.findByGatheringNumberAndGatheringPersonId(new Integer(plDTO.getPersonKey()), new Integer(glDTO.getGatheringKey()));
		logger.debug("number of results: "+ sList.size());
		
		List<SpecimenLiteDTO> smLiteDTOList = new ArrayList<SpecimenLiteDTO>();
		for(Specimen s : sList){
			smLiteDTOList.add(new SpecimenLiteDTO(s.getSpecimenId().toString()));
		}
		
		return smLiteDTOList;
	}
	

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.TaxonomyManager#getTaxonLiteById(java.lang.String)
	 */
	public TaxonLiteDTO getTaxonLiteById(String taxonKey) throws IllegalArgumentException {
		
		Taxon t = (Taxon) taxonDAO.findById(Taxon.class, new Integer(taxonKey)); 
		return (TaxonLiteDTO) tlDTOFactory.createDTO(t);
	}

	
	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.TaxonomyManager#getTaxonLiteFromGatheringCode(java.lang.String)
	 */
	public List<TaxonLiteDTO> getTaxonLiteFromGatheringCode(String gatheringCode)
			throws IllegalArgumentException {
		//logger.debug("getTaxonIdsFromGatheringCode start");

		String errorMsj = "No se puede obtener taxonomía asociada a la recolecta  #"
				+ gatheringCode + ".";

		List<TaxonLiteDTO> tlDTOList = new ArrayList<TaxonLiteDTO>();
		TaxonLiteDTO tlDTO = null;
		List<SpecimenLiteDTO> slDTOList = getSpecimenLiteForGatheringCode(gatheringCode);

		try {
			logger.debug("numero de especimenes: "+ slDTOList.size());

			for(SpecimenLiteDTO slDTO : slDTOList) { 
				tlDTO = getTaxonLiteFromSpecimenId(slDTO.getSpecimenKey());
				tlDTOList.add(tlDTO);
			}
			logger.debug("numero de taxones: " + tlDTOList.size());

			return tlDTOList;

		} catch (Exception iae) {
			throw new IllegalArgumentException(errorMsj + iae.getMessage());
		}
	}


	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.TaxonomyManager#getTaxonLite(java.lang.String, java.lang.String)
	 */
	public TaxonLiteDTO getTaxonLite(String defaultName, String kingdomName) throws TaxonNotFoundException {
		logger.debug("start in getTaxonLite");
		Taxon kingdom = (Taxon) taxonDAO.findByNameAndRange(kingdomName, TaxonomicalRangeEntity.KINGDOM.getId());
		if(kingdom==null)
			throw new TaxonNotFoundException("The taxon["+kingdomName+"] cannot be found the database", null, kingdomName);
		logger.debug("Kingdom: "+kingdom.getDefaultName());
		Taxon t = (Taxon) taxonDAO.findByDefaultNameAndKingdomId(defaultName, kingdom.getTaxonId());
		if(t==null)
			throw new TaxonNotFoundException("The taxon["+defaultName+"] cannot be found the database", null, defaultName);
		logger.debug("taxon: "+t.getDefaultName());
		return (TaxonLiteDTO) tlDTOFactory.createDTO(t);
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.TaxonomyManager#getTaxonLiteForMediaId(java.lang.String)
	 */
	public List<TaxonLiteDTO> getTaxonLiteForMediaId(String mediaKey)
			throws IllegalArgumentException {
		
		List<TaxonMediaId> tmIdList = taxonMediaDAO.getByMediaId(new Integer(mediaKey));
		List<TaxonLiteDTO> tlDTOList = null;
		
		if(tmIdList!=null){
			tlDTOList = new ArrayList<TaxonLiteDTO>();
			for(TaxonMediaId tmId : tmIdList)
				tlDTOList.add((TaxonLiteDTO) tlDTOFactory.createDTO(taxonDAO.findById(Taxon.class, tmId.getTaxonId())));
		}
		
		return tlDTOList;
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.TaxonomyManager#getTaxonLiteFromObservationId(java.lang.String)
	 */
	public List<TaxonLiteDTO> getTaxonLiteFromObservationId(String observationKey)
			throws IllegalArgumentException {
		List<Taxon> tList = taxonDAO.findByObservationId(new Integer(observationKey));
		return tlDTOFactory.createDTOList(tList);
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.TaxonomyManager#getTaxonLiteFromSpecimenId(java.lang.String)
	 */
	public TaxonLiteDTO getTaxonLiteFromSpecimenId(String specimenKey)
			throws IllegalArgumentException {
		Taxon t = (Taxon) taxonDAO.findBySpecimenId(new Integer(specimenKey));
		return (TaxonLiteDTO) tlDTOFactory.createDTO(t);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.TaxonomyManager#getTaxonLite(java.lang.String)
	 */
	public List<TaxonLiteDTO> getTaxonLite(String defaultName)
			throws IllegalArgumentException {
		List<Taxon> tList = taxonDAO.findAllByName(defaultName);
		return tlDTOFactory.createDTOList(tList);
	}


	/**
	 * @return the taxonDAO
	 */
	public TaxonDAO getTaxonDAO() {
		return taxonDAO;
	}

	/**
	 * @param taxonDAO the taxonDAO to set
	 */
	public void setTaxonDAO(TaxonDAO taxonDAO) {
		this.taxonDAO = taxonDAO;
	}

	/**
	 * @return the gatheringMediaDAO
	 */
	public GatheringMediaDAO getGatheringMediaDAO() {
		return gatheringMediaDAO;
	}

	/**
	 * @param gatheringMediaDAO the gatheringMediaDAO to set
	 */
	public void setGatheringMediaDAO(GatheringMediaDAO gatheringMediaDAO) {
		this.gatheringMediaDAO = gatheringMediaDAO;
	}

	/**
	 * @return the personDAO
	 */
	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	/**
	 * @param personDAO the personDAO to set
	 */
	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	/**
	 * @return the plDTOFactory
	 */
	public PersonLiteDTOFactory getPlDTOFactory() {
		return plDTOFactory;
	}

	/**
	 * @param plDTOFactory the plDTOFactory to set
	 */
	public void setPlDTOFactory(PersonLiteDTOFactory plDTOFactory) {
		this.plDTOFactory = plDTOFactory;
	}

	/**
	 * @return the agentManager
	 */
	public AgentManager getAgentManager() {
		return agentManager;
	}

	/**
	 * @param agentManager the agentManager to set
	 */
	public void setAgentManager(AgentManager agentManager) {
		this.agentManager = agentManager;
	}

	/**
	 * @return the specimenDAO
	 */
	public SpecimenDAO getSpecimenDAO() {
		return specimenDAO;
	}

	/**
	 * @param specimenDAO the specimenDAO to set
	 */
	public void setSpecimenDAO(SpecimenDAO specimenDAO) {
		this.specimenDAO = specimenDAO;
	}

	/**
	 * @return the slDTOFactory
	 */
	public SpecimenLiteDTOFactory getSlDTOFactory() {
		return slDTOFactory;
	}

	/**
	 * @param slDTOFactory the slDTOFactory to set
	 */
	public void setSlDTOFactory(SpecimenLiteDTOFactory slDTOFactory) {
		this.slDTOFactory = slDTOFactory;
	}

	/**
	 * @return the specimenMediaDAO
	 */
	public SpecimenMediaDAO getSpecimenMediaDAO() {
		return specimenMediaDAO;
	}

	/**
	 * @param specimenMediaDAO the specimenMediaDAO to set
	 */
	public void setSpecimenMediaDAO(SpecimenMediaDAO specimenMediaDAO) {
		this.specimenMediaDAO = specimenMediaDAO;
	}


	/**
	 * @return the olDTOFactory
	 */
	public ObservationLiteDTOFactory getOlDTOFactory() {
		return olDTOFactory;
	}

	/**
	 * @param olDTOFactory the olDTOFactory to set
	 */
	public void setOlDTOFactory(ObservationLiteDTOFactory olDTOFactory) {
		this.olDTOFactory = olDTOFactory;
	}

	/**
	 * @return the observationMediaDAO
	 */
	public ObservationMediaDAO getObservationMediaDAO() {
		return observationMediaDAO;
	}

	/**
	 * @param observationMediaDAO the observationMediaDAO to set
	 */
	public void setObservationMediaDAO(ObservationMediaDAO observationMediaDAO) {
		this.observationMediaDAO = observationMediaDAO;
	}

	/**
	 * @return the tlDTOFactory
	 */
	public TaxonLiteDTOFactory getTlDTOFactory() {
		return tlDTOFactory;
	}

	/**
	 * @param tlDTOFactory the tlDTOFactory to set
	 */
	public void setTlDTOFactory(TaxonLiteDTOFactory tlDTOFactory) {
		this.tlDTOFactory = tlDTOFactory;
	}

	/**
	 * @return the taxonMediaDAO
	 */
	public TaxonMediaDAO getTaxonMediaDAO() {
		return taxonMediaDAO;
	}

	/**
	 * @param taxonMediaDAO the taxonMediaDAO to set
	 */
	public void setTaxonMediaDAO(TaxonMediaDAO taxonMediaDAO) {
		this.taxonMediaDAO = taxonMediaDAO;
	}

	
	public TaxonLiteDTO setKingdomName(TaxonLiteDTO tlDTO)
			throws IllegalArgumentException {
		TaxonLiteDTO kingdomtlDTO = getTaxonLiteById(tlDTO.getKingdomKey());
		tlDTO.setKingdomName(kingdomtlDTO.getDefaultName());
		return tlDTO;
	}

	public List<TaxonLiteDTO> setKingdomName(List<TaxonLiteDTO> tlDTOList)
			throws IllegalArgumentException {
		
		List<TaxonLiteDTO> updatedList = new ArrayList<TaxonLiteDTO>();

		for(TaxonLiteDTO tlDTO : tlDTOList)
			updatedList.add(setKingdomName(tlDTO));

		return updatedList;
	}


}
