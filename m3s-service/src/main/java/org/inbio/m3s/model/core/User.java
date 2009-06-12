package org.inbio.m3s.model.core;

import java.io.Serializable;

/**
 * 
 * @author jgutierrez
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = -7387906692288603045L;

	private Integer userId;

	private String name;

	private String password;

	public User() {
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
