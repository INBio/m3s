/**
 * 
 */
package org.inbio.m3s.dao.ara.impl;

import java.util.List;

import org.inbio.m3s.dao.core.SpecimenDAO;
import org.inbio.m3s.dao.impl.BaseDAOImpl;
import org.inbio.m3s.model.general.Specimen;

/**
 * @author jgutierrez
 *
 */
public class ARASpecimenDAOImpl extends BaseDAOImpl implements SpecimenDAO {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dao.core.SpecimenDAO#findByGatheringNumberAndGatheringPersonId(java.lang.Integer, java.lang.Integer)
	 */
	public List<Specimen> findByGatheringNumberAndGatheringPersonId(
			Integer gatheringPersonId, Integer gatheringNumber)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
