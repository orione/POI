package org.apache.poi.xssf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import junit.framework.TestCase;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	
	public void testShouldWriteStructureLock() throws Exception {
		XSSFWorkbook workbook = XSSFTestDataSamples.openSampleWorkbook("workbookProtection_not_protected.xlsx");
		assertFalse(workbook.isStructureLocked());
		
		workbook.lockStructure();
		assertTrue(workbook.isStructureLocked());
	}
	
	public void testShouldWriteWindowsLock() throws Exception {
		XSSFWorkbook workbook = XSSFTestDataSamples.openSampleWorkbook("workbookProtection_not_protected.xlsx");
		assertFalse(workbook.isWindowsLocked());
		
		workbook.lockWindows();
		assertTrue(workbook.isWindowsLocked());
	}
	
	public void testShouldWriteRevisionLock() throws Exception {
		XSSFWorkbook workbook = XSSFTestDataSamples.openSampleWorkbook("workbookProtection_not_protected.xlsx");
		assertFalse(workbook.isRevisionLocked());
		
		workbook.lockRevision();
		assertTrue(workbook.isRevisionLocked());
	}
	
	public void testIntegration() throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook();
		wb.createSheet("Testing purpose sheet");
		assertFalse(wb.isRevisionLocked());
		
		wb.lockRevision();
		
		File tempFile = File.createTempFile("workbookProtection", ".xlsx");
        FileOutputStream out = new FileOutputStream(tempFile);
        wb.write(out);
        out.close();

        FileInputStream inputStream = new FileInputStream(tempFile);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        inputStream.close();

        assertTrue(workbook.isRevisionLocked());
	}
}
