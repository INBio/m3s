/* Ara - capture species and specimen data
 *
 * Copyright (C) 2009  INBio ( Instituto Naciona de Biodiversidad )
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.inbio.m3s.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jgutierrez
 *
 * Based on dmartin code, in the GBIF customizable portal.
 */
public abstract class BaseDTOFactory implements DTOFactory {


    /**
     * 
     * @param entitiesList
     * @return
     */
	public List createDTOList(List entitiesList) {
		if(entitiesList==null)
			return null;
		List dtoList = new ArrayList();
		for (Object entity: entitiesList)
			dtoList.add(createDTO(entity));
		return dtoList;
	}

}