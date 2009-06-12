/**
 * 
 */
package org.inbio.m3s.dao;

import java.util.List;


/**
 * @author jgutierrez
 *
 */
public interface BaseDAO {
	
	@SuppressWarnings("unchecked")
	public Object findById(Class entityClass, Object Id) throws IllegalArgumentException;
	
	@SuppressWarnings("unchecked")
	public List<Object> findAll(Class entityClass) throws IllegalArgumentException;
	
	public void create(Object entity) throws IllegalArgumentException;
	
	public void update(Object entity) throws IllegalArgumentException;
	
	public void delete(Object entity) throws IllegalArgumentException;

}
