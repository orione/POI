package org.apache.poi.xwpf.usermodel;

import java.util.List;

import org.apache.poi.POIXMLDocumentPart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSettings;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDocProtect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.SettingsDocument;

public class XWPFSettings {

	private CTSettings ctSettings;

	public XWPFSettings() {
		ctSettings = CTSettings.Factory.newInstance();
	}

	public XWPFSettings(List<POIXMLDocumentPart> relations) {
		try {
			for (POIXMLDocumentPart p : relations) {
				String relation = p.getPackageRelationship().getRelationshipType();

				if (relation.equals(XWPFRelation.SETTINGS.getRelation())) {
					ctSettings = SettingsDocument.Factory.parse(p.getPackagePart().getInputStream()).getSettings();
				}

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (ctSettings == null) {
			ctSettings = CTSettings.Factory.newInstance();
		}
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

	private CTDocProtect safeGetDocumentProtection() {
		CTDocProtect documentProtection = ctSettings.getDocumentProtection();
		if (documentProtection == null) {
			documentProtection = CTDocProtect.Factory.newInstance();
			ctSettings.setDocumentProtection(documentProtection);
		}
		return ctSettings.getDocumentProtection();
	}

}
