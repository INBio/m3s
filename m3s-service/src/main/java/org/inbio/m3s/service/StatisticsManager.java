/**
 * 
 */
package org.inbio.m3s.service;

/**
 * @author jgutierrez
 *
 */
public interface StatisticsManager {

	public int getAllMediaCount() throws IllegalArgumentException;
	
	public int getDSCPhotosCount() throws IllegalArgumentException;
	
	public int getVideosCount() throws IllegalArgumentException;

}
