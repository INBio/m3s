package org.inbio.m3s.model.general;

import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class Person extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer personId;

	private String firstName;

	private String lastName;

	private String initials;

	private Integer birthYear;

	private Integer deathYear;

	private String occupation;

	private String telephone;

	private String fax;

	private String streetAddress;

	private String city;

	private String stateProvince;

	private String country;

	private String email;

	private String url;

	private String secondLastName;
	
	/*
	private Date creationDate;

	private String createdBy;

	private Date lastModificationDate;

	private String lastModificationBy;
*/
	
	
	/**
	 * 
	 */
	public Person() {
		super();
	}

	/**
	 * @param personId
	 * @param firstName
	 * @param lastName
	 */
	public Person(Integer personId, String firstName, String lastName) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
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
	public Person(Integer personId, String firstName, String lastName, String initials, Integer birthYear, Integer deathYear, String occupation, String telephone, String fax, String streetAddress, String city, String stateProvince, String country, String email, String url, Date creationDate, String createdBy, Date lastModificationDate, String lastModificationBy, String secondLastName) {
		super();
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.initials = initials;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
		this.occupation = occupation;
		this.telephone = telephone;
		this.fax = fax;
		this.streetAddress = streetAddress;
		this.city = city;
		this.stateProvince = stateProvince;
		this.country = country;
		this.email = email;
		this.url = url;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
		this.secondLastName = secondLastName;
	}

	/**
	 * @return the birthYear
	 */
	public Integer getBirthYear() {
		return birthYear;
	}

	/**
	 * @param birthYear the birthYear to set
	 */
	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the deathYear
	 */
	public Integer getDeathYear() {
		return deathYear;
	}

	/**
	 * @param deathYear the deathYear to set
	 */
	public void setDeathYear(Integer deathYear) {
		this.deathYear = deathYear;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the initials
	 */
	public String getInitials() {
		return initials;
	}

	/**
	 * @param initials the initials to set
	 */
	public void setInitials(String initials) {
		this.initials = initials;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	/**
	 * @return the personId
	 */
	public Integer getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	/**
	 * @return the secondLastName
	 */
	public String getSecondLastName() {
		return secondLastName;
	}

	/**
	 * @param secondLastName the secondLastName to set
	 */
	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	/**
	 * @return the stateProvince
	 */
	public String getStateProvince() {
		return stateProvince;
	}

	/**
	 * @param stateProvince the stateProvince to set
	 */
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	/**
	 * @return the streetAddress
	 */
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * @param streetAddress the streetAddress to set
	 */
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}



}
