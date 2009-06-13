/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.login.listener;

import java.util.Iterator;
import java.util.Vector;


/**
 * @author jgutierrez
 * 
 */
public class LoginListenerCollection extends Vector {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Fires a new user log's in
	 * 
	 * @param sender
	 *            the widget sending the event.
	 */
	public void userLogin(String userName) {
		for (Iterator it = iterator(); it.hasNext();) {
			LoginListener listener = (LoginListener) it.next();
			listener.userLogin(userName);
		}
	}

	/**
	 * Fires a user log's out
	 * 
	 * @param sender
	 *            the widget sending the event.
	 * 
	 */
	public void userLogout() {
		for (Iterator it = iterator(); it.hasNext();) {
			LoginListener listener = (LoginListener) it.next();
			listener.userLogout();
		}
	}

}