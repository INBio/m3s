package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.model.general.Specimen;


public interface SpecimenDAO extends BaseDAO {


	//public List<SpecimenLite> getSpecimenLiteFromGathering(Integer gatheringPersonId, Integer gatheringNumber)
	//		throws IllegalArgumentException;
	
	//public List<SpecimenLite> getAllSpecimensLiteForMedia(Integer mediaId) throws IllegalArgumentException;

	//public List<SpecimenLite> getSpecimenLiteFromGathering(String gatheringCode) throws IllegalArgumentException;
	
	
	public List<Specimen> findByGatheringNumberAndGatheringPersonId(Integer gatheringPersonId, Integer gatheringNumber) throws IllegalArgumentException;

}
