/**
 * 
 */
package org.inbio.m3s.dto.metadata.util;

import org.inbio.m3s.dao.multimedia.MetadataExtractorDAO;
import org.inbio.m3s.dao.multimedia.impl.EXIFMetadataExtractorDAOImpl;
import org.inbio.m3s.dao.multimedia.impl.FileInfoMetadataExtractorImpl;
import org.inbio.m3s.dao.multimedia.impl.VIDEOMDMetadataExtractorImpl;

/**
 * @author jgutierrez
 *
 */
public enum MetadataStandardEntity {

	EXIF(1, "EXIF", EXIFMetadataExtractorDAOImpl.class.getName()),
	FILE_INFO(2, "File Information", FileInfoMetadataExtractorImpl.class.getName()),  
	MET(3, "MET",VIDEOMDMetadataExtractorImpl.class.getName()),
	MIGRATION(4, "Migration",null),
	YOUTUBE_VIDEO(5, "Youtube Video",null);
	
	private int id;
	private String name;
	/*
	 * implementing class to extract metadata from the original file.
	 * If null means that their is no way to extract data.
	 */
	private String implementingClass; 
	
	/**
	 * @param id
	 * @param name
	 * @param implementingClass
	 */
	private MetadataStandardEntity(int id, String name, String implementingClass) {
		this.id = id;
		this.name = name;
		this.implementingClass = implementingClass;
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
	 * @return
	 */
	public String getImplementingClass(){
		return implementingClass;
	}
	
	/**
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	public MetadataExtractorDAO getMetadataExtractorDAOImpl() throws IllegalArgumentException{
		try {
			if(this.implementingClass != null)
			  return (MetadataExtractorDAO) Class.forName(this.implementingClass).newInstance();
			else
				return null;
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static MetadataStandardEntity getById(int id){
		
		MetadataStandardEntity[] all = MetadataStandardEntity.values();
		
		return all[id-1];
	}
	
}
