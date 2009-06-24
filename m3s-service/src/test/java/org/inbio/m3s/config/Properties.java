package org.inbio.m3s.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


/**
 * Configura que tipo de inyección de dependencias se hará para
 * las pruebas unitarias!.
 * 
 */
public class Properties {

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
	public static String METADATA_MANAGER = null;
	
	
	public static void init() throws ConfigurationException{
		PropertiesConfiguration config = new PropertiesConfiguration("m3s-service-test.properties");
		
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
		Properties.METADATA_MANAGER = (String) config.getProperty("metadata_manager");

	}
}