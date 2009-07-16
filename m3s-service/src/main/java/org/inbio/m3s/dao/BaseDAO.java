/**
 * 
 */
package org.inbio.m3s.dao;

import java.util.List;


/**
 * @author jgutierrez
 *
 */
//public interface BaseDAO<E extends Object,I extends Object> {
public interface BaseDAO {
	
	@SuppressWarnings("unchecked")
	//public E findById(Class entityClass, I Id) throws IllegalArgumentException;
	public Object findById(Class entityClass, Object Id) throws IllegalArgumentException;
	
	@SuppressWarnings("unchecked")
	//public List<E> findAll(Class entityClass) throws IllegalArgumentException;
	public List<Object> findAll(Class entityClass) throws IllegalArgumentException;
	
	//public void create(E entity) throws IllegalArgumentException;
	public void create(Object entity) throws IllegalArgumentException;
	
	//public void update(E entity) throws IllegalArgumentException;
	public void update(Object entity) throws IllegalArgumentException;
	
	//public void delete(E entity) throws IllegalArgumentException;
	public void delete(Object entity) throws IllegalArgumentException;

}
