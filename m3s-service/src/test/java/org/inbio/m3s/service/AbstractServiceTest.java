/***************************************************************************
 * Copyright (C) 2006 Global Biodiversity Information Facility Secretariat.
 * All Rights Reserved.
 *
 * The contents of this file are subject to the Mozilla Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 ***************************************************************************/
package org.inbio.m3s.service;

import org.apache.commons.configuration.ConfigurationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.inbio.m3s.config.Properties;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * AbstractServiceTest
 *
 * @author dbarnier
 */
public abstract class AbstractServiceTest extends AbstractDependencyInjectionSpringContextTests {

	/**
	 * @see org.springframework.test.AbstractDependencyInjectionSpringContextTests#getConfigLocations()
	 */
	protected String[] getConfigLocations() {
		
		try {
			Properties.init();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new String [] {
				//"classpath*:/**/applicationContext-*.xml",
				//"classpath*:**/applicationContext-*.xml",
				"classpath*:org/inbio/m3s/**/applicationContext-*.xml",
				//"classpath*:org/inbio/m3s/**/log4j.xml",
				"classpath*:org/inbio/m3s/dao/applicationContext-dao.xml",
				"classpath*:org/inbio/m3s/dao/applicationContext-factories.xml",
				//"classpath*:org/inbio/m3s/service/applicationContext-service.xml"					
				};
	}

	public AbstractServiceTest() {
		/* turn on autowire by name injection */
		setPopulateProtectedVariables(true);
	}

	/**
	 * Returns the named bean from the application context
	 * @param name The name of the bean
	 * @return The bean
	 */
	protected Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	/**
	 * One time setup which keeps a session open for the duration of the tests.
	 */
	protected void onSetUp() throws Exception {
		//logger.trace("onSetup(): entered method.");

		SessionFactory sessionFactory = (SessionFactory) getBean("coffeaSessionFactory");
		Session session = sessionFactory.openSession();

		SessionHolder holder = new SessionHolder(session);
		TransactionSynchronizationManager.bindResource(sessionFactory, holder);
	}

	/**
	 * One time teardown which closes the session opened for the tests.
	 */
	protected void onTearDown() throws Exception {
		//logger.trace("onTearDown(): entered method.");
		super.onTearDown();

		SessionFactory sessionFactory = (SessionFactory) getBean("coffeaSessionFactory");
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session session = holder.getSession();
		if (session.isOpen()) {
			if (session.isDirty()) {
				session.flush();
			}
			session.close();
		}
		if (session.isConnected()) {
			session.connection().close();
		}
		holder.removeSession(session);
		TransactionSynchronizationManager.unbindResource(sessionFactory);
	}
}
