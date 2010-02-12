/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.GenericBaseDAO;
import org.inbio.m3s.model.core.ImportControl;
import org.inbio.m3s.model.core.ImportControlId;

/**
 * @author jgutierrez
 *
 */
public interface ImportControlDAO extends GenericBaseDAO<ImportControl, ImportControlId>{

	public List<ImportControl> getImportControlList(String username, int quantity) throws IllegalArgumentException;
	
	public ImportControl findBySystemFileName(String systemFileName) throws IllegalArgumentException;
	
	
}
