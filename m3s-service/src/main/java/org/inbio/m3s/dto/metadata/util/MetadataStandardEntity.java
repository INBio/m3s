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
	MET(16, "MET",VIDEOMDMetadataExtractorImpl.class.getName());
	
	private int id;
	private String name;
	private String implementingClass;
	
	/**
	 * @param id
	 * @param name
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
	
	public MetadataExtractorDAO getMetadataExtractorDAOImpl() throws IllegalArgumentException{
		try {
			return (MetadataExtractorDAO) Class.forName(this.implementingClass).newInstance();
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
