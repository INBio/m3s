/*
 * InsertImagesRPC.java
 *
 * Created on October 31, 2006, 3:38 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.inbio.m3s.gwt.client.rpcinterface;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * 
 * @author james
 */
public interface InsertImagesRPC extends RemoteService {

	public boolean hasThumbnail(String fileId);

	/**
	 *  
	 * 
	 */
	public int storeImage(String fileId, List generalMetadataValues,
			List usesAndCopyrightsValues, List specificMetadataValues);

}
