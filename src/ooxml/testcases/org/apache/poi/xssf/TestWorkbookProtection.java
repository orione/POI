package org.apache.poi.xssf;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import junit.framework.TestCase;

public class TestWorkbookProtection extends TestCase {

	public void testShouldReadWorkbookProtection() throws Exception {
		XSSFWorkbook workbook = XSSFTestDataSamples.openSampleWorkbook("workbookProtection_not_protected.xlsx");
		assertFalse(workbook.isStructureLocked());
		assertFalse(workbook.isWindowsLocked());
		assertFalse(workbook.isRevisionLocked());

		workbook = XSSFTestDataSamples.openSampleWorkbook("workbookProtection_workbook_structure_protected.xlsx");
		assertTrue(workbook.isStructureLocked());
		assertFalse(workbook.isWindowsLocked());
		assertFalse(workbook.isRevisionLocked());

		workbook = XSSFTestDataSamples.openSampleWorkbook("workbookProtection_workbook_windows_protected.xlsx");
		assertTrue(workbook.isWindowsLocked());
		assertFalse(workbook.isStructureLocked());
		assertFalse(workbook.isRevisionLocked());

		workbook = XSSFTestDataSamples.openSampleWorkbook("workbookProtection_workbook_revision_protected.xlsx");
		assertTrue(workbook.isRevisionLocked());
		assertFalse(workbook.isWindowsLocked());
		assertFalse(workbook.isStructureLocked());
}

}
