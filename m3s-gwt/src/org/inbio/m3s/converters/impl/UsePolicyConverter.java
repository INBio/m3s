/**
 * 
 */
package org.inbio.m3s.converters.impl;

import org.inbio.m3s.converters.BaseConverter;
import org.inbio.m3s.converters.Converter;
import org.inbio.m3s.dto.metadata.UsePolicyDTO;
import org.inbio.m3s.gwt.client.dto.UsePolicyGWTDTO;

/**
 * @author jgutierrez
 *
 */
public class UsePolicyConverter extends BaseConverter<UsePolicyDTO, UsePolicyGWTDTO> implements Converter<UsePolicyDTO, UsePolicyGWTDTO> {

	public UsePolicyDTO toDTO(UsePolicyGWTDTO gwtdto) {
		if(gwtdto == null)
			return null;
		
		return new UsePolicyDTO(gwtdto.getUsePolicyKey(),gwtdto.getName());
	}

	public UsePolicyGWTDTO toGWTDTO(UsePolicyDTO dto) {
		if(dto == null)
			return null;
		
		return new UsePolicyGWTDTO(dto.getUsePolicyKey(),dto.getName());
	}

}
