/**
 * 
 */
package org.inbio.m3s.gwt.server.rpcimpl;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.gwt.client.rpcinterface.InitServicesRPC;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author jgutierrez
 *
 */
public class InitServicesRPCImpl extends RemoteServiceServlet implements
		InitServicesRPC {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6476373139399874319L;
	
	private static Logger logger = Logger.getLogger(InitServicesRPCImpl.class);

	/**
	 * Creates a new instance of RPCManagerRPCImpl
	 */
	public boolean InitServices(String moduleBaseURL) {
		
		try {
			Properties.init();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		Properties.MODULE_BASE_URL = moduleBaseURL;
		 logger.debug("init de module base URL done");
		
		return true;
	}


	/**
	 * Initialize the log4j logger
	 * 
	 * @return true if everything goes cool
	 */
	public boolean initLogger() {

		BasicConfigurator.configure();
		
		return true;

	}

}
