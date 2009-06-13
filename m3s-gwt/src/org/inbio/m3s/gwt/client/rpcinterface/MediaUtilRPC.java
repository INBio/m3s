/**
 * 
 */
package org.inbio.m3s.gwt.client.rpcinterface;

import org.inbio.m3s.gwt.client.exception.RPCIllegalArgumentException;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author jgutierrez
 * 
 */
public interface MediaUtilRPC extends RemoteService {

	public String createTempThumbnail(String fileId)
			throws RPCIllegalArgumentException;

	public void organizeAndCleanFiles(String tempFileId, String DBFileName,
			Integer mediaId) throws RPCIllegalArgumentException;

}
