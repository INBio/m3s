/**
 * 
 */
package org.inbio.m3s.gwt.client.rpcinterface;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author jgutierrez
 * 
 */
public interface InitServicesRPCAsync {
	
	//public void initHibernateConfig(AsyncCallback c);

	//public void isLoginValid(String u, String p, AsyncCallback c);

	public void initLogger(AsyncCallback c);
	
	public void InitServices(String moduleBaseURL, AsyncCallback c);
}
