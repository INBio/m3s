/**
 * 
 */
package org.inbio.m3s.gwt.client.rpcinterface;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author jgutierrez
 * 
 */
public interface MediaUtilRPCAsync {

	public void createTempThumbnail(String fileId, AsyncCallback callback);

	public void organizeAndCleanFiles(String tempFileId, String DBFileName,
			Integer mediaId, AsyncCallback callback);

}
