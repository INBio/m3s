/**
 * 
 */
package org.inbio.m3s.gwt.server.rpcimpl;

import java.util.ArrayList;
import java.util.List;

import org.inbio.gwt.taxonomy.client.dto.TaxonInfo;
import org.inbio.gwt.taxonomy.client.events.TaxonomyRPCException;
import org.inbio.gwt.taxonomy.client.rpc.TaxonSelectorRPC;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.gwt.client.exception.RPCIllegalArgumentException;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.util.ServiceUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author jgutierrez
 *
 */
public class TaxonSelectorRPCImpl extends RemoteServiceServlet implements
		TaxonSelectorRPC {

	/**	 */
	private static final long serialVersionUID = 5121913669318916491L;

	/**
	 * @param taxonName
	 * @return a list of TaxonInfo objects
	 * @throws RPCIllegalArgumentException
	 *           if the databas query returns no results
	 */
	public List<TaxonInfo> getTaxonInfo(String taxonName)
			throws TaxonomyRPCException {

		List<TaxonInfo> result = new ArrayList<TaxonInfo>();
		TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER);
		List<TaxonLiteDTO> tlDTOList = null;
		TaxonInfo taxonInfo;

		// this list will contain elements as they are
		try {
			tlDTOList = taxonomyManager.getTaxonLite(taxonName);

			// rawElements = TaxonomyDAO.getTaxonsInfoByTaxonName(taxonName);
		} catch (IllegalArgumentException iae) {
			throw new TaxonomyRPCException(iae.getMessage());
		}

		if (tlDTOList.size() == 0) {
			throw new TaxonomyRPCException("El nombre de taxon '" + taxonName
					+ "' no existe. ");
		}

		TaxonLiteDTO kindomLiteDTO;
		for (TaxonLiteDTO tlDTO : tlDTOList) {
			kindomLiteDTO = taxonomyManager.getTaxonLiteById(tlDTO.getKingdomKey());
			taxonInfo = new TaxonInfo(tlDTO.getDefaultName(), kindomLiteDTO.getDefaultName(), tlDTO.getTaxonKey());
			result.add(taxonInfo);
		}

		return result;
	}

	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.gwt.taxonomy.client.rpc.TaxonSelectorRPC#getTaxonsInfo(java.util.List)
	 */
	public List<TaxonInfo> getTaxonsInfo(List<String> literalTaxonIds)
			throws TaxonomyRPCException {

		List<TaxonInfo> result = new ArrayList<TaxonInfo>();
		TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER);
		TaxonLiteDTO tlDTO = null;
		TaxonInfo taxonInfo;
		TaxonLiteDTO kindomLiteDTO;

		if(literalTaxonIds!=null){
			for(String taxonKey : literalTaxonIds){
				try {
					tlDTO = taxonomyManager.getTaxonLiteById(taxonKey);
				} catch (IllegalArgumentException iae) {
					throw new TaxonomyRPCException(iae.getMessage());
				}
	
				// String taxonName, String kingdomName, String taxonId
				kindomLiteDTO = taxonomyManager.getTaxonLiteById(tlDTO.getKingdomKey());
				taxonInfo = new TaxonInfo(tlDTO.getDefaultName(), kindomLiteDTO.getDefaultName(), 
						tlDTO.getTaxonKey());
	
				result.add(taxonInfo);
	
			}
		}

		return result;

	}
}
