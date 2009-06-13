/**
 * 
 */
package org.inbio.m3s.usecases.imports;

/**
 * @author james
 * 
 */
public interface ImportFileParser {

	/***************************************************************************
	 * read and write operations
	 **************************************************************************/
	String read(int entryNumber, int dataCode);

	/**
	 * 
	 * @param entryNumber
	 * @param result
	 */
	void writeFinalResult(int entryNumber, String result);

	/**
	 * 
	 * @param entryNumber
	 * @param dataCode
	 * @param result
	 */
	void writeResult(int entryNumber, int dataCode, String result);

	/***************************************************************************
	 * Basic control operations
	 **************************************************************************/

	/**
	 * Closes the input and/or output file(s).
	 */
	void closeFile();

	/**
	 * Number of entries in the input
	 * 
	 * @return int
	 */
	int getTotalEntries();

	/**
	 * The first entry of the output or -1 if there are no entries.
	 * 
	 * @return int
	 */
	int getFistEntryIdex();

	/**
	 * true if entryIndex is the last index of the input
	 * 
	 * @param entryIndex
	 * @return boolean
	 */
	boolean isLastIndex(int entryIdex);

	/***************************************************************************
	 * Constants
	 **************************************************************************/

	public static final String SUCCESFUL = "OK";

	public static final String ERROR = "ERROR";

	final static int FILE_NAME_DATA = 0;

	final static int TITLE_DATA = 1;

	final static int DESCRIPTION_DATA = 2;

	final static int MEDIA_CATEGORY_DATA = 3;

	final static int MEDIA_TYPE_DATA = 4;

	final static int ASOCIATION_TYPE_DATA = 5;

	final static int ASOCIATED_TO_DATA = 6;

	final static int PROJECTS_DATA = 7;

	final static int SERIES_DATA = 8;

	final static int TAXONOMY_DATA = 9;

	final static int TAXNOMY_KINGDOM = 10;

	final static int SYNOPTIC_COLLECTION_DATA = 11;

	final static int KEYWORDS_DATA = 12;

	final static int SITE_DATA = 13;

	final static int AUTHOR_PERSON_NAME_DATA = 14;

	final static int AUTHOR_PERSON_LAST_NAME_DATA = 15;

	final static int OWNER_TYPE_DATA = 16;

	final static int OWNER_FIRST_DATA = 17;

	final static int OWNER_SECOND_DATA = 18;

	final static int USE_POLICY_DATA = 19;

	final static int MEDIA_USES_DATA = 20;

	final static int IS_BACKUP_DATA = 21;

	final static int IS_PUBLIC_DATA = 22;

	final static int YEAR_DATA = 23;

	final static int MONTH_DATA = 24;

	final static int DAY_DATA = 25;

	final static int HOURS_DATA = 26;

	final static int MINUTES_DATA = 27;

	final static int SECONDS_DATA = 28;

	final static int LANGUAGE_DATA = 29;

	final static int FINAL_RESULT = 30;

}
