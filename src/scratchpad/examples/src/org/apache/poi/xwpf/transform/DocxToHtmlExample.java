package org.apache.poi.xwpf.transform;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DocxToHtmlExample {

	public static void main(String[] args) throws Exception {

		transofrmation("test-data/document/simpleDocumentToTransform.docx", "docxTransformed.html", "test-data/document/DocX2HtmlSimple.xslt");
//		transofrmation("test-data/document/docxToTransform.docx", "docxTransformed.html", "test-data/document/DocX2Html.xslt");
	}

	private static void transofrmation(String docx, String outputHtml, String xslt) throws FileNotFoundException, Exception {
		InputStream xml = new FileInputStream(docx);
		InputStream xlst = new FileInputStream(xslt);
		OutputStream html = new FileOutputStream(outputHtml);
		DocxToHtml.getHtml(xml, xlst, html);
	}
}
