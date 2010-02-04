/**
 * 
 */
package org.inbio.m3s.dao.core;

/**
 * Este DAO no responde a una tabla, sino a una serie de operaciones de BD 
 * que se reparten entre varias tablas: Media, Person, Institution, etc, etc
 * 
 * @author jgutierrez
 * 
 * @deprecated NO ES UN DAO, es de manager
 *
 */
public interface StatisticsDAO {

	public Long allMediaCount() throws IllegalArgumentException;
	
	public Long DSCPhotosCount() throws IllegalArgumentException;
	
	public Long videosCount() throws IllegalArgumentException;
}
