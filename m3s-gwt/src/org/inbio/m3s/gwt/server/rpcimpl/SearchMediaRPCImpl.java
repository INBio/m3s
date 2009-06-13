/**
 * 
 */
package org.inbio.m3s.gwt.server.rpcimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.gwt.galleries.client.dto.DisplayInfo;
import org.inbio.m3s.gwt.client.exception.RPCIllegalArgumentException;
import org.inbio.m3s.gwt.client.rpcinterface.SearchMediaRPC;
import org.inbio.m3s.gwt.client.widgets.galleries.SearchCriteriaTriplet;
import org.inbio.m3s.gwt.server.factories.DisplayInfoFactory;
import org.inbio.m3s.manager.SearchManager;
import org.inbio.m3s.util.ServiceUtil;
import org.inbio.m3s.dao.core.MediaDAO;
import org.inbio.m3s.dto.lite.MediaLite;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author jgutierrez
 * 
 */
public class SearchMediaRPCImpl extends RemoteServiceServlet implements
		SearchMediaRPC {

	private static final long serialVersionUID = -5281193521083493788L;
	
	private static Logger logger = Logger.getLogger(SearchMediaRPCImpl.class);

	/**
	 * 
	 */
	public List<DisplayInfo> getLastPublicMedia(int quantity)
			throws RPCIllegalArgumentException {

		logger.debug("getLastPublicMedia");
		System.out.println("getLastPublicMedia");
		
		MediaDAO mediaDAO = (MediaDAO) ServiceUtil.appContext.getBean("mediaDAO");
		List<MediaLite> mediaLiteList = mediaDAO.getLastPublicMedia(quantity);
		System.out.println("Cantidad de resulatados> "+mediaLiteList.size());
		
		return DisplayInfoFactory.createDisplayInfoList(mediaLiteList);
		
	}

	/**
	 * @param searchCriteria
	 *          with a list of SearchCriteriaTriplet's
	 * 
	 * @return the total of results gotten
	 * @deprecated must be checked if its ok!
	 */
	public Integer getTotalResults(List<SearchCriteriaTriplet> searchCriteria)
			throws RPCIllegalArgumentException {
		logger.debug("numero de tripletas "	+ searchCriteria.size());
		try {
			return SearchManager.getTotalResults(searchCriteria);
		} catch (Exception e) {
			throw new RPCIllegalArgumentException(e.getMessage());
		}

	}

	/**
	 * Gets a subset of the results of a given query
	 * 
	 * @param searchCriteria
	 * @param first
	 * @param quantity
	 * @return a list of DisplayInfoBrief
	 * @throws RPCIllegalArgumentException
	 * @deprecated must be checked if its ok!
	 */
	@SuppressWarnings("unchecked")
	public List<DisplayInfo> getResults(List searchCriteria, int first, int quantity)
			throws RPCIllegalArgumentException {

		try {
			
			List<Integer> mediaIds = SearchManager.getResults(searchCriteria, first, quantity);
			
			MediaDAO mediaDAO = (MediaDAO) ServiceUtil.appContext.getBean("mediaDAO");
			List<MediaLite> mediaLiteList = new ArrayList<MediaLite>();
			
			for(Integer mediaId : mediaIds)
				mediaLiteList.add(mediaDAO.getMediaLite(mediaId));

			return DisplayInfoFactory.createDisplayInfoList(mediaLiteList);
			//return (List) SearchManager.getResults(searchCriteria, first, quantity);

		} catch (Exception e) {
			throw new RPCIllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param mediaId
	 * @return a List with the neded info for creating a simple thumb
	 * @deprecated must be checked if its ok!
	 */
	public List<String> getThumbInfo(Integer mediaId) {
		List<String> result = new ArrayList<String>();
		MediaDAO mediaDAO = (MediaDAO) ServiceUtil.appContext.getBean("mediaDAO");
		MediaLite mediaLite = mediaDAO.getMediaLite(mediaId);

		// TODO
		// en pos 0: URL
		// result.add(ImageDispatcher.getImageAddress(mediaId,
		// ImageDispatcher.THUMP_IMAGE));
		result.add(GWT.getModuleBaseURL()
				+ "getImage?size=thumb&link=false&id=" + mediaId);

		// en pos 1: media Title
		//result.add(MultimediaDAO.getTitle(mediaId));
		result.add(mediaLite.getTitle());

		// en pos3: media Description
		//result.add(MultimediaDAO.getDescription(mediaId));
		result.add(mediaLite.getDescription());

		return result;
	}

	/**
	 * 
	 * @param mediaId
	 * @return a List with the neded info for creating a simple big
	 * @deprecated must be checked if its ok!
	 */
	public List<String> getBigInfo(Integer mediaId) {
		List<String> result = new ArrayList<String>();
		MediaDAO mediaDAO = (MediaDAO) ServiceUtil.appContext.getBean("mediaDAO");
		MediaLite mediaLite = mediaDAO.getMediaLite(mediaId);
		
		// TODO
		// en pos 0: URL
		result.add(GWT.getModuleBaseURL() + "getImage?size=big&link=false&id="
				+ mediaId);

		// en pos 1: media Title
		//result.add(MultimediaDAO.getTitle(mediaId));
		result.add(mediaLite.getTitle());
		
		// en pos3: media Description
		//result.add(MultimediaDAO.getDescription(mediaId));
		result.add(mediaLite.getDescription());

		return result;
	}

}
