/**
 * 
 */
package org.inbio.m3s.dao.core.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.inbio.m3s.dao.GenericBaseDAOImpl;
import org.inbio.m3s.dao.core.MediaAttributeDAO;
import org.inbio.m3s.dto.metadata.util.MediaAttributeEntity;
import org.inbio.m3s.model.core.MediaAttribute;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author jgutierrez
 *
 */
public class MediaAttributeDAOImpl extends GenericBaseDAOImpl<MediaAttribute, Integer> implements MediaAttributeDAO {
	
	private static Logger logger = Logger.getLogger(MediaAttributeDAOImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<MediaAttribute> findAllByMediaType(final String mediaTypeKey)
			throws IllegalArgumentException {
		logger.debug("findAllByMediaType, param mediaTypeId["	+ mediaTypeKey);
		HibernateTemplate template = getHibernateTemplate();
		
		List<Integer> mediaAttributesIdList = (List<Integer>) template.execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(
						"select mat.id.mediaAttributeId" +
						" from MediaAttributeType as mat"+
						" where mat.id.mediaTypeId = " + mediaTypeKey);
				query.setCacheable(true);
				return query.list();
			}
		});
		
		List<MediaAttribute> mediaAttributeList = new ArrayList<MediaAttribute>();
		MediaAttribute ma;
		MediaAttributeEntity mae;
		
		for(Integer mediaAttributeId :mediaAttributesIdList){
			mae = MediaAttributeEntity.getById(mediaAttributeId.intValue());
			ma = new MediaAttribute(mediaAttributeId,mae.getNamekey(),mae.getDescriptionkey(),mae.getMediaAttributeValueType());
			mediaAttributeList.add(ma);
		}
		
		return mediaAttributeList;
		
	}
	
	




	
}
