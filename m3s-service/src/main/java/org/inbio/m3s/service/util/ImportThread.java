/**
 * 
 */
package org.inbio.m3s.service.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.inbio.m3s.dto.importcontrol.ImportControlDTOLite;
import org.inbio.m3s.dto.metadata.util.ImportationFileEntity;
import org.inbio.m3s.service.ImportationManager;

/**
 * @author jgutierrez
 * 
 */
public class ImportThread extends Thread {
	
	private ImportationManager importationManager;
	private ImportFromFile importFromFile;


	private static Logger logger = Logger.getLogger(ImportThread.class);
	

	/**
	 * 
	 * 
	 * Thread invocation to start working
	 * 
	 * @param importationBatchMediaPath real path where the media is going to be ready for the importation
	 * @param mediaFilesPath the path where the media will be stored
	 */
	public void run(ImportationFileEntity fileType, String username, String systemFileName, String userFileName, String realImportFilesDir, String importationBatchMediaPath,String mediaFilesPath) {

		// set the status to ...
		ImportControlDTOLite icLite = new ImportControlDTOLite(systemFileName,ImportationManager.IMPORT_SCHEDULED_FOR+"hoy", username,userFileName);

		importationManager.createImportControl(icLite);
		
		logger.debug("el hilo bretando...");
		// set the status to procesando...
		icLite.setStatus(ImportationManager.IMPORT_IN_PROGRESS);
		importationManager.updateImportControl(icLite);

		
		
		try {

			importFromFile.ImportMedia(realImportFilesDir + systemFileName, fileType, importationBatchMediaPath,mediaFilesPath);

			// sets the status to terminado...
			// set the status to procesando...
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String literalDate = dateFormat.format(new java.util.Date())
					.toString();
			
			icLite.setStatus(ImportationManager.IMPORT_DONE + literalDate);
			importationManager.updateImportControl(icLite);

		} catch (FileNotFoundException fnfe) {
			logger.error("FileNotFoundException");
			logger.error(fnfe.getMessage());
			icLite.setStatus("Error: FileNotFoundException");
			importationManager.updateImportControl(icLite);
		} catch (IOException ioe) {
			logger.error("IOException");
			logger.error(ioe.getMessage());
			icLite.setStatus("Error: IOException");
			importationManager.updateImportControl(icLite);
		} catch (IllegalArgumentException iae) {
			logger.error("IllegalArgumentException");
			logger.error(iae.getMessage());
			icLite.setStatus("Error: IllegalArgumentException");
			importationManager.updateImportControl(icLite);
		}

	}
	
	/**
	 * @param importationManager the importationManager to set
	 */
	public void setImportationManager(ImportationManager importationManager) {
		this.importationManager = importationManager;
	}

	/**
	 * @return the importationManager
	 */
	public ImportationManager getImportationManager() {
		return importationManager;
	}

	/**
	 * @return the importFromFile
	 */
	public ImportFromFile getImportFromFile() {
		return importFromFile;
	}

	/**
	 * @param importFromFile the importFromFile to set
	 */
	public void setImportFromFile(ImportFromFile importFromFile) {
		this.importFromFile = importFromFile;
	}
}
