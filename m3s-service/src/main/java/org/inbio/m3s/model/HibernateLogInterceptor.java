/**
 * 
 */
package org.inbio.m3s.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.type.Type;

/**
 * @author jgutierrez
 * 
 * This class has to do the loggin database work. Has to set and update the log
 * camps, which are: creation_date, created_by, last_modification_date and
 * last_modification_by
 * 
 */
public class HibernateLogInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;

	Session session;

	private Set<DBLogEntity> inserts = new HashSet<DBLogEntity>();

	private Set<DBLogEntity> updates = new HashSet<DBLogEntity>();

	public HibernateLogInterceptor() {
	}

	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] properyNames, Type[] types) throws CallbackException {
		try {
			if (entity instanceof DBLogEntity) {
				inserts.add((DBLogEntity) entity);
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public void preFlush(Iterator iterator) throws CallbackException {
		try {
			for (Iterator it = inserts.iterator(); it.hasNext();) {
				DBLogEntity log = (DBLogEntity) it.next();
				log.setSaveValues();
			}
			for (Iterator it = updates.iterator(); it.hasNext();) {
				DBLogEntity log = (DBLogEntity) it.next();
				log.setUpdateValues();
			}
		} finally {
			inserts.clear();
			updates.clear();
		}
	}

	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) throws CallbackException {
		if (entity instanceof DBLogEntity) {
			updates.add((DBLogEntity) entity);
		}
		return false;
	}

	public void setSession(Session session) {
		this.session = session;

	}

}
