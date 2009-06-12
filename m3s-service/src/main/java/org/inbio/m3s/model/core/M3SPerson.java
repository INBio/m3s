package org.inbio.m3s.model.core;

import java.sql.Date;

import org.inbio.m3s.model.general.Person;

/**
 * 
 * @author jgutierrez
 *
 */
public class M3SPerson extends Person {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -77980835884397081L;

	/**
	 * 
	 */
	public M3SPerson() {
		super();
	}

	/**
	 * @param personId
	 * @param firstName
	 * @param lastName
	 */
	public M3SPerson(Integer personId, String firstName, String lastName) {
		super(personId,firstName,lastName);
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
	public M3SPerson(Integer personId, String firstName, String lastName, String initials, Integer birthYear, Integer deathYear, String occupation, String telephone, String fax, String streetAddress, String city, String stateProvince, String country, String email, String url, Date creationDate, String createdBy, Date lastModificationDate, String lastModificationBy, String secondLastName) {
		super(personId,firstName,lastName,initials,birthYear,deathYear,occupation,telephone,fax,streetAddress,city,stateProvince,
				country,email,url,creationDate,createdBy,lastModificationDate,lastModificationBy, secondLastName);
	}

}
