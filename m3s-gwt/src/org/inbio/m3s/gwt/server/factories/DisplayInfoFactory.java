/**
 * 
 */
package org.inbio.m3s.gwt.server.factories;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.gwt.galleries.client.dto.DisplayInfo;
import org.inbio.gwt.galleries.client.dto.DisplayType;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.gwt.client.config.ClientProperties;
import org.inbio.m3s.service.AgentManager;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.util.ServiceUtil;
import org.inbio.m3s.dto.agent.InstitutionLiteDTO;
import org.inbio.m3s.dto.agent.PersonLiteDTO;
import org.inbio.m3s.dto.lite.MediaLite;
import org.inbio.m3s.dto.message.MediaCategoryDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;

/**
 * @author jgutierrez
 *
 */
public class DisplayInfoFactory {
	
	private static Logger logger = Logger.getLogger(DisplayInfoFactory.class);
	
	private static AgentManager agentManager = (AgentManager) ServiceUtil.appContext.getBean(Properties.AGENT_MANAGER);
	
	/**
	 * 
	 * @param mll
	 * @return
	 */
	public static List<DisplayInfo> createDisplayInfoList(List<MediaLite> mll){
	
		List<DisplayInfo> dibList = new ArrayList<DisplayInfo>();
		
		for(MediaLite ml: mll)
			dibList.add(DisplayInfoFactory.createDisplayInfo(ml));
		
		return dibList;
		
	}
	
	
	/**
	 * 
	 * @param ml
	 * @return
	 */
	public static DisplayInfo createDisplayInfo(MediaLite ml){
		
		logger.debug("createDisplayInfoBrief");
		DisplayInfo di = new DisplayInfo();
		
		try{
		MessageManager messageManager = (MessageManager) ServiceUtil.appContext.getBean(Properties.MESSAGE_MANAGER);
		AgentManager agentManager = (AgentManager) ServiceUtil.appContext.getBean(Properties.AGENT_MANAGER); 
		PersonLiteDTO pLite = null;
		InstitutionLiteDTO il = null;
		TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER);
		List<TaxonLiteDTO> taxonLiteList = new ArrayList<TaxonLiteDTO>(); //lista de TaxonLite
		TaxonLiteDTO tlDTO = null;
		MediaCategoryDTO mcDTO = null;
		UsePolicyDTO upDTO = null;
		
		//id
		di.setId(String.valueOf(ml.getMediaId()));
		logger.debug("id seteado");
		
		//type: actually sets the media category as the DisplayInfo.type
		mcDTO =  messageManager.getMediaCategoryByType(String.valueOf(ml.getMediaTypeId()));
		di.setType(getDisplayType(Integer.valueOf(mcDTO.getMediaCategoryKey())));
		logger.debug("type seteado");
		
		//url -> should be set latter
		//dib.setUrl(Properties.MODULE_BASE_URL+"getImage?size=thumb&id=" + dib.getId());
		di.setUrl(null);
		logger.debug("url en null");
		
		//title o si es null entonces el nombre scientifico del primer taxon
		if(ml.getTitle() == null  || ml.getTitle().length() == 0){
		logger.debug("title viene 'null' en MediaLite.");
		//di.setTitle("taxonomia");
		taxonLiteList = taxonomyManager.getTaxonLiteForMediaId(ml.getMediaId().toString());
			if(taxonLiteList.size() > 0){
				tlDTO = taxonLiteList.get(0);
				di.setTitle(tlDTO.getDefaultName());
				logger.debug("seteado taxon default name como title.");
			}
		} else {
			di.setTitle(ml.getTitle());
		}
		logger.debug("seteado title con ["+ml.getTitle()+"].");
			
		//info1 --> author name
		pLite = agentManager.getPersonLite(ml.getAuthorPersonId().toString());
		di.setInfo1(pLite.getName());
		//di.setInfo1("Autor: "+pl.getDisplayName());
		logger.debug("seteado info1.");
		
		//info2 --> dueño (tambien se sugirio codigo de colecta
		//Owner text & Type
		if(ml.getOwnerInstitutionId() != null){
			il = agentManager.getInstitutionLite(ml.getOwnerInstitutionId().toString());
			//di.setInfo2("Dueño: "+il.getName());
			di.setInfo2(il.getName());
			logger.debug("seteado info2. con owner institution");
		} else if (ml.getOwnerPersonId() != null){
			pLite = agentManager.getPersonLite(ml.getOwnerPersonId().toString());
			//di.setInfo2("Dueño: "+pl.getDisplayName());
			di.setInfo2(pLite.getName());
			logger.debug("seteado info2. con owner person");
		}
		
		//info3 -> use policy
		upDTO = messageManager.getUsePolicy(String.valueOf(ml.getUsePolicyId()));
		di.setInfo3("Politica de Uso: "+upDTO.getName());
		logger.debug("seteado info.");
		
		di.setInfo4("");
		di.setInfo5("");
		di.setInfo6("");
		di.setInfo7("");

		//info8 --> multimedia ID
		di.setInfo8("Identificador: "+ml.getMediaId());
		logger.debug("seteado info8.");
		
		 
		}catch(Exception e){
			logger.error("exception");
			logger.error(e.getMessage());
		}
		return di;
	}


	/**
	 * gets the apropiated media type
	 * 
	 * @param mediaCategoryId
	 * @return
	 */
	private static DisplayType getDisplayType(Integer mediaCategoryId) {
		
		if(mediaCategoryId.intValue() == ClientProperties.IMAGE_MEDIA_CATEGORY_ID){
			return DisplayType.IMAGE;
		
		} else if(mediaCategoryId.intValue() == ClientProperties.VIDEO_MEDIA_CATEGORY_ID){
			return DisplayType.VIDEO;
		} if(mediaCategoryId.intValue() == ClientProperties.AUDIO_MEDIA_CATEGORY_ID){
			return DisplayType.AUDIO;
		}
		
		return null;
	}


	/**
	 * @param agentManager the agentManager to set
	 */
	public static void setAgentManager(AgentManager agentManager) {
		DisplayInfoFactory.agentManager = agentManager;
	}


	/**
	 * @return the agentManager
	 */
	public static AgentManager getAgentManager() {
		return agentManager;
	}

}
