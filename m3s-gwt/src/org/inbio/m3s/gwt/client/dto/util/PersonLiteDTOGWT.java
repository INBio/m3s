/**
 * 
 */
package org.inbio.m3s.gwt.client.dto.util;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 
 * Este objeto debería ser igual que el que viene de la capa de service, pero
 * por ignorancia, no se como heredar el otro objeto y que GWT lo pueda usar,
 * tengo que crear este enmascaramiento, güeiso. 
 * @author jgutierrez
 *
 */
public class PersonLiteDTOGWT implements IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4994487393351577195L;

	private String personKey;

	/* The name in a display ready way, i.e. first name + last name*/
	private String name;
	

	/**
	 * 
	 */
	public PersonLiteDTOGWT() {
		super();
	}

	/**
	 * @param personKey
	 * @param name
	 */
	public PersonLiteDTOGWT(String personKey, String name) {
		super();
		this.setPersonKey(personKey);
		this.setName(name);
	}
	
	@Override
	public String toString(){
		return "La Person Lite DTO tiene:" +
		"\n\tKey : " + this.getPersonKey() +
		"\n\tName: '"+ this.getName() +"'" +
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


}
