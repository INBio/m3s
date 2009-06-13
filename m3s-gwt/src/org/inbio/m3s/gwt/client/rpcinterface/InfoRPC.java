/**
 * 
 */
package org.inbio.m3s.gwt.client.rpcinterface;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author jgutierrez
 * 
 */
public interface InfoRPC extends RemoteService {

	/**
	 * 
	 * 
	 */
	public Integer countMultimedia();

	public Integer countDigitalPhotos();
	
	public Integer countVideos();
	
}
