<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main"
	xmlns:a="http://schemas.openxmlformats.org/drawingml/2006/main"
	xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:v="urn:schemas-microsoft-com:vml"
	xmlns:WX="http://schemas.microsoft.com/office/word/2003/auxHint"
	xmlns:aml="http://schemas.microsoft.com/aml/2001/core" xmlns:w10="urn:schemas-microsoft-com:office:word"
	version="1.0">

	<xsl:output method="html" encoding="utf-8"
		omit-xml-declaration="yes" indent="yes" />

	<xsl:template match="w:body">
		<p>a Body</p>
	</xsl:template>

	<xsl:template match="w:p">
		<p>a Paragraph</p>
	</xsl:template>

</xsl:stylesheet>
