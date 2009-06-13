package org.inbio.m3s.gwt.client.widgets.login.ui;

import org.inbio.m3s.gwt.client.widgets.login.LoginManager;
import org.inbio.m3s.gwt.client.widgets.login.listener.LoginListener;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO: fix the languages, all the texts must be imported from a
 * internationalizarion file
 */
public class LoginBar extends Composite implements MouseListener,
		ClickListener, LoginListener {

	// the login controller
	private LoginManager loginManager;

	private DialogBox loginBox;

	private HorizontalPanel loginBar;

	private Label loginMessage;

	private String loginMessageText = "Iniciar Sesion";

	private Label username;

	private Label closeSession;

	private String closeSessionText = "Cerrar Sesion";

	public LoginBar(LoginManager login) {
		loginManager = login;
		loginManager.addLoginListener(this);
		loginBar = new HorizontalPanel();
		initLoginBar();
		initWidget(loginBar);
	}

	/**
	 * Creates and loads a Horizontal Panel for the login, and logout.
	 */
	private void initLoginBar() {
		cleanLoginBar();

		loginMessage = new Label(loginMessageText);
		loginMessage.setStyleName("LoginBar-LinkText");

		loginMessage.addMouseListener(this);
		loginMessage.addClickListener(this);

		loginBar.add(loginMessage);

	}

	private void cleanLoginBar() {
		int totalWidgets = loginBar.getWidgetCount();
		for (int i = 0; i < totalWidgets; i++)
			loginBar.remove(0);
	}

	private void UpdateLoginBar(String usrname) {

		cleanLoginBar();

		username = new Label(usrname);
		username.setStyleName("LoginBar-NormalText");

		closeSession = new Label(closeSessionText);
		closeSession.setStyleName("LoginBar-LinkText");

		closeSession.addClickListener(this);
		closeSession.addMouseListener(this);

		loginBar.add(username);
		loginBar.add(closeSession);

	}

	// mouseListener
	public void onMouseEnter(Widget sender) {
		sender.addStyleName("LoginBar-LinkText-MouseOver");
	}

	// mouseListener
	public void onMouseLeave(Widget sender) {
		sender.removeStyleName("LoginBar-LinkText-MouseOver");
	}

	// clickListener
	public void onClick(Widget sender) {

		if (sender == loginMessage) {
			loginBox = new LoginBox();

			int x = ((Label) sender).getAbsoluteLeft() - 300;
			int y = ((Label) sender).getAbsoluteTop() - 100;
			loginBox.setPopupPosition(x, y);

			loginBox.show();

		} else if (sender == closeSession) {
			LoginManager.logoutUser();
		}

	}

	// login Listener
	public void userLogin(String username) {
		UpdateLoginBar(username);
		if (loginBox != null) {
			loginBox.hide();
			loginBox = null;
		}
	}

	// login Listener
	public void userLogout() {
		initLoginBar();
	}

	// mouseListener
	public void onMouseDown(Widget sender, int x, int y) {
		;
	}

	// mouseListener
	public void onMouseMove(Widget sender, int x, int y) {
		;
	}

	// mouseListener
	public void onMouseUp(Widget sender, int x, int y) {
		;
	}

}
