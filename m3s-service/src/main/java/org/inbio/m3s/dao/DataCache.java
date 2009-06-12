/**
 * 
 */
package org.inbio.m3s.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.dao.core.MediaCategoryDAO;
import org.inbio.m3s.dao.core.MediaTypeDAO;
import org.inbio.m3s.dao.core.MediaUseDAO;
import org.inbio.m3s.dao.core.UsePolicyDAO;
import org.inbio.m3s.dto.lite.MediaCategoryLite;
import org.inbio.m3s.dto.lite.MediaTypeLite;
import org.inbio.m3s.dto.lite.MediaUseLite;
import org.inbio.m3s.dto.lite.UsePolicyLite;
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
 * 
 */
public class DataCache {

	private static Logger logger = Logger.getLogger(DataCache.class);

	public static boolean peopleInCache = false;

	public static List<String> peopleNames = new ArrayList<String>();

	public static List<Integer> peopleDBIds = new ArrayList<Integer>();

	public static boolean institutionsInCache = false;

	public static List<String> institutionsNames = new ArrayList<String>();

	public static List<Integer> institutionsDBIds = new ArrayList<Integer>();

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

	public static boolean mediaTypesInCache = false;

	public static String mediaType4Category = "";

	public static List<String> mediaTypesNames;

	public static List<Integer> mediaTypesDBIds;

	/**
	 * sets the peopleNames and peopleDBIds
	 * 
	 */
	/*
	public static void initPeopleInfo() {
		//logger.debug("getting People Info...");

		PersonDAO personDAO = (PersonDAO) ServiceUtil.appContext.getBean("INBioPersonDAO");
		
		try {
			List<PersonDTOLite> peopleInfo = personDAO.listAllLite();

			peopleDBIds = new ArrayList<Integer>();
			peopleNames = new ArrayList<String>();

			for(PersonDTOLite personDTOLite : peopleInfo){
				peopleDBIds.add(personDTOLite.getPersonId());
				peopleNames.add(personDTOLite.getDisplayName());
			}

			//logger.debug("getting People Info... is now on cache!.");
			peopleInCache = true;

		} catch (Exception e) {
			//logger.error("Something gets wrong on initPeopleInfo.");
			//logger.error(e.getMessage());
		}
	}
	*/

	/*
	public static void initInstitutionsInfo() {
		//logger.debug("getting Institutions Info...");

		InstitutionDAO institutionDAO = (InstitutionDAO) ServiceUtil.appContext.getBean("INBioInstitutionDAO");
		
		try {
			List<InstitutionDTOLite> instutionsInfo = institutionDAO.listAllLite();

			institutionsDBIds = new ArrayList<Integer>();
			institutionsNames = new ArrayList<String>();
			
			for(InstitutionDTOLite institutionDTOLite: instutionsInfo){
				institutionsNames.add(institutionDTOLite.getName());
				institutionsDBIds.add(institutionDTOLite.getInstitutionId());
			}

			//logger.debug("getting Institutions Info...... is now on cache!.");
			institutionsInCache = true;

		} catch (Exception e) {
			//logger.error("Something gets wrong on initInstitutionsInfo.");
			//logger.error(e.getMessage());
		}

	}
*/
	
	public static void initMediaUsesInfo() {
		//logger.debug("getting Media uses Info... using default language");
		
		MediaUseDAO mediaUseDAO = (MediaUseDAO) ServiceUtil.appContext.getBean("mediaUseDAO");
		
		try{
			List<MediaUseLite> mediaUsesInfo = mediaUseDAO.listAllLite(new Integer(MessageManager.DEFAULT_LANGUAGE));
			
			mediaUsesDBIds = new ArrayList<Integer>();
			mediaUsesNames = new ArrayList<String>();
			
			for(MediaUseLite mul : mediaUsesInfo){
				mediaUsesNames.add(mul.getMediaUseName());
				mediaUsesDBIds.add(mul.getMediaUseId());
			}
			logger.debug("getting Media uses Info... is now on cache!.");
			mediaUsesInCache = true;
			mediaUses4Language = new Integer(MessageManager.DEFAULT_LANGUAGE);
			
		} catch (Exception e) {
			logger.error("Something gets wrong on initMediaUsesInfo.");
			logger.error(e.getMessage());
		}
	}

	public static void initUsePoliciesInfo() {
		logger.debug("getting use policy Info...");

		UsePolicyDAO usePolicyDAO = (UsePolicyDAO) ServiceUtil.appContext.getBean("usePolicyDAO");
		
		try {
			List<UsePolicyLite> usePolicyInfo = usePolicyDAO.listAllLite();

			usePoliciesDBIds = new ArrayList<Integer>();
			usePoliciesNames = new ArrayList<String>();
			
			for(UsePolicyLite usePolicyLite: usePolicyInfo){
				usePoliciesNames.add(usePolicyLite.getName());
				usePoliciesDBIds.add(usePolicyLite.getUsePolicyId());
	
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
			List<MediaCategoryLite> mediaCategoryInfo = mediaCategoryDAO.listAllLite();

			mediaCategoriesDBIds = new ArrayList<Integer>();
			mediaCategoriesNames = new ArrayList<String>();
			
			for(MediaCategoryLite mcl: mediaCategoryInfo){
				mediaCategoriesNames.add(mcl.getMediaCategoryName());
				mediaCategoriesDBIds.add(mcl.getMediaCategoryId());
	
			}

			logger.debug("getting Media Category Info...... is now on cache!.");
			mediaCategoriesInCache = true;

		} catch (Exception e) {
			logger.error("Something gets wrong on initMediaCategoriesInfo.");
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param categoryName
	 * @throws IllegalArgumentException
	 */
	public static void initMediaTypesInfo(String categoryName)
			throws IllegalArgumentException {

		logger.debug("getting Media Types Info... for category '"
				+ categoryName + "'.");

		MediaCategoryDAO mediaCategoryDAO = (MediaCategoryDAO) ServiceUtil.appContext.getBean("mediaCategoryDAO");
		MediaCategoryLite mediaCategoryLite = mediaCategoryDAO.getMediaCategoryLite(categoryName);
		MediaTypeDAO mediaTypeDAO = (MediaTypeDAO) ServiceUtil.appContext.getBean("mediaTypeDAO");
		List<MediaTypeLite> mediaTypeList = mediaTypeDAO.listAllForMediaCategoryLite(mediaCategoryLite.getMediaCategoryId());

		// If both queries go ok, then set the new iniformation in their
		// adecuate place
		mediaTypesNames = new ArrayList<String>();
		mediaTypesDBIds = new ArrayList<Integer>();
		
		for(MediaTypeLite mtl : mediaTypeList){
			mediaTypesNames.add(mtl.getMediaTypeName());
			mediaTypesDBIds.add(mtl.getMediaTypeId());
		}
		
		logger.debug("getting Media Types Info...  mediaTypes info for category '"
						+ categoryName + "' is now on cache!.");

		mediaType4Category = categoryName;
		mediaTypesInCache = true;
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
