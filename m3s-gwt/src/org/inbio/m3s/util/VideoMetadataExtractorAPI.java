package org.inbio.m3s.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class purpose is to get some information from the media File, but
 * information that isn't in any especific standard or is gotten in a different
 * way.
 * 
 * 
 * @author garias
 * 
 */

public class VideoMetadataExtractorAPI {
	
	//private static final String EXT_FILE_TEMP = ".txt";
	
	//private static final String EXE_FILE = "C:\\Documents and Settings\\garias\\Desktop\\MediaInfo_0.7.5.9_CLI_Win32\\MediaInfo.exe -full";
	
	private static final String TYPE_GENERAL = "General.*";
	
	private static final String TYPE_VIDEO = "Video.*";
	
	private static final String TYPE_SOUND = "Audio.*";
	
	private static final String FORMAT = "Format/Info.*:";
	
	private static final String ASPECT_RADIO = "Display Aspect ratio.*:.*[0-9]*.[0-9]*";
	
	private static final String HEAD_DURATION = ":.*[0-9][0-9]:[0-9][0-9]:[0-9][0-9].\\d*" ;
	
	private static final String DURATION = "PlayTime.*:.*[0-9][0-9]:[0-9][0-9]:[0-9][0-9].\\d*";
	
	private static final String PIXEL_HORIZONTAL = "Width.*:";
	
	private static final String PIXEL_VERTICAL = "Height.*:";
	
	private static final String SOUND = "Count of audio streams.*:";
	
	private static final String SIGNAL_FORMAT = "Codecs Video.*:";
	
	private static final String COMPRESSION = "Codec/Info.*:";
	
	private static final String AUDIO_DATA_ENCODING = "Codec/Family*.:"; 
	
	private static final String BITS_PER_SAMPLE = "Bits/*.*:";
	
	private static final String TARGGET_ID = "Tagged date.*:*UTC";
	
	private static final String NUM_CHANNELS = "Channe(s).*:";
	
	private static final String SAMPLING_FREQUENCY = "Bit rate.*:";
	
	private static final String HEAD_ASPECT_RADIO = "Display Aspect ratio.*:";
	
	private static final String EMPTY = ""; 
	
//FIXME!!!!! : urgente esto no puede quedar así!
	//private static final String EXE_DIR = "C:\\Documents and Settings\\garias\\Desktop\\MediaInfo_0.7.5.9_CLI_Win32\\MediaInfo.exe ";
	private static final String EXE_DIR ="/home/jgutierrez/escritorio[2008.02.28]/pruebaVideo_ffmpeg/MediaInfo_CLI_Linux_i386/mediainfo";
	
	private static String OPTION = "--full ";

	private String Patter = "";
	
	private String FitPatter = "";
	
	private String Type = "";
	
	public Process processTemp = null;
	
	public BufferedReader bufferRead = null;

	/**
	 * 
	 */
	public VideoMetadataExtractorAPI() {
		// TODO Auto-generated constructor stub
	}
	
