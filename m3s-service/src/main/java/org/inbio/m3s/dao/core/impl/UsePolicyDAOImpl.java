/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.UsePolicyDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.dao.DataCache;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.service.MessageManager;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class UsePolicyDAOImpl extends BaseDAOImpl implements UsePolicyDAO {
	
	private static Logger logger = Logger.getLogger(UsePolicyDAOImpl.class);


	public UsePolicyDTO getUsePolicyLite(String name) throws IllegalArgumentException {
		logger.debug("getUsePolicyLite with name = '" + name + "'.");
		String errorMsj = "El nombre de la politica de uso '" + name
				+ "' no fue encontrado.";

		try {
			if (DataCache.usePoliciesInCache) {
				logger.debug("getting use policy DBId... were in cache.");
			} else {
				logger.debug("getting use policy DBId... weren't in cache!.");
				DataCache.initUsePoliciesInfo();
			}

			int index = DataCache.usePoliciesNames.indexOf(name);

			logger.debug("use policy DBId... done: "
					+ DataCache.usePoliciesDBIds.get(index));

			return new UsePolicyDTO(DataCache.usePoliciesDBIds.get(index), name);
		} catch (Exception e) {
			logger.error(errorMsj);
			logger.error(e.getMessage());
			throw new IllegalArgumentException(errorMsj);
		}
	}

	
	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.UsePolicyDAO#getUsePolicyLite(java.lang.Integer)
	 */
	public UsePolicyDTO getUsePolicyLite(Integer usePolicyId) throws IllegalArgumentException {
		logger.debug("getUsePolicyLite");
		String errorMsj = "No se encontro ninguna politica de uso con el Id #"
				+ usePolicyId + ". ";

		try {

			if (DataCache.usePoliciesInCache) {
				logger.debug("getting use policy name... were in cache.");
			} else {
				logger.debug("getting use policy name... weren't in cache!.");
				DataCache.initUsePoliciesInfo();
			}

			int index = DataCache.usePoliciesDBIds.indexOf(usePolicyId);

			logger.debug("use policy name... done: "+ DataCache.usePoliciesNames.get(index));

			return new UsePolicyDTO(usePolicyId, DataCache.usePoliciesNames.get(index));
		} catch (Exception e) {
			logger.error(errorMsj);
			logger.error(e.getMessage());
			throw new IllegalArgumentException(errorMsj);
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<UsePolicyDTO> listAllLite() throws IllegalArgumentException {
		logger.debug("listAllLite");
		HibernateTemplate template = getHibernateTemplate();
		return (List<UsePolicyDTO>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.metadata.UsePolicyDTO(up.usePolicyId, tt.name)"
						+ " from TextTranslation as tt, UsePolicy as up"
						+ " where tt.language.languageId = "	+ MessageManager.DEFAULT_LANGUAGE
						+ " and tt.text = up.textByNameTextId");
				//query.setParameter(0, nomenclaturalGroupId);
				query.setCacheable(true);
				return query.list();
			}
		});
	}
	
	

}
