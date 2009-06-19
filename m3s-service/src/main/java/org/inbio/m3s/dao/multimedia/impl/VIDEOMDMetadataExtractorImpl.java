package org.inbio.m3s.dao.multimedia.impl;

import org.apache.log4j.Logger;
import org.inbio.m3s.dao.multimedia.MetadataExtractorDAO;
import org.inbio.m3s.util.VideoMetadataExtractorAPI;

/**
 *Interface for the VideoExtractorAPI created by garias
 * 
 * @author jgutierrez
 *
 */
public class VIDEOMDMetadataExtractorImpl implements MetadataExtractorDAO {

	public static final int FORMAT_NAME = 0;
	
	public static final int ASPECT_RADIO = 1;
	
	public static final int PIXELS_HORIZONTAL = 2;
	
	public static final int PIXELS_VERTICAL = 3;
	
	public static final int SAMPLING = 4;
	
	public static final int SCAN = 5;
	
	public static final int SOUND = 6;
	
	public static final int SIGNAL_FORMAT = 7;
	
	public static final int COMPRESION = 8;
	
	public static final int AUDIO_DATA_ENCODING = 9;

	public static final int BIT_PER_SAMPLING = 10;
	
	public static final int TAGGET_ID = 11;
	
	public static final int DURATION = 12;
	
	public static final int NUM_CHANNELS = 13;
	
	public static final int SAMPLING_FREQUENCY = 14;
	
	VideoMetadataExtractorAPI MetaDataFile = new VideoMetadataExtractorAPI();
	
	private String fileAddress = null;
	
	private static Logger logger = Logger.getLogger(VIDEOMDMetadataExtractorImpl.class);
	
	/**
	 * This array holds the name of the atributes in the VIDEOMD standard. The name of
	 * the attribute is gotten using the number of the attribute as the index in
	 * the array. For example VIDEOMD_ATTRIBUTE_NAME[LIGHT_SOURCE] = "LightSource"
	 */
	
	private static final String[] VIDEOMD_ATTRIBUTE_NAME = 
	{"Format name","aspet_radio", "pixels_horizontal",
	"pixels_vertical","samplin","scan","sound","signal_format",
	"compresion","auto_data_encoding","bit_per_samplig", 
	"tagget_id","duration","num_channes","sampling_frequency"};
	
	/**
	 * 
	 */
	public VIDEOMDMetadataExtractorImpl() {
		// TODO Auto-generated constructor stub
	}
	

	/**
	 * do not use this method for this standard. Is the something as using
	 * getStringAttributeValue
	 */
	
	  /*public Object getAttributeValue(int standardAttributeId, char resultType,
			String fileAddress) throws IllegalArgumentException {
		return getStringAttributeValue(standardAttributeId, fileAddress);
	  }*/
	  
	
	/**
	 * Gets the value of the attribute as a String *
	 * 
	 * @param fileAddress
	 *          of the file in the file System
	 * 
	 */	
	
	public String getStringAttributeValue(int standardAttributeId,
			String fileAddress) throws IllegalArgumentException { 
		
		try {
			
			switch (standardAttributeId) {
			
				case  FORMAT_NAME:
					 String fn = MetaDataFile.getFormatName(fileAddress);
					 return (fn);
			
				case  ASPECT_RADIO:
					String asr = MetaDataFile.getAspectRadio(fileAddress);
					return (asr);
				
				case  PIXELS_HORIZONTAL:
					String pixH = MetaDataFile.getPixelsHorizontal(fileAddress);
					return (pixH);
					
				case  PIXELS_VERTICAL:
					String pixV = MetaDataFile.getPixelsVertical(fileAddress);
					return (pixV);
					
				case  SAMPLING:
					String samplin = MetaDataFile.getSamplingFrequency(fileAddress);
					return samplin;
								
				case  SOUND:
					String sound = MetaDataFile.getSound(fileAddress);
					return sound;
					
				case  SIGNAL_FORMAT:
					String sig_f = MetaDataFile.getSignalFormat(fileAddress);
					return sig_f;
					
				case  COMPRESION:
					String comp = MetaDataFile.getCompression(fileAddress);
					return  comp;
					
				case  AUDIO_DATA_ENCODING:
					String audi = MetaDataFile.getAudioDataEncoding(fileAddress);
					return audi;
				
				case  TAGGET_ID:
					String tag = MetaDataFile.getTaggetId(fileAddress);
					return tag;
					
				case  DURATION:
					String duration = MetaDataFile.getDuration(fileAddress);
					return duration;
					
				case  NUM_CHANNELS:
					String num_c = MetaDataFile.getNumChannels(fileAddress);
					return num_c;
					
				case  SAMPLING_FREQUENCY:
					String samp_freq = MetaDataFile.getSamplingFrequency(fileAddress);
					return samp_freq;
					
				default:
						return	getVIDEOMDAttributeName(standardAttributeId);
			}
			
		}catch (Exception e) {
			throw new IllegalArgumentException(
					"El atributo no existe en el estándar 'VIDEOMD'");
		}
		
	}

	/**
	 * falta documentar
	 * 
	 * @param attributeNumber
	 */	
		private String getVIDEOMDAttributeName(int attributeNumber) {
			try {
				return VIDEOMD_ATTRIBUTE_NAME[attributeNumber];
			} catch (Exception e) {
				return "unknown";
			}
		}


		/**
		 * @NIUS
		 */
	public Object getAttributeValue(int standardAttributeId, char resultType, String fileAddress) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


		public String getAttributeValue(int standardAttributeId) throws IllegalArgumentException, IllegalStateException {
			return getStringAttributeValue(standardAttributeId, this.fileAddress);
		}


		/**
		 * inits the basic things of the class
		 * 
		 */
		public void init(String fileAddress) {
			logger.debug("iniciando la clase");
			this.fileAddress = fileAddress;
			
		}

}
