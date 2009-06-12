/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.full.MediaAttributeFull;

/**
 * @author jgutierrez
 *
 */
public interface MediaAttributeDAO extends BaseDAO{
	
	//static final Character VARCHAR_TYPE = new Character('V');

	//public static final Character NUMBER_TYPE = new Character('N');

	//public static final Character DATE_TYPE = new Character('D');


	public MediaAttributeFull getMediaAttributeFull(Integer mediaAttributeId) throws IllegalArgumentException;

	public MediaAttributeFull getMediaAttributeFull(String mediaAttributeName) throws IllegalArgumentException;
	
	public List<MediaAttributeFull> getMediaAttributesFullForMediaType(Integer mediaTypeId) throws IllegalArgumentException;
}
