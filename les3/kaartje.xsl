<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="kaartjes">
		<html>
			<body>
				<h2>Visitekaartjes</h2>
				<table border="1">
					<tr bgcolor="#9acd32">
						<th>School</th>
						<th>Opleiding</th>
						<th>Naam</th>
						<th>Foto</th>
					</tr>
					<xsl:apply-templates select="kaartje" />
				</table>
			</body>
		</html>
	</xsl:template>
	
	<xsl:template match="kaartje">
		<tr>
			<td><xsl:value-of select="school" /></td>
			<td><xsl:value-of select="opleiding" /></td>
			<td><xsl:value-of select="naam" /></td>
			<td><xsl:if test="foto"><img src="{foto/@url}" /></xsl:if></td>
		</tr>
	</xsl:template>
</xsl:stylesheet>

