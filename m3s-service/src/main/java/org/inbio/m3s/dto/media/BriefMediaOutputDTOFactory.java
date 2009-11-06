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
import org.inbio.m3s.dto.lite.MediaLite;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.TaxonomyManager;

/**
 * This class is more a Service than a simpleDTOFactory...
 * 
 * @author jgutierrez
 *
 */
public class BriefMediaOutputDTOFactory extends BaseDTOFactory {

	private static Logger logger = Logger.getLogger(BriefMediaOutputDTOFactory.class);

	//Manager
	private TaxonomyManager taxonomyManager;
	private AgentManager agentManager;
	
	public Object createDTO(Object mediaLiteObject) {
		
		if(mediaLiteObject == null)
			return null;
		MediaLite ml = (MediaLite) mediaLiteObject;
		return createBriefMediaOutputDTO(ml);
	}
	
	/**
	 * 
	 * @param ml
	 * @return
	 */
	private BriefMediaOutputDTO createBriefMediaOutputDTO(MediaLite ml){
		//Auxiliary variables
		List<TaxonLiteDTO> taxonLiteList = new ArrayList<TaxonLiteDTO>();
		TaxonLiteDTO tlDTO = null; 
		PersonLiteDTO pLite = null;
		InstitutionLiteDTO il = null;
		
		logger.debug("createBriefMediaOutputDTO");
		BriefMediaOutputDTO bmoDTO = new BriefMediaOutputDTO();
		
		//mediaKey
		bmoDTO.setMediaKey(String.valueOf(ml.getMediaId()));
		logger.debug("mediaKey is ready");
		
		//TODO media category has to be displayed in some way...
		
		//title: idealmente debe ser el título de la imágen, pero si este es nulo,
		// entonces se tomará el primer nombre cintífico que se pueda extraer. 
		// si ambos son nulos, el campo quedará vacío.
		if(ml.getTitle() == null  || ml.getTitle().length() == 0){
			logger.debug("title viene 'null' en MediaLite.");
			taxonLiteList = taxonomyManager.getTaxonLiteForMediaId(ml.getMediaId().toString());
				if(taxonLiteList.size() > 0){
					tlDTO = taxonLiteList.get(0);
					bmoDTO.setTitle(tlDTO.getDefaultName());
					logger.debug("Title will be the Taxon default name.");
				}
		} else {
			bmoDTO.setTitle(ml.getTitle());
		}
		logger.debug("title set with ["+bmoDTO.getTitle()+"].");
		
		
		//info1: Author name
		pLite = agentManager.getPersonLite(ml.getAuthorPersonId().toString());
		bmoDTO.setInfo1(pLite.getName());
		//di.setInfo1("Autor: "+pl.getDisplayName());
		logger.debug("info1 set with ["+bmoDTO.getInfo1()+"].");
		
		
		//info2: Owner text & Type
		if(ml.getOwnerInstitutionId() != null){
			il = agentManager.getInstitutionLite(ml.getOwnerInstitutionId().toString());
			bmoDTO.setInfo2(il.getName());
			logger.debug("info2 set with the owner institution");
		} else if (ml.getOwnerPersonId() != null){
			pLite = agentManager.getPersonLite(ml.getOwnerPersonId().toString());
			bmoDTO.setInfo2(pLite.getName());
			logger.debug("info2 set with the owner person");
		} else{
			bmoDTO.setInfo2("");
			logger.error("info2 set empty");
		}
		logger.debug("info2 set with ["+bmoDTO.getInfo2()+"]");
		
		//info3: fecha de la creación del multimedio
		//creation date
		bmoDTO.setInfo3(String.valueOf(ml.getCreationDate()));
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
