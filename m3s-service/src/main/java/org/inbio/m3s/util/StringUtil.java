/**
 * 
 */
package org.inbio.m3s.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author jgutierrez
 * 
 */
public class StringUtil {
	
	// Delimiter character for data conversion
	public final static String TEXT_DELIMITER = ";";

	private static Logger logger = Logger.getLogger(StringUtil.class);

	/**
	 * Parses a string using a text delimiter defined in the
	 * Propierties.TEXT_DELIMITER contant. Blank Spaces after the delimiter or
	 * before the first character of the item are ignored, and the last item of
	 * the list doesn't need the delimiter at the end.
	 * 
	 * @param itemsList
	 *            the string to be parse.
	 * @param returnType
	 *            the expected class type of the returning list elements, by now
	 *            only could be String or Integer. The way of introducing this
	 *            parameter is: Integer.class or String.class
	 * @return List of Objects of returnType class (Posible returning clases are
	 *         String or Integer)
	 * @throws IllegalArgumentException
	 */
	public static List<Object> getIndividualItems(String itemsList, Class returnType) throws IllegalArgumentException {
		logger.debug("getting Individual Items...");

		List<Object> result = new ArrayList<Object>();
		if (itemsList == null) {
			return result;
		} else if (itemsList.length() == 0) {
			return result;
		}

		int start = 0;
		int end = 0;
		try {

			// cleans the empty spaces at the begining of the string
			while (itemsList.charAt(start) == ' ') {
				start++;
			}

			while (start < itemsList.length()) {
				// looks for the text delimiter
				end = itemsList.indexOf(TEXT_DELIMITER, start);

				if (end == -1) {
					end = itemsList.length();
				}

				// adds the the item to the result list
				if (returnType == String.class)
					result.add(itemsList.substring(start, end));
				else if (returnType == Integer.class)
					result.add(new Integer(itemsList.substring(start, end)));
				else {
					logger
							.error("getting Individual Items... Illegal returnType");
					throw new IllegalArgumentException(
							"getIndividualItems - Illegal return type");
				}
				// sets the next start of the item on the end of this one plus
				// the size of the delimiter
				start = end + TEXT_DELIMITER.length();
				// cleans the empty spaces and sets the start on a character
				// diferent than ' '
				// cleans the empty spaces at the begining of the string
				while (start < itemsList.length()
						&& itemsList.charAt(start) == ' ') {
					start++;
				}
			}
		} catch (ClassCastException cce) {
			logger.error("getting Individual Items... ClassCastException");
			cce.printStackTrace();
			logger.error(cce.getMessage());
			throw new IllegalArgumentException("getIndividualItems", cce);
		} catch (StringIndexOutOfBoundsException sioobe) {
			logger
					.error("getting Individual Items... StringIndexOutOfBoundsException");
			logger.error(sioobe.getMessage());
			logger.error(sioobe.getCause());
			throw new IllegalArgumentException("getIndividualItems", sioobe);
		}

		logger.debug("getting Individual Items... ok, returnig "
				+ result.size() + " items.");
		return result;
	}

}
