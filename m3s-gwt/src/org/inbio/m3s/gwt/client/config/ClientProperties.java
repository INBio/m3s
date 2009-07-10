/*
 * Constants.java
 *
 * Created on October 27, 2006, 11:09 AM
 *
 * Constants - wired/alambred :( -
 *
 * @author james
 */

package org.inbio.m3s.gwt.client.config;

public class ClientProperties {


	// Result of the uploadFile
	public static int OK = 0;

	public static int ERROR = 1;

	// Default values.... this must be loaded from the Properties file that is
	// on the server....
	// TODO
	public static Integer DEFAULT_LANGUAGE = new Integer(1);

	public static String DEFAULT_MEDIA_CATEGORY = "Imágenes";

	public static String DEFAULT_MEDIA_TYPE = "Cámara Digital";
	public static String DEFAULT_MEDIA_TYPE_KEY = "1";

	// for the GUI
	public static String DEFAULT_TEXTBOX_WIDTH = "250px";

	public static String DEFAULT_TEXTAREA_WIDTH = "370px";

	public static int DEFAULT_TEXT_AREA_LINES = 4;
	
	
	public static int IMAGE_MEDIA_CATEGORY_ID = 1;

	public static int AUDIO_MEDIA_CATEGORY_ID = 2;
	
	public static int VIDEO_MEDIA_CATEGORY_ID = 3;

}
