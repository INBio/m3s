/**
 * 
 */
package org.inbio.m3s.web.converter;

import java.util.List;

import org.inbio.m3s.dto.BaseDTO;

/**
 * Some times tha GUI needs very simple data Strings. The goal
 * of this interface is to offer this Converter between simple Strings to
 * Data Transfer Object (DTO)
 * 
 * 
 * @author jgutierrez
 *
 */
public interface GuiOrDTOConverter <DTO extends BaseDTO> {

	
	public String toString(List<DTO> dtoList);

	public DTO toDTO(String simpleData);
	
	public List<DTO> toDTOList(String simpleData);
	
}
