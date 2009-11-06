/**
 * 
 */
package org.inbio.m3s.service.autocomplete.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.TaxonDAO;
import org.inbio.m3s.model.taxonomy.Taxon;
import org.inbio.m3s.service.autocomplete.AutoCompleteManager;

/**
 * @author jgutierrez
 * 
 */
public class TaxonNameAutoCompleteManagerImpl implements AutoCompleteManager {

	protected static Log logger = LogFactory.getLog(TaxonNameAutoCompleteManagerImpl.class);
	
	// DAO's
	TaxonDAO taxonDAO;

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.AutoCompleteManager#getAutoCompleteOptions(java.lang.String)
	 */
	public Map<Integer, String> getAutoCompleteOptions(String value) {
		
		List<Taxon> tList = taxonDAO.findAllByPartialNamePaginated("%"+value+"%", 20);
		Map<Integer, String> results = new HashMap<Integer, String>();
		
		for(Taxon t: tList)
			results.put(t.getTaxonId(), t.getDefaultName());
		
		return results;
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


}
