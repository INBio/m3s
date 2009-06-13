/**
 * 
 */
package org.inbio.m3s.gwt.server.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author jgutierrez
 * 
 */

public class SessionListener implements ServletContextListener {

	private static final long serialVersionUID = 1L;

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("CERRANDO LA SESSION");
		System.out.println("CERRANDO LA SESSION");
		System.out.println("CERRANDO LA SESSION");
		//HibernateUtil.closeM3SDBFactory();
		//HibernateUtil.closeATTADB_SFFactory();
		System.out.println("CERRANDO LA SESSION");
		System.out.println("CERRANDO LA SESSION");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("INICIANDO LA SESSION");
		System.out.println("INICIANDO LA SESSION");
		System.out.println("INICIANDO LA SESSION");
		System.out.println("INICIANDO LA SESSION");
		System.out.println("INICIANDO LA SESSION");
	}
}
