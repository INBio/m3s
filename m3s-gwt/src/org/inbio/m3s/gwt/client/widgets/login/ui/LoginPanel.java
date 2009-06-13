package org.inbio.m3s.gwt.client.widgets.login.ui;

import org.inbio.m3s.gwt.client.widgets.login.LoginManager;
import org.inbio.m3s.gwt.client.widgets.login.listener.LoginBoxListener;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Login panel is the core of the login widget. Is the basic graphical
 * representation where the user types he's/she's userName and Password. This
 * panel sends the info to the Login class to check if the user and password
 * match.
 * 
 * @author james
 */
public class LoginPanel extends DockPanel implements KeyboardListener {

	private static final int HORIZONTAL_SIZE = 300;

	private static final int VERTICAL_SIZE = 100;

	private static final int MAX_ATTEMPTS = 3;

	private int attempts = 1;

	private Label title;

	private Label usrText;

	private TextBox usr;

	private Label pwdText;

	private PasswordTextBox pwd;

	private Button sendButton;

	private Button closeButton;

	private Label bottomText;

	private LoginBoxListener listener;

	/**
	 * Login Constructor
	 * 
	 * TODO: the internationalization info info must be loaded from a config
	 * file.
	 */
	public LoginPanel(LoginBoxListener loginBoxListener) {
		listener = loginBoxListener;
		initGUI();
	}

	/**
	 * Inits the panel
	 */
	private void initGUI() {

		title = new Label("Iniciar Session");
		title.setStyleName("loginTitle");

		usrText = new Label("Nombre de Usuario:");
		usrText.setStyleName("login-Label");

		usr = new TextBox();
		usr.setStyleName("login-TextBox");

		pwdText = new Label("Contrase\u00F1a");
		pwdText.setStyleName("login-Label");

		pwd = new PasswordTextBox();
		pwd.setStyleName("login-TextBox");
		sendButton = new Button("Login", new ClickListener() {
			public void onClick(Widget sender) {
				checkInfo();
			}
		});
		sendButton.setStyleName("login-Button");

		closeButton = new Button("Cerrar", new ClickListener() {
			public void onClick(Widget sender) {
				listener.closePanelHolder("user click close button");
			}
		});
		// sendButton.setStyleName("login-Button");

		bottomText = new Label("");
		bottomText.setStyleName("loginBottomMessages");

		setStyleName("LoginBox-Panel");

		setSpacing(8);
		add(usrText, DockPanel.NORTH);
		setSpacing(4);
		add(usr, DockPanel.NORTH);

		setSpacing(8);
		add(pwdText, DockPanel.NORTH);
		setSpacing(4);
		add(pwd, DockPanel.NORTH);

		setSpacing(8);
		add(closeButton, DockPanel.SOUTH);
		setSpacing(6);
		add(bottomText, DockPanel.SOUTH);
		add(sendButton, DockPanel.SOUTH);

		setCellHorizontalAlignment(usrText, DockPanel.ALIGN_LEFT);
		setCellHorizontalAlignment(usr, DockPanel.ALIGN_RIGHT);
		setCellHorizontalAlignment(pwdText, DockPanel.ALIGN_LEFT);
		setCellHorizontalAlignment(pwd, DockPanel.ALIGN_RIGHT);
		setCellHorizontalAlignment(sendButton, DockPanel.ALIGN_CENTER);
		setCellHorizontalAlignment(bottomText, DockPanel.ALIGN_CENTER);
		setCellHorizontalAlignment(closeButton, DockPanel.ALIGN_RIGHT);

		setWidth("100%");
		setPixelSize(HORIZONTAL_SIZE, VERTICAL_SIZE);

		usr.addKeyboardListener(this);
		pwd.addKeyboardListener(this);

	}

	// KeyboardListener
	public void onKeyPress(Widget sender, char keyCode, int modifiers) {

		if (keyCode == KeyboardListener.KEY_TAB) {
			if (sender.equals(usr)) {
				pwd.setFocus(true);
			}
		} else if (keyCode == KeyboardListener.KEY_ENTER) {
			sendButton.click();
		}
	}

	// KeyboardListener
	public void onKeyDown(Widget sender, char keyCode, int modifiers) {
		// ignore
		;
	}

	// KeyboardListener
	public void onKeyUp(Widget sender, char keyCode, int modifiers) {
		// ignore
		;
	}

	/**
	 * Cleand the username and password textboxes
	 * 
	 */
	private void cleanTextBoxes() {
		((TextBox) usr).setText("");
		((PasswordTextBox) pwd).setText("");
	}

	/**
	 * Sets a message for the Bottom Text Line
	 * 
	 * @param text
	 */
	private void setBottomText(String text) {
		((Label) bottomText).setText(text);
	}

	private void checkInfo() {
		if (attempts + 1 <= MAX_ATTEMPTS) {
			attempts = attempts + 1;

			// asks in a strange way because the method that validates is async
			LoginManager.isValid(usr.getText(), pwd.getText(), this);

		} else {
			listener.closePanelHolder("user reached max atempts for login");
		}
	}

	public void loginIsNotValid() {
		cleanTextBoxes();
		setBottomText("nombre de usuario y/o contraseña invalidos");
	}
}
