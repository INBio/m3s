/**
 * 
 */
package org.inbio.m3s.gwt.client.widgets.galleries;

/**
 * @author jgutierrez
 * @deprecated
 * @use SearchCriteriaValuesDTO
 */
public class SearchCriteriaValues {

	public static final Integer IS = new Integer(0);
	
	public static final Integer LIKE = new Integer(1);

	public static final String[] LITERAL_VALUES = { "igual a", "similar a"};

	public static String getLiteralValue(Integer code) {
		return LITERAL_VALUES[code.intValue()];
	}

}
