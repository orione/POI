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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;

public class TestDocumentProtection extends TestCase {

	public void testShouldReadEnforcementProperties() throws Exception {


		XWPFDocument documentWithoutDocumentProtectionTag = XWPFTestDataSamples.openSampleDocument("documentProtection_no_protection.docx");
		assertFalse(documentWithoutDocumentProtectionTag.isEnforcedReadonly());
		assertFalse(documentWithoutDocumentProtectionTag.isEnforcedFillingForms());
		assertFalse(documentWithoutDocumentProtectionTag.isEnforcedComments());
		assertFalse(documentWithoutDocumentProtectionTag.isEnforcedTrackedChanges());

		XWPFDocument documentWithoutEnforcement = XWPFTestDataSamples.openSampleDocument("documentProtection_no_protection_tag_existing.docx");
		assertFalse(documentWithoutEnforcement.isEnforcedReadonly());
		assertFalse(documentWithoutEnforcement.isEnforcedFillingForms());
		assertFalse(documentWithoutEnforcement.isEnforcedComments());
		assertFalse(documentWithoutEnforcement.isEnforcedTrackedChanges());

		XWPFDocument documentWithReadonlyEnforcement = XWPFTestDataSamples.openSampleDocument("documentProtection_readonly_no_password.docx");
		assertTrue(documentWithReadonlyEnforcement.isEnforcedReadonly());
		assertFalse(documentWithReadonlyEnforcement.isEnforcedFillingForms());
		assertFalse(documentWithReadonlyEnforcement.isEnforcedComments());
		assertFalse(documentWithReadonlyEnforcement.isEnforcedTrackedChanges());

		XWPFDocument documentWithFillingFormsEnforcement = XWPFTestDataSamples.openSampleDocument("documentProtection_forms_no_password.docx");
		assertTrue(documentWithFillingFormsEnforcement.isEnforcedFillingForms());
		assertFalse(documentWithFillingFormsEnforcement.isEnforcedReadonly());
		assertFalse(documentWithFillingFormsEnforcement.isEnforcedComments());
		assertFalse(documentWithFillingFormsEnforcement.isEnforcedTrackedChanges());

		XWPFDocument documentWithCommentsEnforcement = XWPFTestDataSamples.openSampleDocument("documentProtection_comments_no_password.docx");
		assertFalse(documentWithCommentsEnforcement.isEnforcedFillingForms());
		assertFalse(documentWithCommentsEnforcement.isEnforcedReadonly());
		assertTrue(documentWithCommentsEnforcement.isEnforcedComments());
		assertFalse(documentWithCommentsEnforcement.isEnforcedTrackedChanges());
		
		XWPFDocument documentWithTrackedChangesEnforcement = XWPFTestDataSamples.openSampleDocument("documentProtection_trackedChanges_no_password.docx");
		assertFalse(documentWithTrackedChangesEnforcement.isEnforcedFillingForms());
		assertFalse(documentWithTrackedChangesEnforcement.isEnforcedReadonly());
		assertFalse(documentWithTrackedChangesEnforcement.isEnforcedComments());
		assertTrue(documentWithTrackedChangesEnforcement.isEnforcedTrackedChanges());
		
	}

	public void testShouldEnforceForReadOnly() throws Exception {
		XWPFDocument document = createDocumentFromSampleFile("test-data/document/documentProtection_no_protection.docx");
		assertFalse(document.isEnforcedReadonly());

		document.enforceReadonly();

		assertTrue(document.isEnforcedReadonly());
	}
	public void testShouldEnforceForFillingForms() throws Exception {
		XWPFDocument document = createDocumentFromSampleFile("test-data/document/documentProtection_no_protection.docx");
		assertFalse(document.isEnforcedFillingForms());

		document.enforceFillingForms();

		assertTrue(document.isEnforcedFillingForms());
	}
	public void testShouldEnforceForComments() throws Exception {
		XWPFDocument document = createDocumentFromSampleFile("test-data/document/documentProtection_no_protection.docx");
		assertFalse(document.isEnforcedComments());
		
		document.enforceComments();
		
		assertTrue(document.isEnforcedComments());
	}
	
	public void testShouldEnforceForTrackedChanges() throws Exception {
		XWPFDocument document = createDocumentFromSampleFile("test-data/document/documentProtection_no_protection.docx");
		assertFalse(document.isEnforcedTrackedChanges());
		
		document.enforceTrackedChanges();
		
		assertTrue(document.isEnforcedTrackedChanges());
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
