/**
 * 
 */
package org.inbio.m3s.gwt.server.rpcimpl;

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
		
		Properties.MODULE_BASE_URL = moduleBaseURL;
		 logger.debug("init de module base URL done");
		
		return true;
	}

	/**
	 * Initialize the Hibernate Configuration and the session factory variable for
	 * future use in tha application
	 * @deprecated
	 * @return true if everything goes cool
	 */
	public boolean initHibernateConfig() {
		logger.debug("initHibernateConfig");
		System.out.println("initHibernateConfig");
		boolean result = true;
		// inits the sessin factory variable for the application use...

		// inits the local MultiMedia Management Data Base
		//if (Properties.POSTGRES_DB_CONECTION_AVALIABLE) {
			//System.out.println("si postgres");
			// DB Local
			//result = HibernateUtil.buildM3SDBSessionFactory(Properties.M3S_HIBERNATE_CONFIG_FILE);
			//logger.debug("Postgres bien? = "+result);
			//System.out.println("fin si postgres");
		//}
		

		// inits the local atta Databa
		//if (Properties.ORACLE_DB_CONECTION_AVALIABLE) {
			//System.out.println("si oracle");
			// atta
			 //result = HibernateUtil.buildATTADBSessionFactory(Properties.ATTA_HIBERNATE_CONFIG_FILE);
			 //logger.debug("Oracle bien? = "+result);
			 //System.out.println("fin si oracle");
		//}
		
		//if(Properties.ARA_DB_CONECTION_AVALIABLE) {
			//System.out.println("si ara");
			//ara
			//result = HibernateUtil.buildARADBSessionFactory(Properties.ARA_HIBERNATE_CONFIG_FILE);
			//logger.debug("ARA bien? = "+ result);
			//System.out.println("fin si ara");
		//}
		
		
		//logger.debug("initSessionFactory... PURA VIDA!");

		return result;
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
