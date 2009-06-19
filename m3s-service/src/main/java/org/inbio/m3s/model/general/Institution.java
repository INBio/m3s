package org.inbio.m3s.model.general;

import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;

/**
 * 
 * @author jgutierrez
 *
 */
public class Institution extends DBLogEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer institutionId;

	private String name;

	private String telephone;

	private String fax;

	private String streetAddress;

	private String city;

	private String stateProvince;

	private String country;

	private String acronym;

	private String url;

	private Integer logoImageId;

	/**
	 * 
	 */
	public Institution() {
		super();
	}

	/**
	 * @param institutionId
	 * @param name
	 */
	public Institution(Integer institutionId, String name) {
		super();
		this.institutionId = institutionId;
		this.name = name;
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
	public Institution(Integer institutionId, String name, String telephone, String fax, String streetAddress, String city, String stateProvince, String country, String acronym, String url, Integer logoImageId,Date creationDate, String createdBy, Date lastModificationDate, String lastModificationBy) {
		super();
		this.institutionId = institutionId;
		this.name = name;
		this.telephone = telephone;
		this.fax = fax;
		this.streetAddress = streetAddress;
		this.city = city;
		this.stateProvince = stateProvince;
		this.country = country;
		this.acronym = acronym;
		this.url = url;
		this.logoImageId = logoImageId;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}


	/**
	 * @return the acronym
	 */
	public String getAcronym() {
		return acronym;
	}

	/**
	 * @param acronym the acronym to set
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
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
	 * @return the institutionId
	 */
	public Integer getInstitutionId() {
		return institutionId;
	}

	/**
	 * @param institutionId the institutionId to set
	 */
	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}

	/**
	 * @return the logoImageId
	 */
	public Integer getLogoImageId() {
		return logoImageId;
	}

	/**
	 * @param logoImageId the logoImageId to set
	 */
	public void setLogoImageId(Integer logoImageId) {
		this.logoImageId = logoImageId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
