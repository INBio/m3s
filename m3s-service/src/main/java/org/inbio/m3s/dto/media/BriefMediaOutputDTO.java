/**
 * 
 */
package org.inbio.m3s.dto.media;

import org.inbio.m3s.dto.BaseDTO;

/**
 * This is a READ only DTO.. not to be used for any other purpose
 * 
 * @author jgutierrez
 *
 */
public class BriefMediaOutputDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private String mediaKey;
	
	private String title;
	
	private String info1;
	
	private String info2;
	
	private String info3;
	
	/**
	 * @param mediaKey
	 * @param title
	 * @param info1
	 * @param info2
	 * @param info3
	 */
	public BriefMediaOutputDTO(String mediaKey, String title, String info1,
			String info2, String info3) {
		this.mediaKey = mediaKey;
		this.title = title;
		this.info1 = info1;
		this.info2 = info2;
		this.info3 = info3;
	}

	/**
	 * 
	 */
	public BriefMediaOutputDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the mediaKey
	 */
	public String getMediaKey() {
		return mediaKey;
	}

	/**
	 * @param mediaKey the mediaKey to set
	 */
	public void setMediaKey(String mediaKey) {
		this.mediaKey = mediaKey;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the info1
	 */
	public String getInfo1() {
		return info1;
	}

	/**
	 * @param info1 the info1 to set
	 */
	public void setInfo1(String info1) {
		this.info1 = info1;
	}

	/**
	 * @return the info2
	 */
	public String getInfo2() {
		return info2;
	}

	/**
	 * @param info2 the info2 to set
	 */
	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	/**
	 * @return the info3
	 */
	public String getInfo3() {
		return info3;
	}

	/**
	 * @param info3 the info3 to set
	 */
	public void setInfo3(String info3) {
		this.info3 = info3;
	}
	
	
	
}
