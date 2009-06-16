package org.inbio.m3s.gwt.client;

import java.util.Iterator;

import org.inbio.m3s.gwt.client.rpcinterface.InitServicesRPC;
import org.inbio.m3s.gwt.client.rpcinterface.InitServicesRPCAsync;
import org.inbio.m3s.gwt.client.widgets.login.LoginManager;
import org.inbio.m3s.gwt.client.widgets.login.listener.LoginListener;
import org.inbio.m3s.gwt.client.widgets.login.ui.LoginBar;
import org.inbio.m3s.gwt.client.widgets.verticalmenu.VerticalMenuBar;
import org.inbio.m3s.gwt.client.widgets.verticalmenu.listener.MenuItemSelectedListener;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Index implements EntryPoint, MenuItemSelectedListener, LoginListener {

	private InitServicesRPCAsync rpc;

	HorizontalPanel upperBar;

	VerticalMenuBar menuBar;

	// Login stuff
	LoginManager loginManager = new LoginManager();

	// Elements of the Main Panel
	Home home;

	// InsertImages insertImages;
	InsertMedia insertMedia;

	ImportMedia importImages;

	SearchMedia searchImages;

	EditMediaInfo editMediaInfo;

	VerticalPanel initPanel;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		// inits the rpc service
		rpc = (InitServicesRPCAsync) GWT.create(InitServicesRPC.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) rpc;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "RPCManager");

		
		// inits hibernate,
		/*
		rpc.initHibernateConfig(new AsyncCallback() {
			public void onFailure(Throwable caught) {
				rpcError();
			}

			public void onSuccess(Object result) {
				hibernateReady();
			}
		});
		*/

		// sets the verticapl panel and frontal panel widgets
		initPanel = new VerticalPanel();
		initPanel.add(new Label("Inicializando la aplicación"));
		initPanel.add(new Image("images/loading.gif"));
		RootPanel.get("MainPanel").add(initPanel);

		// inits logger
		// FIXME y TODO: revisar como inicializan el logger en hibernate, pues
		// tomcat da error por no encontrar el archivo.
		rpc.initLogger(new AsyncCallback() {
			public void onFailure(Throwable caught) {
				rpcError();
			}

			public void onSuccess(Object result) {
				// TODO
			}
		});
		System.out.println("SETEANDO LAS COSAS");
		rpc.InitServices(GWT.getModuleBaseURL(), new AsyncCallback() {
			public void onFailure(Throwable caught) {
				rpcError();
			}
			public void onSuccess(Object result) {
			}
		});
		
		loginManager.addLoginListener(this);
		hibernateReady();				
	}

	/**
	 * This method inits the menu bar, is very important to remember the order
	 * of the item, because in the itemSelected method, the itemIdex received as
	 * parameter indicates the item that was click by the index it was added
	 * 
	 * @see itemSelected method
	 */
	private void intMenuBar() {
		menuBar.addItem("Inicio"); // 0
		menuBar.addItem("Insertar Im\u00E1genes"); // 1
		menuBar.addItem("Importar Grupo de Im\u00E1genes"); // 2
		menuBar.addItem("Busqueda de Im\u00E1genes"); // 3
		menuBar.addItem("Editar Información de Multimedio"); // 4

	}
	

	/**
	 * Handles the event of a item of the menu clicked. The index received as
	 * parameter is relative to the order in where the items where added to the
	 * menu.
	 * 
	 * @param itemIndex
	 *            index of the item of the menu that was clicked.
	 * @see initMenuBar method
	 */
	public void itemSelected(int itemIndex) {

		if (!LoginManager.isUserLogged()) {
			Window.alert("Debe iniciar sesion en el sistema."
					+ "Contacte con el administrador para obtener una contraseña");
			return;
		}
		
		// removes actual items of the MainPanel
		Iterator iterator = RootPanel.get("MainPanel").iterator();
		while (iterator.hasNext()) {
			iterator.next();
		}
		iterator.remove();

		// finds what element should be in the main panel
		switch (itemIndex) {
		case 0: // Shows the home in the Main Panel
			if (home == null) {
				home = new Home(loginManager);
			}
			RootPanel.get("MainPanel").add(home);
			break;
		case 1:
			if (insertMedia == null|| insertMedia.getUsername().compareTo(LoginManager.getUserName()) != 0) {
				insertMedia = new InsertMedia();
			}
			RootPanel.get("MainPanel").add(insertMedia);
			break;
		case 2:
			if (importImages == null|| importImages.getUsername().compareTo(LoginManager.getUserName()) != 0) {
				importImages = new ImportMedia();
			}
			RootPanel.get("MainPanel").add(importImages);
			break;
		case 3:
			if (searchImages == null) {
				searchImages = new SearchMedia();
			}
			RootPanel.get("MainPanel").add(searchImages);
			break;
		case 4:
			if (editMediaInfo == null|| editMediaInfo.getUsername().compareTo(LoginManager.getUserName()) != 0) {
				editMediaInfo = new EditMediaInfo();
			}
			RootPanel.get("MainPanel").add(editMediaInfo);
			break;
		}


	}

	private void initUpperBar() {
		LoginBar loginBar = new LoginBar(loginManager);

		upperBar.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		upperBar.setVerticalAlignment(HorizontalPanel.ALIGN_BOTTOM);
		upperBar.setStyleName("UpperBar");
		upperBar.add(loginBar);

	}

	/***************************************************************************
	 * RPC METHODS
	 **************************************************************************/
	private void rpcError() {
		Window.alert("error conectando con el servidor");
	}

	private void hibernateReady() {
		// closes the init panel
		initPanel.removeFromParent();

		// login bar
		upperBar = new HorizontalPanel();
		initUpperBar();
		RootPanel.get("UpperBar").add(upperBar);

		// vertical menu
		menuBar = new VerticalMenuBar();
		intMenuBar();
		menuBar.addItemSelectedListener(this);
		RootPanel.get("MenuBar").add(menuBar);

		// By default init the Home Panel
		home = new Home(loginManager);
		RootPanel.get("MainPanel").add(home);
	}


	// login manager listener
	public void userLogin(String username) {
		// do nothing
	}

	/**
	 * login manager listener.
	 * 
	 * close the window where the user is, set it null and return to Home
	 */
	public void userLogout() {
		// removes actual items of the MainPanel
		Iterator iterator = RootPanel.get("MainPanel").iterator();
		while (iterator.hasNext()) {
			iterator.next();
		}
		iterator.remove();
		home.removeUserStatistics();
		RootPanel.get("MainPanel").add(home);
	}
}