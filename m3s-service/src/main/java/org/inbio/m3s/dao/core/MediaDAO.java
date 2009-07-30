package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.dto.metadata.GeneralMetadataDTO;
import org.inbio.m3s.dto.metadata.UsesAndCopyrightsDTO;
import org.inbio.m3s.dto.lite.MediaLite;

public interface MediaDAO extends BaseDAO{

	//stadisticas
	@Deprecated
	public List<MediaLite> getLastPublicMedia(int quantity) throws IllegalArgumentException;
	@Deprecated
	public MediaLite getMediaLite(Integer mediaId) throws IllegalArgumentException;  
	@Deprecated
	public List<MediaLite> getMediaLiteForTaxonId(Integer taxonId) throws IllegalArgumentException;
	@Deprecated
	public GeneralMetadataDTO getGeneralMetadataDTO(String mediaKey);
	@Deprecated
	public UsesAndCopyrightsDTO getUsesAndCopyrightsDTO(Integer mediaId);
	
	
}