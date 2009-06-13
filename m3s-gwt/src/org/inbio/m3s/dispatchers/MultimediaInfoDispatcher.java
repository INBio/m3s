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

import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dto.lite.MediaLite;
import org.inbio.m3s.gwt.client.widgets.galleries.SearchCriteriaTriplet;
import org.inbio.m3s.manager.SearchManager;
import org.inbio.m3s.util.ServiceUtil;

/**
 * Dispatches information about the multimedia.
 * 
 * 
 * @author jgutierrez
 * 
 */
public class MultimediaInfoDispatcher extends HttpServlet {

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

		Integer searchFilterId = Integer.valueOf(req.getParameter("filter"));
		Integer searchCriteriaId = Integer.valueOf(req.getParameter("criteria"));
		String value = req.getParameter("value"); 
		Integer first = Integer.valueOf(req.getParameter("first"));
		Integer last = Integer.valueOf(req.getParameter("last"));
		
		SearchCriteriaTriplet scTriplet = new SearchCriteriaTriplet(searchFilterId,searchCriteriaId,value);
		List<SearchCriteriaTriplet> sctList = new ArrayList<SearchCriteriaTriplet>();
		sctList.add(scTriplet);
		
		int totalResults = SearchManager.getTotalResults(sctList);
		List<Integer> mediaIdsList = SearchManager.getResults(sctList, 1, totalResults);		
		MediaDAO mDAO = (MediaDAO) ServiceUtil.appContext.getBean("mediaDAO");
		MediaLite ml = null;

		// writting the XML file
		out.println("<?xml version=\"1.0\"?>");

		out.println("<multimedia results=\"" + totalResults + "\" first=\"" + first	+ "\" last=\"" + last + "\">");

		for (Integer mediaId : mediaIdsList) {
			ml = mDAO.getMediaLite(mediaId);
			out.println("<media url=\""+getImageURL("thumb", ml.getMediaId().toString())+"\" title=\"" + ml.getTitle() + "\" author=\"" + ml.getAuthorPersonId()	+ "\" usePolicy=\"" + ml.getUsePolicyId()	+ "\"/>");
		}

		out.println("</multimedia>");

		out.flush();
		out.close();
	}


	
	private String getImageURL(String imageSize, String imageId){
		return "http://coffea:8686/m3sINBio/getImage?id="+imageId+"&size="+imageSize;
	}

}
