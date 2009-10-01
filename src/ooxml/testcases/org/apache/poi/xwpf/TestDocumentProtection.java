/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.xwpf;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class TestDocumentProtection extends TestCase {

	public void testShouldReadEnforcementProperties() throws Exception {
		XWPFDocument documentWithoutDocumentProtectionTag = XWPFTestDataSamples.openSampleDocument("sample.docx");
		assertFalse(documentWithoutDocumentProtectionTag.isEnforcedReadonly());

		XWPFDocument documentWithoutEnforcement = XWPFTestDataSamples.openSampleDocument("documentProtection_no_protection_tag_existing.docx");
		assertFalse(documentWithoutEnforcement.isEnforcedReadonly());

		XWPFDocument documentWithEnforcement = XWPFTestDataSamples.openSampleDocument("documentProtection_readonly_no_password.docx");
		assertTrue(documentWithEnforcement.isEnforcedReadonly());
	}

	public void testShouldModifieEnforcement() throws Exception {
		XWPFDocument document = createDocumentFromSampleFile("test-data/document/documentProtection_no_protection_tag_existing.docx");
		assertFalse(document.isEnforcedReadonly());

		document.enforceReadonly();

		assertTrue(document.isEnforcedReadonly());
	}
	
	public void testShouldUnsetEnforcement() throws Exception {
		XWPFDocument document = createDocumentFromSampleFile("test-data/document/documentProtection_readonly_no_password.docx");
		assertTrue(document.isEnforcedReadonly());

		document.removeEnforcement();

		assertFalse(document.isEnforcedReadonly());
	}

	private XWPFDocument createDocumentFromSampleFile(String fileName) throws FileNotFoundException, IOException {
		File file = new File(fileName);
		FileInputStream in = new FileInputStream(file);
		byte[] bytes = new byte[(int) file.length()];
		in.read(bytes);

		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
		XWPFDocument document = new XWPFDocument(inputStream);
		return document;
	}
}
