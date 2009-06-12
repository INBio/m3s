/**
 * 
 */
package org.inbio.m3s.dao.core;

import java.util.List;

import org.inbio.m3s.dao.BaseDAO;
import org.inbio.m3s.model.core.ImportControl;

/**
 * @author jgutierrez
 *
 */
public interface ImportControlDAO extends BaseDAO{

	public List<ImportControl> getImportControlList(String username, int quantity) throws IllegalArgumentException;
	
}
