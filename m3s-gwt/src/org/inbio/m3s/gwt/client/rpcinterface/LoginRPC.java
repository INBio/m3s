/**
 * 
 */
package org.inbio.m3s.gwt.client.rpcinterface;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author jgutierrez
 * 
 */
public interface LoginRPC extends RemoteService {

	/**
	 * 
	 * @param userName
	 * @param password
	 * @retun true if its a valid user
	 */
	public Boolean isValidUser(String userName, String password);
}
