/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.core.MetadataStandardDAO;
import org.inbio.m3s.dto.lite.MetadataStandardLiteDTO;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author jgutierrez
 * 
 */
public class MetadataStandardDAOImpl extends HibernateDaoSupport implements MetadataStandardDAO {
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.dao.interfaces.MetadataStandardDAO#getMetadataStandardFull(java.lang.Integer)
	public MetadataStandardFull getMetadataStandardFull(final Integer MetadataStandardId)
			throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return (MetadataStandardFull) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.full.MetadataStandardFull(ms.metadataStandardId," +
						"ms.name,ms.description,ms.metadataStandardImplementationClass,ms.creationDate," +
						"ms.createdBy,ms.lastModificationDate,ms.lastModificationBy)"
								+ " from MetadataStandard as ms "
								+ " where ms.metadataStandardId = " + MetadataStandardId + " ");
				query.setCacheable(true);
				return query.uniqueResult();
			}
		});
	}
	 */

	@SuppressWarnings("unchecked")
	public List<MetadataStandardLiteDTO> getMetadataStandardLite(final Integer mediaTypeDBId, final Integer languageId) throws IllegalArgumentException {
		HibernateTemplate template = getHibernateTemplate();
		return (List<MetadataStandardLiteDTO>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query = session.createQuery(
						"select new org.inbio.m3s.dto.lite.MetadataStandardLiteDTO(tt.name, mat.metadataStandard.metadataStandardId, mat.standardAttributeId)"
									+ " from TextTranslation as tt, MediaAttributeType as mat "
									+ " where mat.mediaType.mediaTypeId = " + mediaTypeDBId
									+ " and tt.text.textId = mat.mediaAttribute.textByNameTextId.textId "
									+ "and tt.language.languageId = " + languageId + "");
				query.setCacheable(true);
				return query.list();
			}
		});
	}
	
}
