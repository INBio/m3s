package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.model.core.SpecimenMedia;
import org.inbio.m3s.model.core.SpecimenMediaId;

public interface SpecimenMediaDAO extends GenericBaseDAO<SpecimenMedia, SpecimenMediaId> {
	
	public List<SpecimenMediaId> findAllByMediaId(Integer mediaId) throws IllegalArgumentException;

}
