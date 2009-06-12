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
 */
public interface StatisticsDAO {

	public Integer allMediaCount() throws IllegalArgumentException;
	
	public Integer DSCPhotosCount() throws IllegalArgumentException;
	
	public Integer videosCount() throws IllegalArgumentException;
}
