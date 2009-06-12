/**
 * 
 */
package org.inbio.m3s.dto.importcontrol;

import org.inbio.m3s.dto.BaseDTOFactory;
import org.inbio.m3s.model.core.ImportControl;

/**
 * @author jgutierrez
 *
 */
public class ImportControlDTOLiteFactory extends BaseDTOFactory {

	/* (non-Javadoc)
	 * @see org.inbio.m3s.dto.DTOFactory#createDTO(java.lang.Object)
	 */
	public Object createDTO(Object entity) {
		if(entity == null)
			return null;
	ImportControl ic = (ImportControl) entity;
	ImportControlDTOLite dto = new ImportControlDTOLite();
	populateImportControl(ic, dto);
	return dto;
	}

	/**
	 * Copy properties from model object to dto.
	 * @param ic
	 * @param dto
	 */
	public void populateImportControl(ImportControl ic, ImportControlDTOLite dto){
		
		dto.setSystemFileName(ic.getId().getSystemFileName());
		dto.setStatus(ic.getStatus());
		dto.setUsername(ic.getId().getUserName());
		dto.setUserFileName(ic.getUserFileName());
				
	}


}
