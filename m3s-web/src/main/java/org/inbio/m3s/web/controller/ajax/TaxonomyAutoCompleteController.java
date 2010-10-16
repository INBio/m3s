/**
 * 
 */
package org.inbio.m3s.web.controller.ajax;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.dto.taxonomy.util.TaxonomicalRangeEntity;
import org.inbio.m3s.service.TaxonomyManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author jgutierrez
 *
 */
public class TaxonomyAutoCompleteController extends MultiActionController{
	
	private TaxonomyManager taxonomyManager;
	/* For the yahoo auto complete javascript this value always is = "query"*/
	private String queryParam = "query";
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView taxonsByKingdom(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String param = request.getParameter(queryParam);		
		String errorMsj = "No se puede obtener los reinos para: " + param + ".";
		List<TaxonLiteDTO> tlDTOList = new ArrayList<TaxonLiteDTO>();
		

		try {
			tlDTOList = taxonomyManager.getTaxonsByPatialNameAndTaxonomicalRange(param,TaxonomicalRangeEntity.KINGDOM);
			return writeReponse(request,response, tlDTOList);

			
		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView taxonsByFamily(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String param = request.getParameter(queryParam);		
		String errorMsj = "No se puede obtener las familias para: " + param + ".";
		List<TaxonLiteDTO> tlDTOList = new ArrayList<TaxonLiteDTO>();
		

		try {
			tlDTOList = taxonomyManager.getTaxonsByPatialNameAndTaxonomicalRange(param,TaxonomicalRangeEntity.FAMILY);
			return writeReponse(request,response, tlDTOList);

			
		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	
	}	

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView taxonsByGenus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String param = request.getParameter(queryParam);		
		String errorMsj = "No se puede obtener los generos para: " + param + ".";
		List<TaxonLiteDTO> tlDTOList = new ArrayList<TaxonLiteDTO>();
		

		try {
			tlDTOList = taxonomyManager.getTaxonsByPatialNameAndTaxonomicalRange(param,TaxonomicalRangeEntity.GENUS);
			return writeReponse(request,response, tlDTOList);

			
		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	
	}		
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView taxonsBySpecies(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String param = request.getParameter(queryParam);		
		String errorMsj = "No se puede obtener las especies para: " + param + ".";
		List<TaxonLiteDTO> tlDTOList = new ArrayList<TaxonLiteDTO>();
		

		try {
			tlDTOList = taxonomyManager.getTaxonsByPatialNameAndTaxonomicalRange(param,TaxonomicalRangeEntity.SPECIES);
			return writeReponse(request,response, tlDTOList);

			
		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ModelAndView taxonsByOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String param = request.getParameter(queryParam);		
		String errorMsj = "No se puede obtener los ordenes para: " + param + ".";
		List<TaxonLiteDTO> tlDTOList = new ArrayList<TaxonLiteDTO>();
		

		try {
			tlDTOList = taxonomyManager.getTaxonsByPatialNameAndTaxonomicalRange(param,TaxonomicalRangeEntity.ORDER);
			return writeReponse(request,response, tlDTOList);

			
		} catch (IllegalArgumentException iae) {
			throw new Exception(errorMsj + " "+ iae.getMessage());
		}
	
	}
	
	
	/**
	 * Writes the response in the output!.
	 * 
	 * @param request
	 * @param response
	 * @param tlDTOList
	 * @return
	 * @throws Exception
	 * @deprecated
	 */
	private ModelAndView writeReponse(HttpServletRequest request,
			HttpServletResponse response, List<TaxonLiteDTO> tlDTOList) throws Exception {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		ServletOutputStream out = response.getOutputStream(); // binary output
		
		if(tlDTOList!=null){
			for (TaxonLiteDTO tlDTO : tlDTOList)
				out.println(tlDTO.getDefaultName()+"\t"+tlDTO.getTaxonKey());
		}
		
		out.flush();
		out.close();
		
		return null;
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

}
