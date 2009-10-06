package org.apache.poi.xssf;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import junit.framework.TestCase;

public class TestWorkbookProtection extends TestCase {

	public void testShouldReadWorkbookProtection() throws Exception {
		XSSFWorkbook workbook = XSSFTestDataSamples.openSampleWorkbook("workbookProtection_not_protected.xlsx");
		assertFalse(workbook.isStructureLocked());

		workbook = XSSFTestDataSamples.openSampleWorkbook("workbookProtection_workbook_protected.xlsx");
		assertTrue(workbook.isStructureLocked());
	}

}
