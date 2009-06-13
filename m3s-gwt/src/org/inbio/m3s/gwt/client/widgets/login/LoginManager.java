package org.inbio.m3s.gwt.client.widgets.login;

import org.inbio.m3s.gwt.client.rpcinterface.LoginRPC;
import org.inbio.m3s.gwt.client.rpcinterface.LoginRPCAsync;
import org.inbio.m3s.gwt.client.widgets.login.listener.LoginListener;
import org.inbio.m3s.gwt.client.widgets.login.listener.LoginListenerCollection;
import org.inbio.m3s.gwt.client.widgets.login.ui.LoginPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * Controls all the login stuff for the client side of the application
 * 
 * @author jgutierrez
 *
 * 
 */
public class LoginManager {

	// rpc service
	private static LoginRPCAsync rpc;

	private static LoginPanel asking;

	private static String askingUserName;

	private static LoginListenerCollection loginListeners;

	private static String username = null;

	/**
	 * Basic Class Constructor
	 */
	public LoginManager() {
		initRPC();
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isUserLogged() {
		return (username != null);
	}

	/**
	 * When a user log's out
	 * 
	 */
	public static void logoutUser() {
		username = null;
		if (loginListeners != null) {
			loginListeners.userLogout();
		}
	}

	public static String getUserName() {
		return username;
	}

	/**
	 * Is Valid... determines if the userName and the Password are Ok!
	 * 
	 * @param usrName
	 *            user Name
	 * @param password
	 * 
	 * 
	 * @return true is are ok, false otherwise. to be implemented on the other
	 *         side
	 */
	public static void isValid(String usrName, String password,
			LoginPanel emiter) {
		// TODO
		asking = emiter;
		askingUserName = usrName;
		rpc.isValidUser(usrName, password, new AsyncCallback() {

			public void onFailure(Throwable caught) {
				asking.loginIsNotValid();

			}

			public void onSuccess(Object result) {
				if (((Boolean) result).booleanValue()) {
					username = askingUserName;
					loginListeners.userLogin(username);
				} else {
					asking.loginIsNotValid();
				}
			}

		});
	}


	/**
	 * 
	 * @param listener
	 */
	@SuppressWarnings("unchecked")
	public void addLoginListener(LoginListener listener) {
		if (loginListeners == null) {
			loginListeners = new LoginListenerCollection();
		}
		loginListeners.add(listener);
	}

	public void removeLoginListener(LoginListener listener) {
		if (loginListeners != null) {
			loginListeners.remove(listener);
		}
	}

	/**
	 * Init the RPC that all the class use
	 * 
	 */
	private void initRPC() {
		// (1) Initialize the RPC service.
		rpc = (LoginRPCAsync) GWT.create(LoginRPC.class);

		// (2) Specify the URL at which our service implementation is running.
		// Note that the target URL must reside on the same domain and port from
		// which the host page was served.
		ServiceDefTarget endpoint = (ServiceDefTarget) rpc;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "loginRPC";
		endpoint.setServiceEntryPoint(moduleRelativeURL);
	}

}
