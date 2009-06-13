/**
 * 
 */
package org.inbio.m3s.gwt.client.rpcinterface;

import java.util.List;

import org.inbio.gwt.galleries.client.dto.DisplayInfo;
import org.inbio.m3s.gwt.client.exception.RPCIllegalArgumentException;
import org.inbio.m3s.gwt.client.widgets.galleries.SearchCriteriaTriplet;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author jgutierrez
 * 
 */
public interface SearchMediaRPC extends RemoteService {

	/**
	 * 
	 * @param quantity
	 * @throws RPCIllegalArgumentException
	 */
	public List<DisplayInfo> getLastPublicMedia(int quantity)
			throws RPCIllegalArgumentException;

	
	
	/**
	 * 
	 * @param querySummary
	 *               
	 * @throws RPCIllegalArgumentException
	 * @deprecated: must be checked if its ok!
	 */
	public Integer getTotalResults(List<SearchCriteriaTriplet> querySummary)
			throws RPCIllegalArgumentException;

	/**
	 * 
	 * @param querySummary
	 * 
	 * @param first
	 * @param quantity
	 * 
	 * @throws RPCIllegalArgumentException
	 * @deprecated: must be checked if its ok!
	 */
	public List<DisplayInfo> getResults(List<SearchCriteriaTriplet> querySummary, int first, int quantity)
			throws RPCIllegalArgumentException;

	/**
	 * 
	 * 
	 */
	public List<String> getThumbInfo(Integer mediaId);

	/**
	 * 
	 * 
	 * @deprecated: must be checked if its ok!
	 */
	public List<String> getBigInfo(Integer mediaId);

}
