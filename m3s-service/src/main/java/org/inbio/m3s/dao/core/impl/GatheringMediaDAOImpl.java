/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.GatheringMediaDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.model.core.GatheringMediaId;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;


/**
 * @author jgutierrez
 *
 */
public class GatheringMediaDAOImpl extends BaseDAOImpl implements GatheringMediaDAO {
	
	private static Logger logger = Logger.getLogger(GatheringMediaDAOImpl.class);
	
	//private AgentManager agentManager;

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.GatheringMediaDAO#getAllGatheringsLiteForMedia(java.lang.Integer)
	 */
	/*
	@SuppressWarnings("unchecked")
	public List<GatheringLite> getAllGatheringsLiteForMedia(final Integer mediaId) throws IllegalArgumentException {
		logger.error("getAllGatheringsLiteForMedia, param[mediaId:"+mediaId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<GatheringLite>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.model.core.GatheringMediaId(gm.id.mediaId,gm.id.gatheringDetailPersonId,gm.id.gatheringNumber)"
						+ " from GatheringMedia as gm "
						+ " where gm.id.mediaId = " + mediaId);
				query.setCacheable(false);
				List<GatheringMediaId> tempResults = query.list();
				List<GatheringLite> result = new ArrayList<GatheringLite>();
				GatheringLite gl = null;
				PersonLiteDTO pLite = null;
				for(GatheringMediaId gmId: tempResults){
					pLite = agentManager.getGatheringResposiblePersonLite(gmId.getGatheringDetailPersonId().toString());
					gl = new GatheringLite(gmId.getGatheringNumber(), pLite.getName());
					result.add(gl);
				}
				
				return result;
				
			}
		});
	}
	*/
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.GatheringMediaDAO#findAllByMediaId(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<GatheringMediaId> findAllByMediaId(final Integer mediaId)
			throws IllegalArgumentException {
		logger.debug("findAllByMediaId, param[mediaId:"+mediaId+"]");
		HibernateTemplate template = getHibernateTemplate();
		return (List<GatheringMediaId>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.model.core.GatheringMediaId(gm.id.mediaId,gm.id.gatheringDetailPersonId,gm.id.gatheringNumber)"
						+ " from GatheringMedia as gm "
						+ " where gm.id.mediaId = " + mediaId);
				query.setCacheable(false);
				return query.list();
			}
		});
	}

	/**
	 * @param agentManager the agentManager to set
	
	public void setAgentManager(AgentManager agentManager) {
		this.agentManager = agentManager;
	}
	 */

	/**
	 * @return the agentManager
	 
	public AgentManager getAgentManager() {
		return agentManager;
	}
	*/

}
