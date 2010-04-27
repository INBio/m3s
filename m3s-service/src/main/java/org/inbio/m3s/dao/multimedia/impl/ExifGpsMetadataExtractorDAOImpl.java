/**
 * 
 */
package org.inbio.m3s.dao.multimedia.impl;

import java.io.File;

import org.apache.log4j.Logger;
import org.inbio.m3s.dao.multimedia.MetadataExtractorDAO;
import org.inbio.m3s.dto.metadata.util.MediaAttributeEntity;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.lang.Rational;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.GpsDirectory;

/**
 * @author jgutierrez
 *
 */
public class ExifGpsMetadataExtractorDAOImpl implements MetadataExtractorDAO{

	private static Logger logger = Logger.getLogger(ExifGpsMetadataExtractorDAOImpl.class);
	Directory gpsDirectory = null;
	String fileAddress = null;

	
	public void init(String fileAddress) throws IllegalArgumentException {
		logger.info("using the fileAddress: '"+fileAddress+"'");
		this.fileAddress =fileAddress;
    
		try {
    	File jpegFile = new File(fileAddress);
    	Metadata metadata = JpegMetadataReader.readMetadata(jpegFile); 
    	this.gpsDirectory = metadata.getDirectory(GpsDirectory.class);
    	
    } catch (JpegProcessingException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("The fileAddress ["+fileAddress+"] doesn't exist. ",e.getCause());
		}

	}

	
	
	public String getAttributeValue(int standardAttributeId)
			throws IllegalArgumentException, IllegalStateException {
		
		logger.debug("with standard attribute id  '"+standardAttributeId+"'");
		
		if (this.gpsDirectory == null)
			throw new IllegalStateException("Debe invocar el metodo init.");
		
		else if (standardAttributeId == MediaAttributeEntity.GPS_LATITUDE.getMediaAtributeId()){
			
			logger.debug("gps latitude");
			try{
				Rational[] lat = gpsDirectory.getRationalArray(GpsDirectory.TAG_GPS_LATITUDE);
				Double lats = lat[0].doubleValue() + lat[1].doubleValue()/60 + lat[2].doubleValue()/3600;
			
				if(!gpsDirectory.getString(GpsDirectory.TAG_GPS_LATITUDE_REF).equalsIgnoreCase("N"))
					lats = lats * -1; 
			
				return String.valueOf(lats);
			} catch(MetadataException me){
				throw new IllegalArgumentException("atributo invalido");	
			}

		}
		
		else if (standardAttributeId == MediaAttributeEntity.GPS_LONGITUDE.getMediaAtributeId()){
			
			logger.debug("gps longitude");
			try{
			
				Rational[] lon = gpsDirectory.getRationalArray(GpsDirectory.TAG_GPS_LONGITUDE);
			
				Double lons = lon[0].doubleValue() + lon[1].doubleValue()/60 + lon[2].doubleValue()/3600;
			
				if(gpsDirectory.getString(GpsDirectory.TAG_GPS_LONGITUDE_REF).equalsIgnoreCase("W"))
					lons = lons * -1; 
		
				return String.valueOf(lons);
				
			} catch(MetadataException me){
				throw new IllegalArgumentException("atributo invalido");	
			}
		}
		
		
		else
			throw new IllegalArgumentException("atributo invalido");

	}
	
}
