package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.model.general.Specimen;


public interface SpecimenDAO extends GenericBaseDAO<Specimen, Integer> {	
	
	public List<Specimen> findByGatheringNumberAndGatheringPersonId(Integer gatheringPersonId, Integer gatheringNumber) throws IllegalArgumentException;

}
