package org.inbio.m3s.converters;

import java.util.ArrayList;
import java.util.List;

import org.inbio.m3s.dto.BaseDTO;

/**
 *
 * @author jgutierrez
 *
 */
public abstract class BaseConverter<S extends BaseDTO, G extends Object> implements Converter<S, G> {


	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.converters.Converter#toGWTDTOList(java.util.List)
	 */
	public List<G> toGWTDTOList(List<S> dtoList){
		if(dtoList==null)
			return null;
		List<G> gwtdtoList = new ArrayList<G>();
		for (S dto: dtoList)
			gwtdtoList.add(toGWTDTO(dto));
		return gwtdtoList;
	}


	/*
	 * (non-Javadoc)
	 * @see org.inbio.m3s.converters.Converter#toDTOList(java.util.List)
	 */
	public List<S> toDTOList(List<G> gwtdtoList){
		if(gwtdtoList==null)
			return null;
		List<S> dtoList = new ArrayList<S>();
		for (G gwtdto: gwtdtoList)
			dtoList.add(toDTO(gwtdto));
		return dtoList;
	}


}