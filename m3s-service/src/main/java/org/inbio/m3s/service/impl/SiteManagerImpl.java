/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.dao.core.SiteDAO;
import org.inbio.m3s.dto.taxonomy.SpecimenLiteDTO;
import org.inbio.m3s.service.SiteManager;
import org.inbio.m3s.service.TaxonomyManager;

/**
 * @author jgutierrez
 *
 */
public class SiteManagerImpl implements SiteManager {
	
	TaxonomyManager taxonomyManager;
	SiteDAO siteDAO;

	private static Logger logger = Logger.getLogger(SiteManagerImpl.class);
	

	/* (non-Javadoc)
	 * @see org.inbio.m3s.service.SiteManager#getSiteFromGatheringCode(java.lang.String)
	 */
	public String getSiteFromGatheringCode(String code) throws IllegalArgumentException {
		logger.debug("getSiteFromGatheringCode... start");
		String errorMsj = "No se enuentra ningúna descripcion de sitio para la recolecta #"
				+ code + ".";
		try {
			
			List<SpecimenLiteDTO> slDTOList = taxonomyManager.getSpecimenLiteForGatheringCode(code);
			SpecimenLiteDTO slDTO = slDTOList.get(0);
		
			return siteDAO.getSiteDBIdFromSpecimenNumber(new Integer(slDTO.getSpecimenKey()));

		} catch (Exception e) {
			logger.error(errorMsj);
			logger.error(e.getMessage());
			throw new IllegalArgumentException(errorMsj);
		}
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
	 * @return the siteDAO
	 */
	public SiteDAO getSiteDAO() {
		return siteDAO;
	}


	/**
	 * @param siteDAO the siteDAO to set
	 */
	public void setSiteDAO(SiteDAO siteDAO) {
		this.siteDAO = siteDAO;
	}

}
