<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>Zbiór wierszy</title>
                <link rel="stylesheet" href="style.css" type="text/css"></link>
            </head>
            <header>
                <h1>Zbiór wierszy</h1>
            </header>
            <xsl:apply-templates />
            <body></body>
        </html>
    </xsl:template>
    <xsl:template match="/CollectionOfPoems">
        <xsl:for-each select="Poem">
            <h2>
                <xsl:value-of select="Title" />
                -
                <xsl:value-of select="@YearOfPublication" />
            </h2>
            <xsl:choose>
                <xsl:when test="@Language = 'de'">
                    <div class="german">German</div>
                </xsl:when>
                <xsl:otherwise>
                    <div class="Greek">Greek</div>
                </xsl:otherwise>
            </xsl:choose>
            <h4>
                <xsl:value-of select="Subtitle" />
            </h4>
            <cite>
                <xsl:value-of select="Author" />
            </cite>
            <p>
                <xsl:copy-of select="Strophe" />
            </p>
        </xsl:for-each>
    </xsl:template>

</xsl:stylesheet>