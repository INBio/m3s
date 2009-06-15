/**
 * 
 */
package org.inbio.m3s.gwt.server.rpcimpl;

import org.apache.log4j.Logger;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.gwt.client.rpcinterface.LoginRPC;
import org.inbio.m3s.service.SecurityManager; 
import org.inbio.m3s.util.ServiceUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author jgutierrez
 * 
 */
public class LoginRPCImpl extends RemoteServiceServlet implements LoginRPC {

	private static Logger logger = Logger.getLogger(LoginRPCImpl.class);

	private static final long serialVersionUID = 1L;

	SecurityManager securityManager = (SecurityManager) ServiceUtil.appContext.getBean(Properties.SECURITY_MANAGER);

	public Boolean isValidUser(String userName, String password) {
		logger.info("se validó el usuario: "+securityManager.isValidUser(userName, password));
		return new Boolean(securityManager.isValidUser(userName, password));

	}

}
