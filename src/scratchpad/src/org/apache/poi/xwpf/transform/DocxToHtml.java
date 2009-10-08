package org.apache.poi.xwpf.transform;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Templates;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class DocxToHtml {

	public static void getHtml(InputStream docx, InputStream xlst, OutputStream html) throws Exception {
		Templates template = TransformerFactory.newInstance().newTemplates(new StreamSource(xlst));

		InputStream corePart = new DocumentPartsExtractor().getCorePart(docx);

		template.newTransformer().transform(new StreamSource(corePart), new StreamResult(html));
	}
}