	private String getInformation(String fileAddress) {
		
		try {
			
			BufferedReader myBufferRead=getBufferRead (fileAddress);
			boolean getType = false;
			String lineType = "";
			String line = "";
			String data = "";
			
			if (myBufferRead!=null){
					
				while ((lineType = myBufferRead.readLine()) != null) {
					
					if (getType=getStringType(lineType))
						break;
					
				}
				
				if (getType){
		
					while ((line = myBufferRead.readLine()) != null) {
						
							data = getStringInformation(line);
							if ( data.length() >0 )
								return data;
							
					}
				}
			}
			
			return data;
			
		}catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}//catch
		
	}//String

	private String getInformationPatter(String fileAddress) {
		
		try {
			
			BufferedReader myBufferRead=getBufferRead (fileAddress);
			boolean getType = false;
			String lineType = "";
			String line = "";
			String data = "";
			
			if (myBufferRead!=null){
					
				while ((lineType = myBufferRead.readLine()) != null) {
					
					if (getType=getStringType(lineType))
						break;
					
				}
				
				if (getType){
		
					while ((line = myBufferRead.readLine()) != null) {
						
							data = getStringInformationPatter(line);
							if ( data.length() >0 ){
								return getStringInformationFitPatter(data);
							}
					}
				}
			}
			
			return data;
			
		}catch (Exception e) {			
			e.printStackTrace();
			return e.getMessage();
		}//catch
		
	}//String

	/**
	 * 
	 * @param fileAddress
	 * @return BufferedReader
	 * 
	 * @author garias
	 * 
	 */
	private BufferedReader getBufferRead (String fileAddress){
		
		try {			
			String []exe = {EXE_DIR ,OPTION," FileName1 ",fileAddress};
			processTemp = Runtime.getRuntime().exec(exe);
			bufferRead = new BufferedReader (new InputStreamReader(processTemp.getInputStream()));
	
			return bufferRead;
			
		}catch (Exception e) {
			
			e.printStackTrace();
			return null;
			
		}
	}
	
	/*
	 * Gets type of information of video file
	 *
	 */
	
	private boolean getStringType(String line){
		
		Pattern pat=null;
		Matcher mat=null;
		pat=Pattern.compile(Type);
		mat=pat.matcher(line);
		if(mat.find())
			return true;
		return false;
	}
	
	/*
	 * Gets information of video file information
	 *
	 */
	
	private String getStringInformation(String line){
		
		Pattern pat=null;
		Matcher mat=null;
		//System.out.println("BUSCANDO A ["+Patter+"]EN ["+line+"]");
		pat=Pattern.compile(Patter);
		mat=pat.matcher(line);
		
		if(mat.find()){
		
			String data = mat.group();
			System.out.println("["+data+"]");
			return line.substring(data.length(),line.length());
		}
		
		return "";
	}
	private String getStringInformationPatter(String line){
		
		Pattern pat=null;
		Matcher mat=null;
		//System.out.println("BUSCANDO A ["+Patter+"]EN ["+line+"]");
		pat=Pattern.compile(Patter);
		mat=pat.matcher(line);
		
		if(mat.find()){
		
			String data = mat.group();
			System.out.println("["+data+"]");
			return data;
		}
		
		return "";
	}
	private String getStringInformationFitPatter(String line){
		
		Pattern pat=null;
		Matcher mat=null;
		System.out.println("{"+FitPatter+"}["+line+"]");
		pat=Pattern.compile(FitPatter);
		mat=pat.matcher(line);
		
		if(mat.find()){
		
			String data = mat.group();
			System.out.println("["+data+"]");
			return line.substring(data.length(),line.length());
		}
		
		return "";
	}
	

	 public String getFormatName(String fileAddress){
		
		Patter = FORMAT;
		FitPatter = EMPTY;
		Type = TYPE_GENERAL;
		return getInformation(fileAddress);
		
	}
	
	 public String getAspectRadio(String fileAddress){
			
			Patter = ASPECT_RADIO;
			FitPatter = HEAD_ASPECT_RADIO;
			Type = TYPE_VIDEO;
			return getInformationPatter(fileAddress);
	}
	 
	 public String getPixelsHorizontal(String fileAddress){
			
			Patter = PIXEL_HORIZONTAL;
			FitPatter = EMPTY;
			Type = TYPE_VIDEO;
			return getInformation(fileAddress);
			
	} 
	
	 public String getPixelsVertical(String fileAddress){
			
			Patter = PIXEL_VERTICAL;
			FitPatter = EMPTY;
			Type = TYPE_VIDEO;
			return getInformation(fileAddress);
			
	}
	 
	 public String getSound(String fileAddress){
			
			Patter = SOUND;
			FitPatter = EMPTY;
			Type = TYPE_GENERAL;
			return getInformation(fileAddress);
	}
	 
	 public String getSignalFormat(String fileAddress){
			
			Patter = SIGNAL_FORMAT;
			FitPatter = EMPTY;
			Type = TYPE_GENERAL;
			return getInformation(fileAddress);
			
	}
	 
	 public String getCompression(String fileAddress){
			
			Patter = COMPRESSION;
			FitPatter = EMPTY;
			Type = TYPE_VIDEO;
			return getInformation(fileAddress);
			
	}
	 
	 public String getAudioDataEncoding(String fileAddress){
			
			Patter = AUDIO_DATA_ENCODING;
			FitPatter = EMPTY;
			Type = TYPE_SOUND;
			return getInformation(fileAddress);
			
	}
	 
	 public String getBitsPerSample(String fileAddress){
			
			Patter = BITS_PER_SAMPLE;
			FitPatter = EMPTY;
			Type = TYPE_VIDEO;
			return getInformation(fileAddress);
			
	}
	 
	 public String getTaggetId(String fileAddress){
			
			Patter = TARGGET_ID;
			FitPatter = EMPTY;
			Type = TYPE_GENERAL;
			return getInformation(fileAddress);
			
	}
	 
	 public String getDuration(String fileAddress){
			
			Patter = DURATION;
			FitPatter = HEAD_DURATION;
			Type = TYPE_GENERAL;
			return getInformationPatter(fileAddress);
	}
	 
	 public String getNumChannels(String fileAddress){
			
			Patter = NUM_CHANNELS;
			FitPatter = EMPTY;
			Type = TYPE_SOUND;
			return getInformation(fileAddress);
			
	}
	 public String getSamplingFrequency(String fileAddress){
			
			Patter = SAMPLING_FREQUENCY;
			FitPatter = EMPTY;
			Type = TYPE_SOUND;
			return getInformation(fileAddress);
			
	}
	 
	public  void freeProcessTemp() {
		// TODO Auto-generated method stub
		 processTemp.destroy();
	}


}
