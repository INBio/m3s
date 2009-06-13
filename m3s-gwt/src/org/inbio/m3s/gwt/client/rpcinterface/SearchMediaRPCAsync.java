/**
 * 
 */
package org.inbio.m3s.gwt.client.rpcinterface;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author jgutierrez
 * 
 */
public interface SearchMediaRPCAsync {

	/**
	 * 
	 * @param quantity
	 */
	public void getLastPublicMedia(int quantity, AsyncCallback callback);

	/**
	 * 
	 * @param mediaId
	 * @param callback
	 * @deprecated: must be checked if its ok!
	 */
	public void getThumbInfo(Integer mediaId, AsyncCallback callback);

	/**
	 * 
	 * @param mediaId
	 * @param callback
	 * @deprecated: must be checked if its ok!
	 */
	public void getBigInfo(Integer mediaId, AsyncCallback callback);

	/**
	 * 
	 * @param querySummary
	 * @param callback
	 * @deprecated: must be checked if its ok!
	 */
	public void getTotalResults(List querySummary, AsyncCallback callback);

	/**
	 * 
	 * @param querySummary
	 * @param first
	 * @param quantity
	 * @deprecated: must be checked if its ok!
	 */
	public void getResults(List querySummary, int first, int quantity,
			AsyncCallback callback);

}
