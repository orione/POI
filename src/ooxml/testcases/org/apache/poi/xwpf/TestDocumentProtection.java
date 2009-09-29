package org.apache.poi.xwpf;

import junit.framework.TestCase;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class TestDocumentProtection extends TestCase {
	public void testShouldReadDocumentProtectionProperties() throws Exception {
		XWPFDocument doc = XWPFTestDataSamples.openSampleDocument("sample.docx");
		
		assertFalse(doc.isProtected());
		
		doc = XWPFTestDataSamples.openSampleDocument("protected_sample.docx");
		
		assertTrue(doc.isProtected());
	}

}
