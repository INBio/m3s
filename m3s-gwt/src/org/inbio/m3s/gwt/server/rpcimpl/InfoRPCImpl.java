/**
 * 
 */
package org.inbio.m3s.gwt.server.rpcimpl;

import org.inbio.m3s.gwt.client.rpcinterface.InfoRPC;
import org.inbio.m3s.service.StatisticsManager;
import org.inbio.m3s.util.ServiceUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author jgutierrez
 * 
 */
public class InfoRPCImpl extends RemoteServiceServlet implements InfoRPC {

	
	private static final long serialVersionUID = -6383951754557169578L;
	
	StatisticsManager statisticsManager = (StatisticsManager) ServiceUtil.appContext.getBean("statisticsManager");

	public Integer countMultimedia() {
		return new Integer(statisticsManager.getAllMediaCount());
	}

	public Integer countDigitalPhotos() {
		return new Integer(statisticsManager.getDSCPhotosCount());
	}

	public Integer countVideos() {
		return new Integer(statisticsManager.getVideosCount());
	}

}
