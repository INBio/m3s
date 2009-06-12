package org.inbio.m3s.model.core;


import java.io.Serializable;
import java.util.Date;

import org.inbio.m3s.model.DBLogEntity;


/**
 * 
 * @author jgutierrez
 *
 */
public class Text extends DBLogEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5868427337994127104L;

	private Integer textId;

	public Text() {
		super();
	}
	
	public Text(Integer textId){
		super();
		this.textId = textId;
	}

	public Text(Integer textId, Date creationDate, String createdBy, Date lastModificationDate,
			String lastModificationBy){
		super();
		this.textId = textId;
		this.setCreationDate(creationDate);
		this.setCreatedBy(createdBy);
		this.setLastModificationDate(lastModificationDate);
		this.setLastModificationBy(lastModificationBy);
	}

	public Integer getTextId() {
		return this.textId;
	}

	public void setTextId(Integer textId) {
		this.textId = textId;
	}


}
