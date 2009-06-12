package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.GeneralMetadataDTO;
import org.inbio.m3s.dto.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.lite.MediaLite;

public interface MediaDAO extends BaseDAO{

	//stadisticas
	public List<MediaLite> getLastPublicMedia(int quantity) throws IllegalArgumentException;

	public MediaLite getMediaLite(Integer mediaId) throws IllegalArgumentException;  
	
	public List<MediaLite> getMediaLiteForTaxonId(Integer taxonId) throws IllegalArgumentException;
	
	public GeneralMetadataDTO getGeneralMetadataDTO(Integer mediaId);
	
	public UsesAndCopyrightsDTO getUsesAndCopyrightsDTO(Integer mediaId);
	
	
}