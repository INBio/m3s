/**
 * 
 */
package org.inbio.m3s.usecases.admin.impl;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;

import org.inbio.m3s.usecases.admin.MetadataExtractor;

/**
 * This class purpose is to get some information from the media File, but
 * information that isn't in any especific standard or is gotten in a different
 * way.
 * 
 * 
 * @author jgutierrez
 * 
 */
public class FileInfoMetadataExtractorImpl implements MetadataExtractor {

	public String fileAddress = null;

	// standard attributes:

	public static final int FILE_EXTENSION = 0;

	public static final int FILE_MIME_TYPE = 1;

	public static final int TOTAL_METADATA_ATTRIBUTES = 2;

	/**
	 * 
	 */
	public FileInfoMetadataExtractorImpl() {
		super();
	}
	
	/**
	 * @param fileAddress
	 */
	public void init(String fileAddress){
		this.fileAddress = fileAddress;
	}

	/**
	 * do not use this method for this standard. Is the samething as using
	 * getStringAttributeValue *
	 * 
	 * @param fileAddress
	 *          of the file in the file System
	 * @deprecated
	 */
	public Object getAttributeValue(int standardAttributeId, char resultType,
			String fileAddress) throws IllegalArgumentException {
		return getStringAttributeValue(standardAttributeId, fileAddress);
	}

	/**
	 * *
	 * 
	 * @param fileAddress
	 *          of the file in the file System
	 * @deprecated
	 */
	public String getStringAttributeValue(int standardAttributeId,
			String fileAddress) throws IllegalArgumentException {
		return null;
	}

	//	switch (standardAttributeId) {
	//	case 0:
	//		return getFileExtension(fileAddress);
	//	case 1:
	//		return getFileMIMEType(fileAddress);
	//	}

	//	throw new IllegalArgumentException(
	//			"El atributo no existe en el estándar 'FileInfo'");

	//}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.inbio.m3s.usecases.admin.MetadataExtractor#getAttributeValue(int)
	 */
	public String getAttributeValue(int standardAttributeId)
			throws IllegalArgumentException, IllegalStateException {

		if (this.fileAddress == null) {
			throw new IllegalStateException(
					"Debe invocar el metodo 'init'.");
		}

		switch (standardAttributeId) {
		case 0:
			return getFileExtension(this.fileAddress);
		case 1:
			return getFileMIMEType(this.fileAddress);
		}

		throw new IllegalArgumentException(
				"El atributo["+standardAttributeId+"] no existe en el estándar 'FileInfo'");
	}

	/**
	 * 
	 * @param FileName
	 * @return
	 */
	private String getFileExtension(String fileAddress) {
		File file = new File(fileAddress);
		String fileName = file.getName();
		int lastPeriod = fileName.lastIndexOf(".");

		if (lastPeriod != -1)
			return fileName.substring(lastPeriod + 1);
		else
			return "";

	}

	/**
	 * 
	 * @return
	 */
	private String getFileMIMEType(String fileAddress) {
		File file = new File(fileAddress);
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		return mimeTypesMap.getContentType(file);
		// return "image/jpg";

		// File f = new File("gumby.gif");
		// System.out.println("Mime Type of " + f.getName() + " is " +
		// new MimetypesFileTypeMap().getContentType(f));
		// expected output :
		// "Mime Type of gumby.gif is image/gif"
	}

}
