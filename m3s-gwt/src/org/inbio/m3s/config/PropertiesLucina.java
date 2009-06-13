package org.inbio.m3s.config;

/**
 * 
 * @author james TODO: This variables must be configured via software, using a
 *         config file an a program that loads the default values
 */
//public class PropertiesLarus
public class PropertiesLucina {

	// CONFIGURADO PARA LARUS && m3sINBio

	// CATALINA_HOME a copy of the result of this command:
	// $echo "$CATALINA_HOME"
	private final static String CATALINA_HOME = "/usr/share/tomcat5.5";

	// apache webapps dir
	private final static String REAL_WEB_DIR = CATALINA_HOME + "/webapps/m3sINBioFiles/";

	// web address
	private final static String WEB_ADDRESS = "http://lucina.inbio.ac.cr/m3sINBioFiles/";

	//imagenes originales,big y thumb.
	private final static String M3S_BASE_DIR = "/mnt/m3sImages/INBio/";

	// the temporal media files
	private final static String TEMP_FILES_DIR = "TEMP_MEDIA_DIR";

	private final static String IMPORT_FILES = "IMPORT_FILES";

	// m3s application public files- images and stuff like that
	public final static String web_APP_FILES = WEB_ADDRESS + "APP_FILES";

	// public addresses
	public static String REAL_TEMP_FILES_DIR = REAL_WEB_DIR + TEMP_FILES_DIR	+ "/";

	public static String WEB_TEMP_MEDIA_DIR = WEB_ADDRESS + TEMP_FILES_DIR + "/";

	public static String MEDIA_REAL_BASE_ADDRESS = M3S_BASE_DIR + "MEDIA/";

	public static String IMAGES_PUBLIC_WEB_BASE_ADDRESS = WEB_ADDRESS	+ "MEDIA/IMAGES/PUBLIC";

	public static String IMAGES_ORIGINAL_REAL_BASE_ADDRESS = M3S_BASE_DIR + "MEDIA/ORIGINAL";

	// public static String REAL_BATCH_MEDIA_DIR = "/media/sda5/Buzon/m3s/";
	public static String REAL_BATCH_MEDIA_DIR = "/root/m3sTempImages/INBio/";

	public static String REAL_IMPORT_FILES_DIR = REAL_WEB_DIR + IMPORT_FILES+ "/";

	public static String WEB_IMPORT_FILES_DIR = WEB_ADDRESS + IMPORT_FILES + "/";

	
	// params for the dispatchers
	public static String THUMB_IMAGES = "THUMB";

	public static String BIG_IMAGES = "BIG";

	public static String FILE_SEPARATOR = "/";


	// PERSON PROFILES
	//public static final Integer IMAGES_PROCESOR_PROFILE_ID = new Integer(12);

	//public static final Integer IMAGES_AUTHOR_PROFILE_ID = new Integer(21);

	//public static final Integer GATHERING_PROFILE_ID = new Integer(3);


	//Sitema Operativo: 'Debian', 'RedHat'.
	public static final int DEBIAN_FAMILY = 1;
	public static final int REDHAT_FAMILY = 2;
	public static final int OS_FAMILY = DEBIAN_FAMILY;
	
	
	//esta variable es inicializada automaticamente al empezar el programa.
	public static String MODULE_BASE_URL = "";
}