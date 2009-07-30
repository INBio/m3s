/**
 * 
 */
package org.inbio.m3s.converters.impl;

import org.inbio.m3s.converters.BaseConverter;
import org.inbio.m3s.converters.Converter;
import org.inbio.m3s.dto.message.KeywordDTO;
import org.inbio.m3s.gwt.client.dto.metadata.KeywordGWTDTO;

/**
 * @author jgutierrez
 *
 */
public class KeywordConverter extends BaseConverter<KeywordDTO, KeywordGWTDTO> implements Converter<KeywordDTO, KeywordGWTDTO> {

	public KeywordDTO toDTO(KeywordGWTDTO gwtdto) {
		if(gwtdto == null)
			return null;
		
		return new KeywordDTO(gwtdto.getKeywordKey(),gwtdto.getName());
	}

	public KeywordGWTDTO toGWTDTO(KeywordDTO dto) {
		if(dto == null)
			return null;
		
		return new KeywordGWTDTO(dto.getKeywordKey(),dto.getName());
	}

}
