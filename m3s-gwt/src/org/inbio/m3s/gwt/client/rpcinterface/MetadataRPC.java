/**
 * 
 */
package org.inbio.m3s.gwt.client.rpcinterface;

import java.util.List;

import org.inbio.m3s.gwt.client.dto.metadata.TechnicalMetadataGWTDTO;
import org.inbio.m3s.gwt.client.dto.util.InstitutionLiteGWTDTO;
import org.inbio.m3s.gwt.client.dto.util.PersonGWTDTO;
import org.inbio.m3s.gwt.client.exception.RPCIllegalArgumentException;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.GeneralMetadataTV;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.UsesAndCopyrightsTV;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author jgutierrez
 * 
 */
public interface MetadataRPC extends RemoteService {

	/**
	 * 
	 * @param mediaId
	 * @return
	 */
	public GeneralMetadataTV getGeneralMetadataTV(Integer mediaId);

/**
 * 
 * @param mediaId
 * @return
 */
	public UsesAndCopyrightsTV getUsesAndCopyrigthsMetadataTV(Integer mediaId);

	/**
	 * @gwt.args
	 * 
	 * @param mediaId
	 * @return
	 * 
	 */
	public TechnicalMetadataGWTDTO getTechnicalMetadataTV(Integer mediaId);

	/**
	 * 
	 * @param mediaTempFileId
	 * @param mediaTypeName
	 * @return
	 * 
	 */
	public TechnicalMetadataGWTDTO getTechnicalMetadataTV(String mediaTempFileId,
			String mediaTypeName);

	/**
	 * 
	 * 
	 * @param mediaTypeName
	 * @return
	 */
	public TechnicalMetadataGWTDTO getTechnicalMetadataNames(String mediaTypeName);

	/**
	 * @param gmtv
	 * 
	 * @param uactv
	 * 
	 * @param tmtv
	 * 
	 * @param username
	 * @return the database Id of the media owner of the metadata
	 */
	public Integer saveMetadata(GeneralMetadataTV gmtv, UsesAndCopyrightsTV uactv, TechnicalMetadataGWTDTO tmtv, String username)
			throws RPCIllegalArgumentException;

	/**
	 * Used in the MediaCategoryAndTypeSelector class
	 * 
	 *  
	 */
	public List<String> getMediaTypes(String categoryName);

	/**
	 * Used in the MediaCategoryAndTypeSelector class
	 * 
	 * @param callback
	 */
	public List<String> getMediaCategories();


	/**
	 * 
	 * @param specimenNumber
	 * @return a list of taxonids as strings
	 * @throws RPCIllegalArgumentException
	 */
	public List<String> getTaxonIdsBySpecimenNumber(Integer specimenNumber)
			throws RPCIllegalArgumentException;

	/**
	 * 
	 * @param observationNumber
	 * @return
	 * @throws RPCIllegalArgumentException
	 */
	public List<String> getTaxonIdsByObservationNumber(Integer observationNumber)
			throws RPCIllegalArgumentException;

	/**
	 * 
	 * @param gatheringCode
	 * @return
	 * @throws RPCIllegalArgumentException
	 */
	public List<String> getTaxonIdsByGatheringCode(String gatheringCode)
			throws RPCIllegalArgumentException;

	/**
	 * Used in the GeneralMetadataTable class
	 * 
	 * @see GeneralMetadataTable The annotation indicates that the returned List
	 *      will only contain String objects. This is done because GWT needs in
	 *      order to return List objects using RPC
	 * 
	 */
	public String getSiteFromSpecimenNumber(Integer specimenId)
			throws RPCIllegalArgumentException;

	/**
	 * Used in the GeneralMetadataTable class
	 * 
	 * @see GeneralMetadataTable The annotation indicates that the returned List
	 *      will only contain String objects. This is done because GWT needs in
	 *      order to return List objects using RPC
	 * 
	 */
	public String getSiteFromObservationNumber(Integer observationNumber)
			throws RPCIllegalArgumentException;

	/**
	 * 
	 * @param gatheringCode
	 */
	public String getSiteFromGatheringCode(String gatheringCode)
			throws RPCIllegalArgumentException;

	/**
	 * Used in the UsesAndCopyrightsPanel class *
	 * 
	 */
	public List<PersonGWTDTO> getPeople();

	/**
	 * Used in the UsesAndCopyrightsPanel class
	 * 
	 * 
	 */
	public List<InstitutionLiteGWTDTO> getInstitutions();

	/**
	 * Used in the UsesAndCopyrightsPanel class
	 * 
	 */
	public List<String> getMediaUses();

	/**
	 * Used in the UsesAndCopyrightsPanel class
	 * 
	 */
	public List<String> getUsePolicies();

}
