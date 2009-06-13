package org.inbio.m3s.converters;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jgutierrez
 *
 */
public abstract class BaseDTOGWTFactory implements DTOConverter {


    /**
     * 
     * @param entitiesList
     * @return
     */
	public List createDTOGWTList(List dtosList) {
		if(dtosList==null)
			return null;
		List dtogwtList = new ArrayList();
		for (Object dto: dtosList)
			dtogwtList.add(createDTOGWT(dto));
		return dtogwtList;
	}

}