/*
 * Login.java
 *
 * Created on February 7, 2007, 2:45 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.inbio.m3s.gwt.client.widgets.login.ui;

import org.inbio.m3s.gwt.client.widgets.login.listener.LoginBoxListener;

import com.google.gwt.user.client.ui.DialogBox;

/**
 * 
 * @author jgutierrez
 */
public class LoginBox extends DialogBox implements LoginBoxListener {

	private LoginPanel loginPanel = new LoginPanel(this);

	/**
	 * Login Constructor
	 * 
	 */
	public LoginBox() {
		setText("Ventana de registro");
		setWidget(loginPanel);
	}

	/**
	 * fires when the login panel fired the event
	 */
	public void closePanelHolder(String reason) {
		hide();
	}
}
