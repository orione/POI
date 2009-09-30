package org.apache.poi.xwpf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.channels.FileChannel;

import junit.framework.TestCase;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class TestDocumentProtection extends TestCase {
	
	public void testShouldReadEnforcementProperties() throws Exception {
		XWPFDocument doc = XWPFTestDataSamples.openSampleDocument("sample.docx");
		assertFalse(doc.isEnforced());

		doc = XWPFTestDataSamples.openSampleDocument("documentProtection_no_protection.docx");
		assertFalse(doc.isEnforced());

		doc = XWPFTestDataSamples.openSampleDocument("documentProtection_readonly_no_password.docx");
		assertTrue(doc.isEnforced());
	}

	public void testShouldModifieEnforcement() throws Exception {
		FileInputStream in = new FileInputStream("test-data/document/documentProtection_no_protection_tag_existing.docx");
		File tempFile = File.createTempFile("documentProtection", ".docx");
		FileOutputStream fos = new FileOutputStream(tempFile);
		try {
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = in.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (in != null)
				in.close();
			if (fos != null)
				fos.close();
		}

		FileInputStream inputStream = new FileInputStream(tempFile);
		XWPFDocument document = new XWPFDocument(inputStream);

		document.enforceReadonly();
		FileOutputStream stream2 = new FileOutputStream(tempFile);
		document.write(stream2);
		stream2.close();

		assertTrue("should be enforced", document.isEnforced());

		document.getProperties().getCoreProperties().setTitle("...");

	}
}
