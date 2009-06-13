package org.inbio.m3s.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


/**
 * 
 * @author james TODO: This variables must be configured via software, using a
 *         config file an a program that loads the default values
 */
public class Properties {

	// CONFIGURADO PARA COFFEA con m3sINBio

	// CATALINA_HOME a copy of the result of this command:
	// $echo "$CATALINA_HOME"
	private static String CATALINA_HOME = null;
	//"/webapps/m3sINBioFiles/"
	private static String TEMP_WEB_DIR = null;
	//web address
	private static String WEB_ADDRESS = null;
	//imagenes originales,big y thumb.
	private static String M3S_BASE_DIR = null;

	// the temporal media files
	private static String TEMP_FILES_DIR = null;
	private static String IMPORT_FILES = null;
	//private static String MEDIA_FILES_DIR = null;
	
	private static String APP_FILES = null;
	
	// public static String REAL_BATCH_MEDIA_DIR = "/media/sda5/Buzon/m3s/";
	public static String REAL_BATCH_MEDIA_DIR = null;
	

	//Estas se setean automáticamente a partir de las variables de arriba:
	// apache webapps dir
	private static String REAL_WEB_DIR = CATALINA_HOME + TEMP_WEB_DIR;
	// m3s application public files- images and stuff like that
	public static String web_APP_FILES = WEB_ADDRESS + APP_FILES;

	// public addresses
	public static String REAL_TEMP_FILES_DIR = REAL_WEB_DIR + TEMP_FILES_DIR	+ "/";
	public static String WEB_TEMP_MEDIA_DIR = WEB_ADDRESS + TEMP_FILES_DIR + "/";
	public static String MEDIA_REAL_BASE_ADDRESS = M3S_BASE_DIR + "MEDIA/";
	
	public static String IMAGES_PUBLIC_WEB_BASE_ADDRESS = WEB_ADDRESS	+ "MEDIA/IMAGES/PUBLIC";

	public static String IMAGES_ORIGINAL_REAL_BASE_ADDRESS = M3S_BASE_DIR + "MEDIA/ORIGINAL";

	// public static String REAL_BATCH_MEDIA_DIR = "/media/sda5/Buzon/m3s/";
	public static String REAL_IMPORT_FILES_DIR = REAL_WEB_DIR + IMPORT_FILES+ "/";
	public static String WEB_IMPORT_FILES_DIR = WEB_ADDRESS + IMPORT_FILES + "/";

	
	// params for the dispatchers
	public static String THUMB_IMAGES = "THUMB";

	public static String BIG_IMAGES = "BIG";

	public static String FILE_SEPARATOR = "/";


	
	//Sitema Operativo: 'Debian', 'RedHat'.
	public static final int DEBIAN_FAMILY = 1;
	public static final int REDHAT_FAMILY = 2;
	public static final int OS_FAMILY = DEBIAN_FAMILY; 
	

	//esta variable es inicializada automaticamente al empezar el programa.
	public static String MODULE_BASE_URL = "";
	
	
	//Managers to be used (from m3s-service)
	public static String AGENT_MANAGER = null;
	public static String IMPORTATION_MANAGER = null;
	public static String MEDIA_ATTRIBUTE_MANAGER = null;
	public static String MEDIA_MANAGER = null;
	public static String MESSAGE_MANAGER = null;
	public static String SECURITY_MANAGER = null;
	public static String SITE_MANAGER = null;
	public static String STATISTICS_MANAGER = null;
	public static String TAXONOMY_MANAGER = null;
	public static String IMPORT_FROM_FILE = null;
	
	public static void init() throws ConfigurationException{
		PropertiesConfiguration config = new PropertiesConfiguration("m3s.properties");
		
		Properties.CATALINA_HOME = (String) config.getProperty("catalina_home");
		Properties.TEMP_WEB_DIR = (String) config.getProperty("temp_web_dir");

		Properties.WEB_ADDRESS = (String) config.getProperty("web_address");
		Properties.M3S_BASE_DIR = (String) config.getProperty("m3s_base_dir");

		Properties.TEMP_FILES_DIR = (String) config.getProperty("temp_files_dir");
		Properties.IMPORT_FILES = (String) config.getProperty("import_files_dir");
		//Properties.MEDIA_FILES_DIR = (String) config.getProperty("media_files_dir");
		
		Properties.APP_FILES = (String) config.getProperty("app_files");
		
		Properties.REAL_BATCH_MEDIA_DIR = (String) config.getProperty("real_batch_media_dir");
		
		Properties.AGENT_MANAGER = (String) config.getProperty("agent_manager");
		Properties.IMPORTATION_MANAGER = (String) config.getProperty("importation_manager");
		Properties.MEDIA_ATTRIBUTE_MANAGER = (String) config.getProperty("media_attibute_manager");
		Properties.MEDIA_MANAGER = (String) config.getProperty("media_manager");
		Properties.MESSAGE_MANAGER = (String) config.getProperty("message_manager");
		Properties.SECURITY_MANAGER = (String) config.getProperty("security_manager");
		Properties.SITE_MANAGER = (String) config.getProperty("site_manager");
		Properties.STATISTICS_MANAGER = (String) config.getProperty("statistics_manager");
		Properties.TAXONOMY_MANAGER = (String) config.getProperty("taxonomy_manager");
		Properties.IMPORT_FROM_FILE = (String) config.getProperty("import_from_file");

		
		


	}
}