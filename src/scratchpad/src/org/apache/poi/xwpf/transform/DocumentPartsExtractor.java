package org.apache.poi.xwpf.transform;

import java.io.InputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.util.PackageHelper;

public class DocumentPartsExtractor {

	public InputStream getCorePart(InputStream docxInputStream) throws Exception {
		OPCPackage opcPackage = PackageHelper.open(docxInputStream);
		PackageRelationship coreRel = opcPackage.getRelationshipsByType(PackageRelationshipTypes.CORE_DOCUMENT).getRelationship(0);
		PackagePart corePart = opcPackage.getPart(coreRel);
		
		return corePart.getInputStream();
	}
}