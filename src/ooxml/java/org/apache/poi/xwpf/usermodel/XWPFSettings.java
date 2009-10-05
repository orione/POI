package org.apache.poi.xwpf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.SettingsDocument;

public class XWPFSettings extends POIXMLDocumentPart {

	private CTSettings ctSettings;

	public XWPFSettings(PackagePart part, PackageRelationship rel) throws IOException {
		super(part, rel);
		readFrom(part.getInputStream());
	}

	private void readFrom(InputStream inputStream) {
		try {
			ctSettings = SettingsDocument.Factory.parse(inputStream).getSettings();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public XWPFSettings() {
		super();
		ctSettings = CTSettings.Factory.newInstance();
	}

	public boolean isEnforcedWith(STDocProtect.Enum editValue) {
		CTDocProtect ctDocProtect = ctSettings.getDocumentProtection();

		if (ctDocProtect == null) {
			return false;
		}

		return ctDocProtect.getEnforcement().equals(STOnOff.X_1) && ctDocProtect.getEdit().equals(editValue);
	}

	public void setEnforcementEditValue(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDocProtect.Enum editValue) {
		safeGetDocumentProtection().setEnforcement(STOnOff.X_1);
		safeGetDocumentProtection().setEdit(editValue);
	}

	public void removeEnforcement() {
		safeGetDocumentProtection().setEnforcement(STOnOff.X_0);
	}

	@Override
	protected void commit() throws IOException {

		XmlOptions xmlOptions = new XmlOptions(DEFAULT_XML_OPTIONS);
		xmlOptions.setSaveSyntheticDocumentElement(new QName(CTSettings.type.getName().getNamespaceURI(), "settings"));
		Map<String, String> map = new HashMap<String, String>();
		map.put("http://schemas.openxmlformats.org/officeDocument/2006/math", "m");
		map.put("urn:schemas-microsoft-com:office:office", "o");
		map.put("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "r");
		map.put("urn:schemas-microsoft-com:vml", "v");
		map.put("http://schemas.openxmlformats.org/markup-compatibility/2006", "ve");
		map.put("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "w");
		map.put("urn:schemas-microsoft-com:office:word", "w10");
		map.put("http://schemas.microsoft.com/office/word/2006/wordml", "wne");
		map.put("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wp");
		xmlOptions.setSaveSuggestedPrefixes(map);

		PackagePart part = getPackagePart();
		OutputStream out = part.getOutputStream();
		ctSettings.save(out, xmlOptions);
		out.close();
	}

	private CTDocProtect safeGetDocumentProtection() {
		CTDocProtect documentProtection = ctSettings.getDocumentProtection();
		if (documentProtection == null) {
			documentProtection = CTDocProtect.Factory.newInstance();
			ctSettings.setDocumentProtection(documentProtection);
		}
		return ctSettings.getDocumentProtection();
	}

}
