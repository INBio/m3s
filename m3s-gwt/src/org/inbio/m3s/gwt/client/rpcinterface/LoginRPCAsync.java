/**
 * 
 */
package org.inbio.m3s.gwt.client.rpcinterface;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author jgutierrez
 * 
 */
public interface LoginRPCAsync {

	/**
	 * 
	 * @param userName
	 * @param password
	 * @retun true if its a valid user
	 */
	public void isValidUser(String userName, String password,
			AsyncCallback callback);

}
