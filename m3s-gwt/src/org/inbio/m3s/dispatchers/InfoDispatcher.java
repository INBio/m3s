/**
 * 
 */
package org.inbio.m3s.dispatchers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dao.core.MediaCategoryDAO;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dto.lite.MediaLite;
import org.inbio.m3s.dto.message.MediaCategoryDTO;
import org.inbio.m3s.dto.taxonomy.TaxonLiteDTO;
import org.inbio.m3s.service.MessageManager;
import org.inbio.m3s.service.TaxonomyManager;
import org.inbio.m3s.util.ServiceUtil;

/**
 * Dispatches information about the multimedia.
 * 
 * 
 * @author jgutierrez
 * @deprecated
 * 
 */
public class InfoDispatcher extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5706888888097730675L;

	/**
	 * Returns an XML with the information request (only if the media is public!).
	 * 
	 * The servlet receives a list of parameters in HttpServletRequest method
	 * parameter, the way each parameter is used will be explain here:
	 * 
	 * @param taxonomy
	 *          a string
	 * @param kingdom
	 *          a string
	 * 
	 * Example of use: 
	 * http://localhost:8080/m3s/getInfo?taxonomy=Ara ambigua&kingdom=Animalia
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		ServletOutputStream out = res.getOutputStream(); // binary output
		res.setContentType("text/xml");

		String taxonomy = req.getParameter("taxonomy");
		String kingdomName = req.getParameter("kingdom");

		TaxonomyManager taxonomyManager = (TaxonomyManager) ServiceUtil.appContext.getBean(Properties.TAXONOMY_MANAGER);
		MediaDAO mDAO = (MediaDAO) ServiceUtil.appContext.getBean("mediaDAO");
		MediaCategoryDAO mcDAO = (MediaCategoryDAO) ServiceUtil.appContext.getBean("mediaCategoryDAO");
		TaxonLiteDTO tl = null;
		List<MediaLite> mlList = new ArrayList<MediaLite>();
		MediaCategoryDTO mcl = null;

		try {
			// buscar los valores reales;
			if (kingdomName != null) {
				tl = taxonomyManager.getTaxonLite(taxonomy, kingdomName);
				mlList = mDAO.getMediaLiteForTaxonId(new Integer(tl.getTaxonKey()));

			} else {// sin kigdom... no recomendado
				List<MediaLite> mLiteList = null;
				List<TaxonLiteDTO> tlDTOList = taxonomyManager.getTaxonLite(taxonomy); 
				for (TaxonLiteDTO tlDTO : tlDTOList) {
					mLiteList = mDAO.getMediaLiteForTaxonId(new Integer(tlDTO.getTaxonKey()));
					mlList.addAll(mLiteList);
				}
			}
		} catch (IllegalArgumentException iae) {
			mlList = new ArrayList<MediaLite>();
		}

		// writting the XML file
		out.println("<?xml version=\"1.0\"?>");

		out.println("<multimedia taxonomy=\"" + taxonomy + "\" kingdom=\"" + kingdomName	+ "\">");

		for (MediaLite ml : mlList) {
			mcl = mcDAO.getMediaCategoryLiteFromMediaType(ml.getMediaTypeId(), MessageManager.ENGLISH);
			out.println("<media id=\"" + ml.getMediaId() + "\" category=\"" + mcl.getMediaCategoryKey()	+ "\"/>");
		}

		out.println("</multimedia>");

		out.flush();
		out.close();
	}



}
