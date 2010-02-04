/**
 * 
 */
package org.inbio.m3s.service.impl;


import java.util.List;

import org.inbio.m3s.config.Properties;
import org.inbio.m3s.dto.importcontrol.ImportControlDTOFull;
import org.inbio.m3s.dto.importcontrol.ImportControlDTOLite;
import org.inbio.m3s.service.AbstractServiceTest;
import org.inbio.m3s.service.ImportationManager;


/**
 * @author jgutierrez
 *
 */
@SuppressWarnings("unused")
public class ImportationManagerTest extends AbstractServiceTest{

	
	public void testCreateAndUpdate(){
		
		ImportationManager im = (ImportationManager) getBean(Properties.IMPORTATION_MANAGER);		
		ImportControlDTOLite icLite = new ImportControlDTOLite("aguacate file", ImportationManager.IMPORT_IN_PROGRESS,"jgutierrez","archivo aguacate");
		List<ImportControlDTOFull> icList;
	 	
		icList= im.getImportControlDTOFullList("jgutierrez", 7);
		System.out.println("antes>"+icList.size());
		
		//System.out.println("borrandolo si existe!");
		//importControlDAO.delete(entity)
		//im.createImportControl(icLite);
		
		System.out.println("creado!");
		//im.createImportControl(icLite);
		
		icList= im.getImportControlDTOFullList("jgutierrez", 7);
		System.out.println("despues>"+icList.size());
		
	}


}
