
package org.inbio.m3s.converters;

import java.util.List;

import org.inbio.m3s.dto.BaseDTO;

/**
 *
 * @author jgutierrez
 */
public interface Converter<S extends BaseDTO, G extends Object> {
    
    /**
     * 
     * @param dto
     * @return
     */
	public G toGWTDTO(S dto);
	
  /**
   * 
   * @param gwtdto
   * @return
   */
	public S toDTO(G gwtdto);

	/**
     *
     * @param dtoList
     * @return
     */
	public List<G> toGWTDTOList(List<S> dtoList);
	
	/**
  *
  * @param gwtdtoList
  * @return
  */
	public List<S> toDTOList(List<G> gwtdtoList);
}