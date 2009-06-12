package org.inbio.m3s.model.ara;

import java.util.Date;

import org.inbio.m3s.model.general.Person;

/**
 * 
 * @author jgutierrez
 *
 */
public class ARAPerson extends Person {
	/** */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ARAPerson() {
		super();
	}

	public ARAPerson(Integer personId, String firstName, String lastName, String initials, Integer birthYear, Integer deathYear, String occupation, String telephone, String fax, String streetAddress, String city, String stateProvince, String country, String email, String url, Date creationDate, String createdBy, Date lastModificationDate, String lastModificationBy, String secondLastName) {
		super(personId,firstName,lastName,initials,birthYear,deathYear,occupation,telephone,fax,streetAddress,city,stateProvince,
				country,email,url,creationDate,createdBy,lastModificationDate,lastModificationBy, secondLastName);
	}

}
