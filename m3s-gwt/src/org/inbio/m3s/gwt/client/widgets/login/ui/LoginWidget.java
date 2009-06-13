package org.inbio.m3s.gwt.client.widgets.login.ui;

import org.inbio.m3s.gwt.client.widgets.login.LoginManager;
import org.inbio.m3s.gwt.client.widgets.login.listener.LoginBoxListener;
import org.inbio.m3s.gwt.client.widgets.login.listener.LoginListener;
import org.inbio.m3s.gwt.client.widgets.login.listener.LoginWidgetListener;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

public class LoginWidget extends Composite implements LoginListener,
		LoginBoxListener {

	// // the login controller
	private LoginManager loginManager;

	private LoginPanel loginPanel;

	private LoginWidgetListener loginWidgetListener;

	// username or user_DB_Id
	private Label widgetTitle;

	public LoginWidget(LoginManager login, LoginWidgetListener listener) {
		loginManager = login;
		loginManager.addLoginListener(this);
		loginWidgetListener = listener;
		widgetTitle = new Label("Registrarse:");
		widgetTitle.setStyleName("infoWidgets-Title");

		loginPanel = new LoginPanel(this);

		initWidget(loginPanel);
	}

	/**
	 * LoginManagerListener
	 * 
	 * Login manager is telling something...
	 * 
	 * @param userName
	 *            is the new user of the applcation
	 */
	public void userLogin(String username) {
		loginWidgetListener.closeWidget(username
				+ " has logged in using another way");

	}

	/**
	 * LoginManagerListener
	 * 
	 * Login manager is telling something...
	 */
	public void userLogout() {
		// TODO Auto-generated method stub
	}

	/**
	 * Login Panel fires this event when the user reaches max attempts for
	 * loggin or when the user decides to close tha panel
	 * 
	 */
	public void closePanelHolder(String reason) {
		loginWidgetListener.closeWidget(reason);
	}

}