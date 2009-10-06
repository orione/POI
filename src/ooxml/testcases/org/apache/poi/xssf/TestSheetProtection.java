package org.apache.poi.xssf;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import junit.framework.TestCase;

public class TestSheetProtection extends TestCase {

	public void testShouldReadWorkbookProtection() throws Exception {
		XSSFWorkbook workbook = XSSFTestDataSamples.openSampleWorkbook("sheetProtection_not_protected.xlsx");
		XSSFSheet sheet = workbook.getSheetAt(0);

		assertFalse(sheet.isAutoFilterLocked());
		assertFalse(sheet.isDeleteColumnsLocked());
		assertFalse(sheet.isDeleteRowsLocked());
		assertFalse(sheet.isFormatCellsLocked());
		assertFalse(sheet.isFormatColumnsLocked());
		assertFalse(sheet.isFormatRowsLocked());
		assertFalse(sheet.isInsertColumnsLocked());
		assertFalse(sheet.isInsertHyperlinksLocked());
		assertFalse(sheet.isInsertRowsLocked());
		assertFalse(sheet.isPivotTablesLocked());
		assertFalse(sheet.isSortLocked());
		assertFalse(sheet.isObjectsLocked());
		assertFalse(sheet.isScenariosLocked());
		assertFalse(sheet.isSelectLockedCellsLocked());
		assertFalse(sheet.isSelectUnlockedCellsLocked());
		assertFalse(sheet.isSheetLocked());

		workbook = XSSFTestDataSamples.openSampleWorkbook("sheetProtection_allLocked.xlsx");
		sheet = workbook.getSheetAt(0);

		assertTrue(sheet.isAutoFilterLocked());
		assertTrue(sheet.isDeleteColumnsLocked());
		assertTrue(sheet.isDeleteRowsLocked());
		assertTrue(sheet.isFormatCellsLocked());
		assertTrue(sheet.isFormatColumnsLocked());
		assertTrue(sheet.isFormatRowsLocked());
		assertTrue(sheet.isInsertColumnsLocked());
		assertTrue(sheet.isInsertHyperlinksLocked());
		assertTrue(sheet.isInsertRowsLocked());
		assertTrue(sheet.isPivotTablesLocked());
		assertTrue(sheet.isSortLocked());
		assertTrue(sheet.isObjectsLocked());
		assertTrue(sheet.isScenariosLocked());
		assertTrue(sheet.isSelectLockedCellsLocked());
		assertTrue(sheet.isSelectUnlockedCellsLocked());
		assertTrue(sheet.isSheetLocked());

	}

}
