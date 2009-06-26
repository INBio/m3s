/**
 * 
 */
package org.inbio.m3s.gwt.client.rpcinterface;

import org.inbio.m3s.gwt.client.dto.metadata.TechnicalMetadataGWTDTO;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.GeneralMetadataTV;
import org.inbio.m3s.gwt.client.widgets.metadata.dto.UsesAndCopyrightsTV;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author jgutierrez
 * 
 */
public interface MetadataRPCAsync {

	/**
	 * 
	 * @param mediaId
	 * @param callback
	 */
	public void getGeneralMetadataTV(Integer mediaId, AsyncCallback callback);

	/**
	 * 
	 * @param mediaId
	 * @param callback
	 */
	public void getUsesAndCopyrigthsMetadataTV(Integer mediaId,
			AsyncCallback callback);

	/**
	 * 
	 * @param mediaId
	 * @param callback
	 */
	public void getTechnicalMetadataTV(Integer mediaId, AsyncCallback callback);

	/**
	 * 
	 * @param mediaTypeId
	 * @param callback
	 */
	public void getTechnicalMetadataNames(String mediaTypeName,
			AsyncCallback<TechnicalMetadataGWTDTO> callback);

	/**
	 * 
	 * @param mediaTempFileId
	 * @param mediaTypeName
	 * @param callback
	 */
	public void getTechnicalMetadataTV(String mediaTempFileId,
			String mediaTypeName, AsyncCallback<TechnicalMetadataGWTDTO> callback);

	/**
	 * 
	 * @param gmtv
	 * @param uactv
	 * @param tmtv
	 * @param username
	 * @param callback
	 */
	public void saveMetadata(GeneralMetadataTV gmtv, UsesAndCopyrightsTV uactv,
			TechnicalMetadataGWTDTO tmtv, String username, AsyncCallback callback);

	/**
	 * 
	 * @param categoryName
	 * @param callback
	 */
	public void getMediaTypes(String categoryName, AsyncCallback callback);

	/**
	 * 
	 * @param callback
	 */
	public void getMediaCategories(AsyncCallback callback);


	/**
	 * 
	 * @param specimenNumber
	 * @param callback
	 */
	public void getTaxonIdsBySpecimenNumber(Integer specimenNumber,
			AsyncCallback callback);


	/**
	 * 
	 * @param observationNumber
	 * @param callback
	 */
	public void getTaxonIdsByObservationNumber(Integer observationNumber,
			AsyncCallback callback);


	/**
	 * 
	 * @param code
	 * @param callback
	 */
	public void getTaxonIdsByGatheringCode(String code, AsyncCallback callback);

	/**
	 * 
	 * @param specimenId
	 * @param callback
	 */
	public void getSiteFromSpecimenNumber(Integer specimenId,
			AsyncCallback callback);

	/**
	 * 
	 * @param observationNumber
	 * @param callback
	 */
	public void getSiteFromObservationNumber(Integer observationNumber,
			AsyncCallback callback);

	/**
	 * 
	 * @param gatheringCode
	 * @param callback
	 */
	public void getSiteFromGatheringCode(String gatheringCode,
			AsyncCallback callback);

	/**
	 * Used in the UsesAndCopyrightsPanel class *
	 * 
	 */
	public void getPeople(AsyncCallback callback);

	/**
	 * Used in the UsesAndCopyrightsPanel class
	 * 
	 */
	public void getInstitutions(AsyncCallback callback);

	/**
	 * Used in the UsesAndCopyrightsPanel class
	 * 
	 */
	public void getMediaUses(AsyncCallback callback);

	/**
	 * 
	 * @param language
	 * @param callback
	 */
	public void getUsePolicies(AsyncCallback callback);

}
