/**
 * 
 */
package org.inbio.m3s.gwt.client.rpcinterface;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author jgutierrez
 *
 */
public interface InitServicesRPC extends RemoteService {

	/**
	 *@deprecated
	 */
	//public boolean initHibernateConfig();

	//public int isLoginValid(String username, String password);

	public boolean initLogger();
	
	public boolean InitServices(String moduleBaseURL);

}
