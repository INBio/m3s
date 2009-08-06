package org.inbio.m3s.gwt.client.rpcinterface;

import java.util.List;

import org.inbio.m3s.gwt.client.widgets.importation.dto.ImportInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ImportRPCAsync {

	/**
	 * 
	 * @param userName
	 * @param callback
	 */
	public void getResultTableData(String userName, AsyncCallback<List<ImportInfo>> callback);

	/**
	 * 
	 * @param usernameImportOwner
	 * @param tempFileId
	 * @param callback
	 */
	public void executeImport(String usernameImportOwner, String tempFileId, AsyncCallback callback);

}
