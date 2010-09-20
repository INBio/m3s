/**
 * 
 */
package org.inbio.m3s.dto.media;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.dto.BaseDTOFactory;
import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.model.core.Media;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.TaxonomyManager;

/**
 * This class is more a Service than a simpleDTOFactory...
 * 
 * @author jgutierrez
 *
 */
public class BriefMediaOutputDTOFactory extends BaseDTOFactory<Media,BriefMediaOutputDTO> {

	private static Logger logger = Logger.getLogger(BriefMediaOutputDTOFactory.class);

	//Manager
	private TaxonomyManager taxonomyManager;
	private AgentManager agentManager;
	
	public BriefMediaOutputDTO createDTO(Media mediaLiteObject) {
		
		if(mediaLiteObject == null)
			return null;
		return createBriefMediaOutputDTO(mediaLiteObject);
	}
	
	/**
	 * 
	 * @param ml
	 * @return
	 */
	private BriefMediaOutputDTO createBriefMediaOutputDTO(Media m){
		//Auxiliary variables
		List<TaxonLiteDTO> taxonLiteList = new ArrayList<TaxonLiteDTO>();
		TaxonLiteDTO tlDTO = null; 
		PersonLiteDTO pLite = null;
		InstitutionLiteDTO il = null;
		
		logger.debug("createBriefMediaOutputDTO");
		BriefMediaOutputDTO bmoDTO = new BriefMediaOutputDTO();
		
		//mediaKey
		bmoDTO.setMediaKey(String.valueOf(m.getMediaId()));
		logger.debug("mediaKey is ready");
		
		//TODO media category has to be displayed in some way...
		
		//title: idealmente debe ser el título de la imágen, pero si este es nulo,
		// entonces se tomará el primer nombre cintífico que se pueda extraer. 
		// si ambos son nulos, el campo quedará vacío.
		if(m.getTitle() == null  || m.getTitle().length() == 0){
			logger.debug("title viene 'null' en MediaLite.");
			taxonLiteList = taxonomyManager.getTaxonLiteForMediaId(m.getMediaId().toString());
				if(taxonLiteList.size() > 0){
					tlDTO = taxonLiteList.get(0);
					bmoDTO.setTitle(tlDTO.getDefaultName());
					logger.debug("Title will be the Taxon default name.");
				}
		} else {
			bmoDTO.setTitle(m.getTitle());
		}
		logger.debug("title set with ["+bmoDTO.getTitle()+"].");
		
		
		//info1: Author name
		pLite = agentManager.getPersonLite(m.getAuthorPersonId().toString());
		bmoDTO.setInfo1(pLite.getName());
		//di.setInfo1("Autor: "+pl.getDisplayName());
		logger.debug("info1 set with ["+bmoDTO.getInfo1()+"].");
		
		
		//info2: Owner text & Type
		if(m.getOwnerInstitutionId() != null){
			il = agentManager.getInstitutionLite(m.getOwnerInstitutionId().toString());
			bmoDTO.setInfo2(il.getName());
			logger.debug("info2 set with the owner institution");
		} else if (m.getOwnerPersonId() != null){
			pLite = agentManager.getPersonLite(m.getOwnerPersonId().toString());
			bmoDTO.setInfo2(pLite.getName());
			logger.debug("info2 set with the owner person");
		} else{
			bmoDTO.setInfo2("");
			logger.error("info2 set empty");
		}
		logger.debug("info2 set with ["+bmoDTO.getInfo2()+"]");
		
		//info3: fecha de la creación del multimedio
		//creation date
		bmoDTO.setInfo3(String.valueOf(m.getCreationDate()));
		logger.debug("info3 set with ["+bmoDTO.getInfo3()+"]");
		
		return bmoDTO;
	}

	/**
	 * @return the taxonomyManager
	 */
	public TaxonomyManager getTaxonomyManager() {
		return taxonomyManager;
	}

	/**
	 * @param taxonomyManager the taxonomyManager to set
	 */
	public void setTaxonomyManager(TaxonomyManager taxonomyManager) {
		this.taxonomyManager = taxonomyManager;
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

}
