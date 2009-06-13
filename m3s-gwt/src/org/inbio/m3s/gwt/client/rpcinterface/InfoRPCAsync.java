/**
 * 
 */
package org.inbio.m3s.gwt.client.rpcinterface;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author jgutierrez
 * 
 */
public interface InfoRPCAsync {

	/**
	 * 
	 */
	public void countMultimedia(AsyncCallback callback);

	public void countDigitalPhotos(AsyncCallback callback);
	
	public void countVideos(AsyncCallback callback);
}
