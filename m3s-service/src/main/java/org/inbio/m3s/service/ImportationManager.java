/**
 * 
 */
package org.inbio.m3s.service;

import java.util.List;

import org.inbio.m3s.dto.importcontrol.ImportControlDTOFull;
import org.inbio.m3s.dto.importcontrol.ImportControlDTOLite;

/**
 * @author jgutierrez
 *
 */
public interface ImportationManager {
	
	// IMPORT STATUS
	public static final String IMPORT_SCHEDULED_FOR = "Programada para: ";

	public static final String IMPORT_IN_PROGRESS = "En progreso";

	public static final String IMPORT_DONE = "Concluida: ";


	public List<ImportControlDTOFull> getImportControlDTOFullList(String username, int quantity) throws IllegalArgumentException;
	
	public ImportControlDTOLite getImportControlDTOLite(String username, String systemFileName) throws IllegalArgumentException;

	public ImportControlDTOFull getImportControlDTOFull(String username, String systemFileName) throws IllegalArgumentException;
	
	public void createImportControl(ImportControlDTOLite icLite) throws IllegalArgumentException;
	
	public void updateImportControl(ImportControlDTOLite icLite) throws IllegalArgumentException;

		
}
