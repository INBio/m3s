/**
 * 
 */
package org.inbio.m3s.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.inbio.m3s.dto.taxonomy.GatheringLiteDTO;

/**
 * @author jgutierrez
 *
 */
public class BotanyUtils {
	
	private static Logger logger = Logger.getLogger(BotanyUtils.class);
	
	/**
	 * This method does not check if the gathering code is correct!!!
	 * Only parses the gatheringCode and creates the GatheringLite object
	 * 
	 * @param gatheringCode
	 * @return
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public static GatheringLiteDTO getGatheringLiteFromCode(String gatheringCode) throws IllegalArgumentException {
		logger.debug("getGatheringLite");
		
		String errorMsj = "Se espera un código de colecta de la forma LETRAS ; NUMEROS";
		
		try {
			List<String> temp = (List) StringUtil.getIndividualItems(gatheringCode,
					java.lang.String.class);

			if (temp.size() != 2) {
				throw new IllegalArgumentException(errorMsj);
			}

			logger.debug(temp.get(0));
			logger.debug(temp.get(1));
			
			String gatheringPersonName = temp.get(0);
			String gatheringNumber = temp.get(1);
			
			return new GatheringLiteDTO(gatheringNumber, gatheringPersonName);

		} catch (IllegalArgumentException iae) {
			throw new IllegalArgumentException(errorMsj);
		}
		
	}
	
	public static GatheringLiteDTO getGatheringLiteDTOFromCode(String gatheringCode) throws IllegalArgumentException {
		logger.debug("getGatheringLiteDTOFromCode");
		
		String errorMsj = "Se espera un código de colecta de la forma LETRAS ; NUMEROS";
		
		try {
			List<String> temp = (List) StringUtil.getIndividualItems(gatheringCode,
					java.lang.String.class);

			if (temp.size() != 2) {
				throw new IllegalArgumentException(errorMsj);
			}

			logger.debug(temp.get(0));
			logger.debug(temp.get(1));
			
			String gatheringPersonName = temp.get(0);
			Integer gatheringNumber = new Integer(temp.get(1));
			
			return new GatheringLiteDTO(gatheringNumber.toString(), gatheringPersonName);

		} catch (IllegalArgumentException iae) {
			throw new IllegalArgumentException(errorMsj);
		}
		
	}

}
