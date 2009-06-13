/*
 * InsertImagesRPCAsync.java
 *
 * Created on October 31, 2006, 3:44 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.inbio.m3s.gwt.client.rpcinterface;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author james
 */
public interface InsertImagesRPCAsync {
    
    public void hasThumbnail(String fileId, AsyncCallback callback);
    
    public void storeImage(String s, List generalMetadataValues, 
            List usesAndCopyrightsValues, List specificMetadataValues, 
            AsyncCallback callback);
}
