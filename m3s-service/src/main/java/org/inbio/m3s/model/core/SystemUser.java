/**
 * 
 */
package org.inbio.m3s.model.core;


import java.util.Date;
import java.util.StringTokenizer;

import org.inbio.m3s.model.LogGenericEntity;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

/**
 * @author jgutierrez
 *
 */
public class SystemUser extends LogGenericEntity implements UserDetails {

	/***/
	private static final long serialVersionUID = 8230565772169868198L;

	private Integer userId;

	private String username;

	private String fullname;
	
	private String password;

	private boolean enabled;

	private String roles;

	private Integer personId;
	
	private String ROLE_DELIMITER = ",";

	/**
	 * 
	 */
	public SystemUser() {
		super();
	}
	
	
	/**
	 * @param userId
	 * @param username
	 * @param fullname
	 * @param password
	 * @param enabled
	 * @param roles
	 * @param personId
   * @param creationDate
	 * @param createdBy
	 * @param lastModificationDate
	 * @param lastModificationBy
	 */
	public SystemUser(Integer userId, String username, String fullname, 
			String password, boolean enabled, String roles, Integer personId,
			Date creationDate, String createdBy, Date lastModificationDate, 
			String lastModificationBy) {
		super(creationDate, createdBy, lastModificationDate, lastModificationBy);
		this.userId = userId;
		this.username = username;
		this.fullname = fullname;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
		this.personId = personId;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getAuthorities()
	 */
	public GrantedAuthority[] getAuthorities() {
		
		StringTokenizer st = new StringTokenizer(this.getRoles(), ROLE_DELIMITER);
		GrantedAuthorityImpl[] grantedAuthorityImplArray = new GrantedAuthorityImpl[st.countTokens()];
		
		for(int i =0; st.hasMoreElements();i++)
			grantedAuthorityImplArray[i] = new GrantedAuthorityImpl(st.nextToken());
		
		return grantedAuthorityImplArray;

		/*
		//return null;
		GrantedAuthorityImpl[] grantedAuthorityImplArray = {new GrantedAuthorityImpl("ROLE_ADMIN"), new GrantedAuthorityImpl("ROLE_USER")};
		
		//User(String username, String password, boolean enabled, boolean accountNonExpired,
   // boolean credentialsNonExpired, boolean accountNonLocked, GrantedAuthority[] authorities)
		if(username.equalsIgnoreCase("jgutierrez"))
			return new User("jgutierrez", "prueba", true, true, true, true, grantedAuthorityImplArray);
		else if(username.equalsIgnoreCase("gama"))
			return new User("gama", "gama", true, true, true, true, grantedAuthorityImplArray);
		else
			return new User("lala", "lolo", true, true, true, true, grantedAuthorityImplArray);
	}
		 */
		//return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SystemUser [enabled=" + enabled + ", fullname=" + fullname
				+ ", password=" + password + ", personId=" + personId + ", roles="
				+ roles + ", userId=" + userId + ", username=" + username + "]";
	}	

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonExpired()
	 */
	public boolean isAccountNonExpired() {
		return this.isEnabled();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
	 */
	public boolean isAccountNonLocked() {
		return this.isEnabled();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	public boolean isCredentialsNonExpired() {
		return this.isEnabled();
	}


	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
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

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getUsername()
	 */
	public String getUsername() {
		return this.username;
	}	
	
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#getPassword()
	 */
	public String getPassword() {
		return this.password;
	}	
	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.userdetails.UserDetails#isEnabled()
	 */
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * @return the rOLE_DELIMITER
	 */
	public String getROLE_DELIMITER() {
		return ROLE_DELIMITER;
	}

	/**
	 * @param rOLEDELIMITER the rOLE_DELIMITER to set
	 */
	public void setROLE_DELIMITER(String rOLEDELIMITER) {
		ROLE_DELIMITER = rOLEDELIMITER;
	}

}
