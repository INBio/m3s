/**
 * 
 */
package org.inbio.m3s.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.dao.core.MediaCategoryDAO;
import org.inbio.m3s.dao.core.MediaUseDAO;
import org.inbio.m3s.dao.core.UsePolicyDAO;
import org.inbio.m3s.dto.message.MediaCategoryDTO;
import org.inbio.m3s.dto.metadata.MediaUseDTO;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.util.ServiceUtil;

//import org.apache.log4j.Logger;

/**
 * A very slow and dump chache of general info queries done for example, the
 * persons names, institutions, mediaTypes, Categories, all that stuff.
 * 
 * Every information loaded takes as language the DEFAULT_LANGUAGE constant of
 * the config.Properties class
 * 
 * @author jgutierrez
 * @deprecated
 * 
 */
public class DataCache {

	private static Logger logger = Logger.getLogger(DataCache.class);

	public static boolean mediaUsesInCache = false;
	
	public static Integer mediaUses4Language = null;

	public static List<String> mediaUsesNames = new ArrayList<String>();

	public static List<Integer> mediaUsesDBIds = new ArrayList<Integer>();

	public static boolean usePoliciesInCache = false;

	public static List<String> usePoliciesNames = new ArrayList<String>();

	public static List<Integer> usePoliciesDBIds = new ArrayList<Integer>();

	public static boolean mediaCategoriesInCache = false;

	public static List<String> mediaCategoriesNames;

	public static List<Integer> mediaCategoriesDBIds;
	
	/*
	public static void initMediaUsesInfo() {
		//logger.debug("getting Media uses Info... using default language");
		
		MediaUseDAO mediaUseDAO = (MediaUseDAO) ServiceUtil.appContext.getBean("mediaUseDAO");
		
		try{
			List<MediaUseDTO> mediaUsesInfo = mediaUseDAO.listAllLite(new Integer(MessageManager.DEFAULT_LOCALE))
			
			mediaUsesDBIds = new ArrayList<Integer>();
			mediaUsesNames = new ArrayList<String>();
			
			for(MediaUseDTO muDTO : mediaUsesInfo){
				mediaUsesNames.add(muDTO.getMediaUseName());
				mediaUsesDBIds.add(Integer.valueOf(muDTO.getMediaUseKey()));
			}
			logger.debug("getting Media uses Info... is now on cache!.");
			mediaUsesInCache = true;
			mediaUses4Language = new Integer(MessageManager.DEFAULT_LOCALE);
			
		} catch (Exception e) {
			logger.error("Something gets wrong on initMediaUsesInfo.");
			logger.error(e.getMessage());
		}
	}
	*/

	public static void initUsePoliciesInfo() {
		logger.debug("getting use policy Info...");

		UsePolicyDAO usePolicyDAO = (UsePolicyDAO) ServiceUtil.appContext.getBean("usePolicyDAO");
		
		try {
			List<UsePolicyDTO> usePolicyInfo = usePolicyDAO.listAllLite();

			usePoliciesDBIds = new ArrayList<Integer>();
			usePoliciesNames = new ArrayList<String>();
			
			for(UsePolicyDTO upDTO: usePolicyInfo){
				usePoliciesNames.add(upDTO.getName());
				usePoliciesDBIds.add(Integer.valueOf(upDTO.getUsePolicyKey()));
	
			}

			logger.debug("getting Use Policy Info...... is now on cache!.");
			usePoliciesInCache = true;

		} catch (Exception e) {
			logger.error("Something gets wrong on initUsePoliciesInfo.");
			logger.error(e.getMessage());
		}

	}
		

	/**
	 * 
	 * @param mediaCategoriesInfo
	 */
	public static void initMediaCategoriesInfo() {
		logger.debug("getting media categories Info...");

		MediaCategoryDAO mediaCategoryDAO = (MediaCategoryDAO) ServiceUtil.appContext.getBean("mediaCategoryDAO");
		
		try {
			List<MediaCategoryDTO> mediaCategoryInfo = mediaCategoryDAO.listAllLite();

			mediaCategoriesDBIds = new ArrayList<Integer>();
			mediaCategoriesNames = new ArrayList<String>();
			
			for(MediaCategoryDTO mcl: mediaCategoryInfo){
				mediaCategoriesNames.add(mcl.getMediaCategoryName());
				mediaCategoriesDBIds.add(Integer.valueOf(mcl.getMediaCategoryKey()));
	
			}

			logger.debug("getting Media Category Info...... is now on cache!.");
			mediaCategoriesInCache = true;

		} catch (Exception e) {
			logger.error("Something gets wrong on initMediaCategoriesInfo.");
			logger.error(e.getMessage());
		}
	}
	
		
	/**
	 * @return the mediaUses4Language
	 */
	public static Integer getMediaUses4Language() {
		return mediaUses4Language;
	}

	/**
	 * @param mediaUses4Language the mediaUses4Language to set
	 */
	public static void setMediaUses4Language(Integer mediaUses4Language) {
		DataCache.mediaUses4Language = mediaUses4Language;
	}
}
