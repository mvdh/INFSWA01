<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" />

	<xsl:template match="zaalvoetbal">
		<xsl:element name="zvoetbal">
			<xsl:apply-templates select="wedstrijden" />
			<xsl:apply-templates select="verenigingen" />
			<xsl:apply-templates select="sporthallen" />
			<xsl:element name="formulier-id">
				<xsl:attribute name="id">1234</xsl:attribute>
			</xsl:element>
		</xsl:element>
	</xsl:template>

	<xsl:template match="wedstrijden">
		<xsl:element name="wedstrijden">
			<xsl:apply-templates select="node()|@*" />
		</xsl:element>
	</xsl:template>

	<xsl:template match="verenigingen">
		<xsl:element name="verenigingen">
			<xsl:apply-templates select="node()|@*" />
		</xsl:element>
	</xsl:template>

	<xsl:template match="sporthallen">
		<xsl:element name="sporthallen">
			<xsl:apply-templates select="node()|@*" />
		</xsl:element>
	</xsl:template>

	<xsl:template match="node()|@*">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*" />
		</xsl:copy>
	</xsl:template>

	<xsl:template match="comment()" />

</xsl:stylesheet>

