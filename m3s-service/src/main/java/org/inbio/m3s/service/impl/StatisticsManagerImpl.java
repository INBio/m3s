/**
 * 
 */
package org.inbio.m3s.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.StatisticsDAO;
import org.inbio.m3s.service.StatisticsManager;

/**
 * @author jgutierrez
 * 
 */
public class StatisticsManagerImpl implements StatisticsManager {

	protected static Log logger = LogFactory.getLog(StatisticsManagerImpl.class);

	private StatisticsDAO statisticsDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.service.StatisticsManager#getAllMediaCount()
	 */
	public int getAllMediaCount() throws IllegalArgumentException {
		return statisticsDAO.allMediaCount().intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.service.StatisticsManager#getDSCPhotosCount()
	 */
	public int getDSCPhotosCount() throws IllegalArgumentException {
		return statisticsDAO.DSCPhotosCount().intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.service.StatisticsManager#getVideosCount()
	 */
	public int getVideosCount() throws IllegalArgumentException {
		return statisticsDAO.videosCount().intValue();
	}

	/**
	 * @param statisticsDAO the statisticsDAO to set
	 */
	public void setStatisticsDAO(StatisticsDAO statisticsDAO) {
		this.statisticsDAO = statisticsDAO;
	}

	/**
	 * @return the statisticsDAO
	 */
	public StatisticsDAO getStatisticsDAO() {
		return statisticsDAO;
	}

}
