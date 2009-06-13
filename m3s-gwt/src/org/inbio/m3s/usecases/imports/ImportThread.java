/**
 * 
 */
package org.inbio.m3s.usecases.imports;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.inbio.m3s.dto.importcontrol.ImportControlDTOLite;
import org.inbio.m3s.service.ImportationManager;
import org.inbio.m3s.util.ServiceUtil;

/**
 * @author jgutierrez
 * 
 */
public class ImportThread extends Thread {
	
	private int fileType;

	private String username;

	private String systemFileName;
	
	private ImportationManager importationManager = (ImportationManager) ServiceUtil.appContext.getBean("importationManager");


	private static Logger logger = Logger.getLogger(ImportThread.class);
	
	/**
	 * Class constructor
	 * 
	 * @param fileType
	 *            a class Constant of ImportFromFile class
	 * @param username
	 * @param systemFileName
	 *            the name given to the file by the system
	 * @param userFileName
	 *            the name of the file for the user
	 */
	public ImportThread(int fileType, String username, String systemFileName,
			String userFileName) {
		this.fileType = fileType;
		this.username = username;
		this.systemFileName = systemFileName;

		// set the status to ...
		//ImportControlDAO icDAO = ImportControlDAOFactory.createImportControlDAOImpl();
		ImportControlDTOLite icLite = new ImportControlDTOLite(systemFileName,ImportationManager.IMPORT_SCHEDULED_FOR+"hoy", username,userFileName);

		importationManager.createImportControl(icLite);
		//icDAO.createImportControl(icLite);
		
	}

	/**
	 * Thread invocation to start working
	 */
	public void run() {
		logger.debug("el hilo bretando...");
		
		// set the status to ...
		ImportControlDTOLite icLite = importationManager.getImportControlDTOLite(username, systemFileName);

		// set the status to procesando...
		icLite.setStatus(ImportationManager.IMPORT_IN_PROGRESS);
		importationManager.updateImportControl(icLite);


		try {

			ImportFromFile.ImportMedia(systemFileName, fileType);

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
}
