/**
 * 
 */
package org.inbio.m3s.gwt.server.rpcimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.config.Properties;
import org.inbio.m3s.config.UserProfile;
import org.inbio.m3s.dto.importcontrol.ImportControlDTOFull;
import org.inbio.m3s.dto.metadata.util.ImportationFileEntity;
import org.inbio.m3s.gwt.client.rpcinterface.ImportRPC;
import org.inbio.m3s.gwt.client.widgets.importation.dto.ImportInfo;
import org.inbio.m3s.service.ImportationManager;
import org.inbio.m3s.service.util.ImportThread;
import org.inbio.m3s.util.OSCommand;
import org.inbio.m3s.util.ServiceUtil;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author jgutierrez
 * 
 */
public class ImportRPCImpl extends RemoteServiceServlet implements ImportRPC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ImportRPCImpl.class);
	
	private ImportationManager importationManager = (ImportationManager) ServiceUtil.appContext.getBean(Properties.IMPORTATION_MANAGER);
	private ImportThread importThread = (ImportThread) ServiceUtil.appContext.getBean(Properties.IMPORT_THREAD);
	
	/**
	 * @param username
	 *            user which import control information is requested
	 * @return a list of ImportResultData elements to be displayed on the table
	 */
	public List<ImportInfo> getResultTableData(String userName) {
		
		List<ImportControlDTOFull> icList = importationManager.getImportControlDTOFullList(userName, 20);
		
		List<ImportInfo> result = new ArrayList<ImportInfo>();
		ImportInfo elem = null;
		
		for(ImportControlDTOFull icFull : icList){
			elem = new ImportInfo(icFull.getUserFileName(),icFull.getStatus(),
					((Date) icFull.getCreationDate()).toString(),
					Properties.WEB_IMPORT_FILES_DIR+icFull.getSystemFileName());
			result.add(elem);
		}
		
		return result;
	}

	/**
	 * @param usernameImportOwner
	 * @param tempFileId
	 * @deprecated
	 */
	public void executeImport(String usernameImportOwner, String tempFileId) {

		// Sets the UserProfile
		UserProfile.setUsername(usernameImportOwner);

		// Moves the import file to the imports file dir and renames it...
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String systemFileName = timestamp.toString() + tempFileId;
		systemFileName = systemFileName.replace(' ', '-');

		String tempFileAddress = Properties.REAL_TEMP_FILES_DIR + tempFileId;

		String systemFileAddress = Properties.REAL_IMPORT_FILES_DIR
				+ systemFileName;

		logger.debug("mv " + tempFileAddress + " " + systemFileAddress);

		String[] cmd = { "mv", tempFileAddress, systemFileAddress };
		OSCommand.run(cmd);

		importThread.run(ImportationFileEntity.MS_EXCEL_FILE,
				UserProfile.getUsername(), systemFileName, tempFileId);

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
	 * @return the importThread
	 */
	public ImportThread getImportThread() {
		return importThread;
	}

	/**
	 * @param importThread the importThread to set
	 */
	public void setImportThread(ImportThread importThread) {
		this.importThread = importThread;
	}
}
