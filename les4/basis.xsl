<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="zaalvoetbal">
		<html>
			<body>
				<h2>Wedstrijden</h2>
				<table border="1">
					<tr bgcolor="#9acd32">
						<th>Thuisteam</th>
						<th>Uitteam</th>
						<th>Datum</th>
						<th>Tijdstip</th>
						<th>Uitslag</th>
					</tr>
					<xsl:apply-templates select="wedstrijden/wedstrijd" mode="overzicht">
						<xsl:sort select="datum" order="descending" />
					</xsl:apply-templates>
				</table>
				
				<br />
				
				<table border="1">
					<tr bgcolor="#9acd32">
						<th>Teamnaam</th>
						<th>Gespeelde wedstrijden</th>
						<th>Doelpunten mee</th>
					</tr>
					<xsl:apply-templates select="verenigingen/vereniging/teams/team" mode="tussenstand" />
				</table>
			</body>
		</html>
	</xsl:template>
	
	<xsl:template match="wedstrijd" mode="overzicht">
		<xsl:variable name="vnrt"><xsl:value-of select="@verenigingnummerthuis" /></xsl:variable>
		<xsl:variable name="vnru"><xsl:value-of select="@verenigingnummeruit" /></xsl:variable>
		<tr>
			<xsl:apply-templates select="/zaalvoetbal/verenigingen/vereniging[$vnrt=@verenigingnummer]">
	        	<xsl:with-param name="tnr" select="@teamnummerthuis" />
	        </xsl:apply-templates>
	        <xsl:apply-templates select="/zaalvoetbal/verenigingen/vereniging[$vnru=@verenigingnummer]">
	        	<xsl:with-param name="tnr" select="@teamnummeruit" />
	        </xsl:apply-templates>
			<td><xsl:value-of select="datum" /></td>
			<td><xsl:value-of select="tijdstip" /></td>
			<td><xsl:value-of select="uitslag/scorethuis" /> - <xsl:value-of select="uitslag/scoreuit" /></td>
		</tr>
	</xsl:template>
	
	<xsl:template match="vereniging">
		<xsl:param name="tnr" select="tnr" />
		<td><xsl:value-of select="naam" />&#160;<xsl:value-of select="$tnr" /></td>
	</xsl:template>
	
	<xsl:template match="team" mode="tussenstand">
		<xsl:variable name="vnr"><xsl:value-of select="@verenigingnummer" /></xsl:variable>
		<xsl:variable name="tnr"><xsl:value-of select="@teamnummer" /></xsl:variable>
		<xsl:variable name="thuis"><xsl:value-of select="sum(/zaalvoetbal/wedstrijden/wedstrijd[$vnr=@verenigingnummerthuis and $tnr=@teamnummerthuis]/uitslag/scorethuis)" /></xsl:variable>
		<xsl:variable name="uit"><xsl:value-of select="sum(/zaalvoetbal/wedstrijden/wedstrijd[$vnr=@verenigingnummeruit and $tnr=@teamnummeruit]/uitslag/scoreuit)" /></xsl:variable>
		<tr>
			<xsl:apply-templates select="/zaalvoetbal/verenigingen/vereniging[$vnr=@verenigingnummer]">
	        	<xsl:with-param name="tnr" select="$tnr" />
	        </xsl:apply-templates>
			<td><xsl:value-of select="count(/zaalvoetbal/wedstrijden/wedstrijd[($vnr=@verenigingnummerthuis and $tnr=@teamnummerthuis) or ($vnr=@verenigingnummeruit and $tnr=@teamnummeruit)])" /></td>
			<td><xsl:value-of select="$thuis + $uit" /></td>
		</tr>
	</xsl:template>
</xsl:stylesheet>