/**
 * 
 */
package org.inbio.m3s.dto.metadata.util;

/**
 * @author jgutierrez
 *
 */
public enum YoutubeVideoMediaAttributeEntity {

	VIDEO_ID(0, "videoId"); 
	//MAKE(1, "Make"); //

	//public static final int TOTAL_METADATA_ATTRIBUTES = 2;


	//standard attribute Id. Is not the *media_attribute.media_attribute_id*
	private int id;
	//name in the exif standard
	private String name;
	
	/**
	 * @param id
	 * @param name
	 */
	private YoutubeVideoMediaAttributeEntity(int id, String name) {
		this.id = id;
		this.name = name;
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static YoutubeVideoMediaAttributeEntity getById(int id){
		YoutubeVideoMediaAttributeEntity[] todos = YoutubeVideoMediaAttributeEntity.values();
		return todos[id];
	}
	
}
