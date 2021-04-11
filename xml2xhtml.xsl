<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version = "1.0" xmlns:xsl = "http://www.w3.org/1999/XSL/Transform"> 

<xsl:output method="text" />
<xsl:key name="row-by-journal" match="row" use="Journal_Name" />
<xsl:key name="row-by-publisher" match="row" use="Publication_Name" />
<xsl:key name="row-by-confname" match="row" use="Conference_Name" />
<xsl:key name="row-by-confloc" match="row" use="Conference_Location" />
<xsl:key name="row-by-booktitle" match="row" use="Book_Title" />
<xsl:key name="row-by-bookeditor" match="row" use="Book_Editor" />


<xsl:template match = "/Bibliography">

<html>
<body>
	<h2>Bibliography Summary</h2>
<br></br>
<h3>Journal Articles</h3>
<br></br>
<table>
<tr>
	<td>
		Number of journal articles
	</td>
	<td>
		<xsl:value-of select="count(/Bibliography/row/Journal_Name[string(.)])" />
	</td>
</tr>	
<tr>
	<td>
		Most frequent journal
	</td>
	<td>		
		<xsl:for-each select="row[count(. | key('row-by-journal', Journal_Name)[1]) = 1]">
            		<xsl:sort select="count(key('row-by-journal', Journal_Name))" data-type="number" order="descending"/>
            		<xsl:if test="position() = 2">
                		<xsl:value-of select="Journal_Name" />
            		</xsl:if>
		</xsl:for-each>
	</td>
</tr>
<tr>
	<td>
		Most frequent publisher
	</td>
	<td>
		<xsl:for-each select="row[count(. | key('row-by-publisher', Publication_Name)[1]) = 1]">
			<xsl:sort select="count(key('row-by-publisher', Publication_Name))" data-type="number" order="descending"/>
			<xsl:if test="position() = 2">
				<xsl:value-of select="Publication_Name" />
			</xsl:if>
		</xsl:for-each>
	</td>
</tr>
</table>
<br></br>
<h3>Conference proceedings</h3>
<br></br>
<table>
<tr>
	<td>
		Number of conference proceedings
	</td>
	<td>
		<xsl:value-of select="count(/Bibliography/row/Conference_Name[string(.)])" />
	</td>
</tr>
<tr>
	<td>
		Most frequent venue
	</td>
	<td>
		<xsl:for-each select="row[count(. | key('row-by-confname', Conference_Name)[1]) = 1]">
			<xsl:sort select="count(key('row-by-confname', Conference_Name))" data-type="number" order="descending"/>
			<xsl:if test="position() = 3">
				<xsl:value-of select="Conference_Name" />
			</xsl:if>
		</xsl:for-each>
	</td>
</tr>
<tr>
	<td>
		Most frequent location
	</td>
	<td>
		<xsl:for-each select="row[count(. | key('row-by-confloc', Conference_Location)[1]) = 1]">
			<xsl:sort select="count(key('row-by-confloc', Conference_Location))" data-type="number" order="descending"/>
			<xsl:if test="position() = 3">
				<xsl:value-of select="Conference_Location" />
			</xsl:if>
		</xsl:for-each>
	</td>
</tr>
</table> 
<br></br> 
<h3>Book chapters</h3> 
<br></br>
<table>
<tr>
	<td>
		Number of book chapters
	</td>
	<td>
		<xsl:value-of select="count(/Bibliography/row/Book_Title[string(.)])" />
	</td>
</tr>
<tr>
	<td>
		Most frequent book
	</td>
	<td>
		<xsl:for-each select="row[count(. | key('row-by-booktitle', Book_Title)[1]) = 1]">
			<xsl:sort select="count(key('row-by-booktitle', Book_Title))" data-type="number" order="descending"/>
			<xsl:if test="position() = 2">
				<xsl:value-of select="Book_Title" />
			</xsl:if>
		</xsl:for-each>
	</td>
</tr>
<tr>
	<td>
		Most frequent publisher
	</td>
	<td>
		<xsl:for-each select="row[count(. | key('row-by-bookeditor', Book_Editor)[1]) = 1]">
			<xsl:sort select="count(key('row-by-bookeditor', Book_Editor))" data-type="number" order="descending"/>
			<xsl:if test="position() = 2">
				<xsl:value-of select="Book_Editor" />
			</xsl:if>
		</xsl:for-each>
	</td>
</tr>
</table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>