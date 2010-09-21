/**
 * 
 */
package org.inbio.m3s.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.inbio.m3s.dao.core.ImportControlDAO;
import org.inbio.m3s.dto.importcontrol.ImportControlDTOFull;
import org.inbio.m3s.dto.importcontrol.ImportControlDTOFullFactory;
import org.inbio.m3s.dto.importcontrol.ImportControlDTOLite;
import org.inbio.m3s.dto.importcontrol.ImportControlDTOLiteFactory;
import org.inbio.m3s.model.core.ImportControl;
import org.inbio.m3s.model.core.ImportControlId;
import org.inbio.m3s.service.ImportationManager;

/**
 * @author jgutierrez
 * 
 */
public class ImportationManagerImpl implements ImportationManager {

	protected static Log logger = LogFactory.getLog(ImportationManagerImpl.class);

	private ImportControlDAO importControlDAO;
	
	private ImportControlDTOFullFactory importControlFullDTOFactory;
	
	private ImportControlDTOLiteFactory importControlLiteDTOFactory;

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.ImportationManager#getImportControlDTOFullList(java.lang.String, int)
	 */
	public List<ImportControlDTOFull> getImportControlDTOFullList(String username,
			int quantity) throws IllegalArgumentException {

		logger.debug("getImportationData() with username " + username);
		List<ImportControl> icList = importControlDAO.getImportControlList(
				username, quantity);
		logger.debug("total ImportControls = " + icList.size());
		return importControlFullDTOFactory.createDTOList(icList);
	}

	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.ImportationManager#getImportControlDTOLite(java.lang.String, java.lang.String)
	 */
	public ImportControlDTOLite getImportControlDTOLite(String username,
			String systemFileName) throws IllegalArgumentException {
		logger.debug("getImportControlDTOLite() with username " + username);
		
		ImportControlId icId = new ImportControlId(systemFileName,username); 
		ImportControl ic = (ImportControl) importControlDAO.findById(ImportControl.class, icId);
		if(ic == null)
			throw new IllegalArgumentException("no import control information");
		
		return (ImportControlDTOLite) getImportControlLiteDTOFactory().createDTO(ic);

	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.ImportationManager#getImportControlDTOFull(java.lang.String, java.lang.String)
	 */
	public ImportControlDTOFull getImportControlDTOFull(String username,
			String systemFileName) throws IllegalArgumentException {
		logger.debug("getImportControlDTOFull() with username " + username);
		
		ImportControlId icId = new ImportControlId(systemFileName,username); 
		ImportControl ic = (ImportControl) importControlDAO.findById(ImportControl.class, icId);
		if(ic == null)
			throw new IllegalArgumentException("no import control information");
		
		return (ImportControlDTOFull) getImportControlFullDTOFactory().createDTO(ic);

	}	
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.ImportationManager#getImportControlDTOFull(java.lang.String)
	 */
	public ImportControlDTOFull getImportControlDTOFull(String systemFileName) throws IllegalArgumentException{
		logger.debug("getImportControlDTOFull with systemFileName " + systemFileName);
		 
		ImportControl ic = importControlDAO.findBySystemFileName(systemFileName);
		if(ic == null)
			throw new IllegalArgumentException("no import control information");
		
		return (ImportControlDTOFull) getImportControlFullDTOFactory().createDTO(ic);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.ImportationManager#createImportControl(org.inbio.m3s.dto.importcontrol.ImportControlDTOLite)
	 */
	public void createImportControl(ImportControlDTOLite icLite)
			throws IllegalArgumentException {

		logger.debug("createImportControl... start");
		
		//SecurityUsers su = (SecurityUsers) securityUserDAO.findById(SecurityUsers.class, icLite.getUsername());
		//if(su == null){
		//	throw new IllegalArgumentException("not valid username: "+icLite.getUsername());
		//}
		ImportControlId icId = new ImportControlId(icLite.getSystemFileName(), icLite.getUsername());
		ImportControl ic = new ImportControl(icId, icLite.getStatus(), icLite.getUserFileName());
		
		// TODO the log of the database tables is not OK
		// sets the date of the creation
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String literalDate = dateFormat.format(new java.util.Date()).toString();
		try {
			Date date = dateFormat.parse(literalDate);
			ic.setCreationDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		importControlDAO.create(ic);
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.service.ImportationManager#updateImportControl(org.inbio.m3s.dto.importcontrol.ImportControlDTOLite)
	 */
	public void updateImportControl(ImportControlDTOLite icLite)
			throws IllegalArgumentException {
		
		logger.debug("updateImportControl... start");
		
		ImportControlId icId = new ImportControlId(icLite.getSystemFileName(), icLite.getUsername());
		ImportControl ic = (ImportControl) importControlDAO.findById(ImportControl.class, icId);
		ic.setLastModificationBy(icLite.getUsername());
		ic.setUserFileName(icLite.getUserFileName());
		ic.setStatus(icLite.getStatus());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String literalDate = dateFormat.format(new java.util.Date()).toString();
		try {
			Date date = dateFormat.parse(literalDate);
			ic.setLastModificationDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		importControlDAO.update(ic);
		
	}

	/**
	 * @param importControlDAO
	 *          the importControlDAO to set
	 */
	public void setImportControlDAO(ImportControlDAO importControlDAO) {
		this.importControlDAO = importControlDAO;
	}

	/**
	 * @return the importControlDAO
	 */
	public ImportControlDAO getImportControlDAO() {
		return importControlDAO;
	}

	/**
	 * @return the importControlFullDTOFactory
	 */
	public ImportControlDTOFullFactory getImportControlFullDTOFactory() {
		return importControlFullDTOFactory;
	}

	/**
	 * @param importControlFullDTOFactory the importControlFullDTOFactory to set
	 */
	public void setImportControlFullDTOFactory(
			ImportControlDTOFullFactory importControlFullDTOFactory) {
		this.importControlFullDTOFactory = importControlFullDTOFactory;
	}

	/**
	 * @return the importControlLiteDTOFactory
	 */
	public ImportControlDTOLiteFactory getImportControlLiteDTOFactory() {
		return importControlLiteDTOFactory;
	}

	/**
	 * @param importControlLiteDTOFactory the importControlLiteDTOFactory to set
	 */
	public void setImportControlLiteDTOFactory(
			ImportControlDTOLiteFactory importControlLiteDTOFactory) {
		this.importControlLiteDTOFactory = importControlLiteDTOFactory;
	}

	
}
