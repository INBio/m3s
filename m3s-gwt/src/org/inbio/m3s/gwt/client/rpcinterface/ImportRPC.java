/**
 * 
 */
package org.inbio.m3s.gwt.client.rpcinterface;

import java.util.List;

import org.inbio.m3s.gwt.client.widgets.importation.dto.ImportInfo;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author jgutierrez
 * 
 */
public interface ImportRPC extends RemoteService {

	/**
	 * 
	 * 
	 */
	public List<ImportInfo> getResultTableData(String userName);

	/**
	 * 
	 * @param usernameImportOwner
	 * @param tempFileId
	 */
	public void executeImport(String usernameImportOwner, String tempFileId);

}
