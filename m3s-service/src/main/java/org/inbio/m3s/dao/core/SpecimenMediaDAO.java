package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.model.core.SpecimenMediaId;

public interface SpecimenMediaDAO extends BaseDAO {
	
	public List<SpecimenMediaId> findAllByMediaId(Integer mediaId) throws IllegalArgumentException;

}
