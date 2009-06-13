
package org.inbio.m3s.converters;

import java.util.List;

/**
 *
 * @author jgutierrez
 */
public interface DTOConverter {
    
    /**
     * 
     * @param dto
     * @return
     */
	public Object createDTOGWT(Object dto);

	/**
     *
     * @param dtosList
     * @return
     */
	public List createDTOGWTList(List dtosList);
}