/*
 * ExcelImportFile.java
 *
 * Created on February 12, 2007, 3:02 PM
 *
 * Utilities for the read and write operations over XLS files.
 * Uses the Apache POI Horrible Spreadsheet Format library
 */

package org.inbio.m3s.service.util.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.inbio.m3s.service.util.ImportFileParser;

/**
 * 
 * @author james
 */
public class ExcelImportFileParserImpl implements ImportFileParser {

	private String filename;

	private POIFSFileSystem fs;

	private HSSFWorkbook wb;

	/***************************************************************************
	 * Creates a new instance of ExcelImportFile
	 **************************************************************************/
	public ExcelImportFileParserImpl(String filename) throws FileNotFoundException,
			IOException, IllegalArgumentException {
		this.filename = filename;
		this.fs = new POIFSFileSystem(new FileInputStream(filename));
		this.wb = new HSSFWorkbook(fs);

		// check the number of sheets of the document
		if (this.wb.getNumberOfSheets() == 0) {
			throw new IllegalArgumentException(
					"El archivo no contiene información necesaria");
		} else if (this.wb.getNumberOfSheets() == 1) {
			wb.createSheet("resultados");
			wb.setSheetOrder("resultados", ExcelImportFileParserImpl.OUTPUT_SHEET);
		} else {
			wb.setSheetName(ExcelImportFileParserImpl.OUTPUT_SHEET, "resultados");
		}

		// sets the output sheet informations
		String value;
		for (int i = 0; i <= ExcelImportFileParserImpl.LAST_COLUMN; i++) {
			value = readCell(ExcelImportFileParserImpl.INPUT_SHEET,
					ExcelImportFileParserImpl.HEADER_ROW, i);
			// System.out.println("Columna #"+i+":"+value);
			writeCell(ExcelImportFileParserImpl.OUTPUT_SHEET, ExcelImportFileParserImpl.HEADER_ROW,
					i, value);
		}

	}// constructor

	/***************************************************************************
	 * read and write operations implementation
	 **************************************************************************/

	/**
	 * Reads the dataCode information from a excel file
	 * 
	 * @param entryNumber
	 * @param datacode
	 * @return String
	 */
	public String read(int entryNumber, int dataCode) {
		return readCell(ExcelImportFileParserImpl.INPUT_SHEET, entryNumber,
				getExcelColumnNumber(dataCode));

	}

	/**
	 * Writes the result of processing the information of the entryNumber
	 * 
	 * @param entryNumber
	 * @param result
	 * 
	 * FIXME: only writes on the first column (0), on invocation of the method
	 * over the same entryNumber row overwrites the first result. This shoud
	 * write the seccond result on the next column.
	 * 
	 */
	public void writeFinalResult(int entryNumber, String result) {
		writeCell(ExcelImportFileParserImpl.INPUT_SHEET, entryNumber,
				getExcelColumnNumber(ImportFileParser.FINAL_RESULT), result);
	}

	/**
	 * 
	 * @param entryNumber
	 * @param dataCode
	 * @param result
	 */
	public void writeResult(int entryNumber, int dataCode, String result) {
		writeCell(ExcelImportFileParserImpl.OUTPUT_SHEET, entryNumber, dataCode, result);
	}

	/***************************************************************************
	 * Basic control operations implementation
	 **************************************************************************/

	/**
	 * Closes the input and/or output file(s).
	 */
	public void closeFile() {
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			this.wb.write(fileOut);
			fileOut.close();
		} catch (IOException ex) {
			System.out.println("Excepcion volo");
			ex.printStackTrace();
		}
	}

	/**
	 * Number of entries in the input
	 * 
	 * @return int
	 */
	public int getTotalEntries() {
		return this.numberOfRows(ExcelImportFileParserImpl.INPUT_SHEET);
	}

	/**
	 * The first entry of the output or -1 if there are no entries.
	 * 
	 * @return int
	 */
	public int getFistEntryIdex() {
		if (this.getTotalEntries() >= 1)
			return ExcelImportFileParserImpl.FIRST_ENTRY_ROW;
		else
			return -1;
	}

	/**
	 * true if entryIndex is the last index of the input
	 * 
	 * @return boolean
	 */
	public boolean isLastIndex(int entryIdex) {
		if ((getTotalEntries() - 1) == entryIdex)
			return true;
		else
			return false;
	}

	/***************************************************************************
	 * Private class methods
	 **************************************************************************/

	/**
	 * Convertes the ImportFile data codes to excel column numbers
	 * 
	 * @param dataCode
	 *            ImportFile data code
	 * @return int excel column number
	 */
	private int getExcelColumnNumber(int dataCode) {
		if (dataCode >= 0 && dataCode < 14)
			return dataCode;
		else if (dataCode >= 14 && dataCode < 23)
			return dataCode + 1;
		else if (dataCode >= 23 && dataCode < 29)
			return dataCode + 2;
		else if (dataCode == 29)
			return 32;
		else
			// data code == 29
			return 34;

	}

	/**
	 * @param sheetNumber
	 * @return int
	 */
	private int numberOfRows(int sheetNumber) {
		return this.wb.getSheetAt(sheetNumber).getLastRowNum();
	}

	/**
	 * @param sheetNumber
	 * @param rowNumber
	 * @param columnNumber
	 * @return String with the value, or null string if there was an error.
	 */
	private String readCell(int sheetNumber, int rowNumber, int columnNumber) {
		Double tempDouble;
		Integer tempInteger;
		try {
			HSSFSheet sheet = this.wb.getSheetAt(sheetNumber);
			HSSFRow row = sheet.getRow(rowNumber);
			HSSFCell cell = row.getCell((short) columnNumber);

			if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				// System.out.println("-string-"+rowNumber);
				return cell.getStringCellValue();
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				// System.out.println("-numeric-"+rowNumber);
				tempDouble = Double.valueOf(cell.getNumericCellValue());
				tempInteger = new Integer(tempDouble.intValue());
				return tempInteger.toString();
			} else
				return null;

		} catch (NullPointerException npe) {
			// System.out.println("Exception en read @ col" + columnNumber);
			return null;
		}
	}

	/**
	 * @param sheetNumber
	 * @param rowNumber
	 * @param columnNumber
	 * @param value
	 */
	private void writeCell(int sheetNumber, int rowNumber, int columnNumber,
			String value) {
		HSSFSheet sheet = wb.getSheetAt(sheetNumber);
		HSSFRow row;
		HSSFCell cell;

		row = sheet.getRow(rowNumber);
		if (row == null) {
			row = sheet.createRow(rowNumber);
		}

		cell = row.getCell((short) columnNumber);
		if (cell == null) {
			cell = row.createCell((short) columnNumber);
		}

		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(value);
	}

	/***************************************************************************
	 * Class constants
	 **************************************************************************/
	// SHEETS
	private final static int INPUT_SHEET = 0;

	private final static int OUTPUT_SHEET = 1;

	// ROWS
	private final static int HEADER_ROW = 0;

	private final static int FIRST_ENTRY_ROW = 1;

	// COLUMNS
	private final static int LAST_COLUMN = 34;

}
