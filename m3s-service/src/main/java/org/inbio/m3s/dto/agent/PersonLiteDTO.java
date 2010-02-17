/**
 * 
 */
package org.inbio.m3s.dto.agent;

import org.inbio.m3s.dto.BaseDTO;

/**
 * @author jgutierrez
 *
 */
public class PersonLiteDTO extends BaseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4994487393351577195L;

	private String personKey;

	/* The name in a display ready way, i.e. first name + last name*/
	private String name;
	
	/* A contact email*/
	private String email;

	/**
	 * 
	 */
	public PersonLiteDTO() {
		super();
	}

	/**
	 * @param personKey
	 * @param name
	 * @param email
	 */
	public PersonLiteDTO(String personKey, String name, String email) {
		super();
		this.setPersonKey(personKey);
		this.setName(name);
		this.email = email;
	}
	
	/**
	 * @param personId
	 * @param name
	 * @param email
	 */
	public PersonLiteDTO(Integer personId, String name, String email) {
		super();
		this.setPersonKey(String.valueOf(personId));
		this.setName(name);
		this.email = email;
	}
	
	@Override
	public String toString(){
		return "La Person Lite DTO tiene:" +
		"\n\tKey : " + this.getPersonKey() +
		"\n\tName: '"+ this.getName() +"'" +
		"\n\te-mail: '"+ this.email +"'" +
		"";
	}

	/**
	 * @param personKey the personKey to set
	 */
	public void setPersonKey(String personKey) {
		this.personKey = personKey;
	}

	/**
	 * @return the personKey
	 */
	public String getPersonKey() {
		return personKey;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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

}
