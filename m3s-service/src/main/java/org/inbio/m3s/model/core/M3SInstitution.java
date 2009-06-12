package org.inbio.m3s.model.core;

import java.sql.Date;

import org.inbio.m3s.model.general.Institution;

/**
 * 
 * @author jgutierrez
 *
 */
public class M3SInstitution extends Institution {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public M3SInstitution() {
		super();
	}

	/**
	 * @param institutionId
	 * @param name
	 */
	public M3SInstitution(Integer institutionId, String name) {
		super(institutionId, name);
	}

	/**
	 * @param institutionId
	 * @param name
	 * @param telephone
	 * @param fax
	 * @param streetAddress
	 * @param city
	 * @param stateProvince
	 * @param country
	 * @param acronym
	 * @param url
	 * @param logoImageId
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 */
	public M3SInstitution(Integer institutionId, String name, String telephone, String fax, String streetAddress, String city, String stateProvince, String country, String acronym, String url, Integer logoImageId,Date creationDate, String createdBy, Date lastModificationDate, String lastModificationBy) {
		super(institutionId,name,telephone,fax,streetAddress,city,stateProvince,country,acronym,url,logoImageId,
				creationDate, createdBy,lastModificationDate,lastModificationBy);
	}
}
