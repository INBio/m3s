package org.inbio.m3s;

import java.io.File;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifDirectory;
import com.drew.metadata.iptc.IptcDirectory;

import junit.framework.TestCase;

public class MetadataTest extends TestCase {

	 /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MetadataTest( String testName )
    {
        super( testName );
    }


    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
    	
    	try {
    		File jpegFile = new File("/home/james/Desktop/Foto.jpg");
			Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
			
			Directory exifDirectory = metadata.getDirectory(ExifDirectory.class); 
			String cameraMake = exifDirectory.getString(ExifDirectory.TAG_MAKE);
			System.out.println("Camara make: "+cameraMake);
			String cameraModel = exifDirectory.getString(ExifDirectory.TAG_MODEL);
			System.out.println("Camara model: "+cameraModel);
			Directory iptcDirectory = metadata.getDirectory(IptcDirectory.class); 
			String caption = iptcDirectory.getString(IptcDirectory.TAG_CAPTION);
			System.out.println("Caption: "+caption);
		} catch (JpegProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
        assertTrue( true );
    }
    
}
