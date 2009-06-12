package org.inbio.m3s.model.atta;


import java.util.Date;

import org.inbio.m3s.model.general.Person;

public class INBioPerson extends Person {
	
	private static final long serialVersionUID = 1L;
	

	public INBioPerson() {
	}


	/**
	 * @param personId
	 * @param firstName
	 * @param lastName
	 * @param initials
	 * @param birthYear
	 * @param deathYear
	 * @param occupation
	 * @param telephone
	 * @param fax
	 * @param streetAddress
	 * @param city
	 * @param stateProvince
	 * @param country
	 * @param email
	 * @param url
	 * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 * @param secondLastName
	 */
	public INBioPerson(Integer personId, String firstName, String lastName, String initials, Integer birthYear, Integer deathYear, String occupation, String telephone, String fax, String streetAddress, String city, String stateProvince, String country, String email, String url, Date creationDate, String createdBy, Date lastModificationDate, String lastModificationBy, String secondLastName) {
		super(personId,firstName,lastName,initials,birthYear,deathYear,occupation,telephone,fax,streetAddress,city,stateProvince,
				country,email,url,creationDate,createdBy,lastModificationDate,lastModificationBy, secondLastName);
	}

}
