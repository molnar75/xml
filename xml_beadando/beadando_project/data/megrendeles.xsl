<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
<xsl:output method="html" indent="yes" encoding="UTF-8"/>
<xsl:template match="/">
<xsl:element name="html">
   <xsl:element name="head">
    <xsl:element name="title">
    	3. beadandó feladat
 	</xsl:element>
   </xsl:element>
   <xsl:element name="body">
     <xsl:element name="h1">
	 	Éttermek
     </xsl:element>
     <xsl:element name="table">
     	<xsl:attribute name="style">
		    width: 50%;
		    text-align: center;
		    border-collapse: collapse;
		    border: 1px solid black;
		    margin-left: 20px;
		 </xsl:attribute>
   		<xsl:element name="thead">
		</xsl:element>
		<xsl:element name="tr">
			<xsl:attribute name="style">
		    	border: 1px solid black;
		 	</xsl:attribute>
			<xsl:element name="th">
			<xsl:attribute name="style">
		    	border: 1px solid black;
		 	</xsl:attribute>
				Név
			</xsl:element>
			<xsl:element name="th">
			<xsl:attribute name="style">
		    	border: 1px solid black;
		 	</xsl:attribute>
				Weblap
			</xsl:element>
			<xsl:element name="th">
			<xsl:attribute name="style">
		    	border: 1px solid black;
		 	</xsl:attribute>
				Minősítés
			</xsl:element>
			<xsl:element name="th">
			<xsl:attribute name="style">
		    	border: 1px solid black;
		 	</xsl:attribute>
				Telefonszám
			</xsl:element>
		</xsl:element>
  		<xsl:apply-templates select="nyilvantartas/ettermek"/>
	</xsl:element>
	<xsl:element name="h1">
	 	Rendezvények
     </xsl:element>
     <xsl:element name="table">
     	<xsl:attribute name="style">
		    width: 50%;
		    text-align: center;
		    border-collapse: collapse;
		    border: 1px solid black;
		    margin-left: 20px;
		 </xsl:attribute>
   		<xsl:element name="thead">
		</xsl:element>
		<xsl:element name="tr">
			<xsl:attribute name="style">
		    	border: 1px solid black;
		 	</xsl:attribute>
			<xsl:element name="th">
			<xsl:attribute name="style">
		    	border: 1px solid black;
		 	</xsl:attribute>
				Időpont
			</xsl:element>
			<xsl:element name="th">
			<xsl:attribute name="style">
		    	border: 1px solid black;
		 	</xsl:attribute>
				Cím
			</xsl:element>
		</xsl:element>
  		<xsl:apply-templates select="nyilvantartas/rendezvenyek"/>
	</xsl:element>
	<xsl:element name="h1">
	 	Megrendelések
     </xsl:element>
     <xsl:element name="table">
     	<xsl:attribute name="style">
		    width: 50%;
		    text-align: center;
		    border-collapse: collapse;
		    border: 1px solid black;
		    margin-left: 20px;
		 </xsl:attribute>
   		<xsl:element name="thead">
		</xsl:element>
		<xsl:element name="tr">
			<xsl:attribute name="style">
		    	border: 1px solid black;
		 	</xsl:attribute>
		 	<xsl:element name="th">
			<xsl:attribute name="style">
		    	border: 1px solid black;
		 	</xsl:attribute>
				Ár
			</xsl:element>
			<xsl:element name="th">
			<xsl:attribute name="style">
		    	border: 1px solid black;
		 	</xsl:attribute>
				Időpont
			</xsl:element>
			<xsl:element name="th">
			<xsl:attribute name="style">
		    	border: 1px solid black;
		 	</xsl:attribute>
				Cím
			</xsl:element>
			<xsl:element name="th">
			<xsl:attribute name="style">
		    	border: 1px solid black;
		 	</xsl:attribute>
				Étterem neve
			</xsl:element>
		</xsl:element>
  		<xsl:apply-templates select="nyilvantartas/megrendelesek"/>
	</xsl:element>
   </xsl:element>
  </xsl:element>	
 </xsl:template>
 
 <xsl:template match="nyilvantartas/ettermek">
   <xsl:for-each select="etterem">
	   <xsl:element name="tr">
	     <xsl:element name="td">
		     <xsl:attribute name="style">
			    border: 1px solid black;
			 </xsl:attribute>
	     	<xsl:value-of select="nev"/>
		 </xsl:element>
	     <xsl:element name="td">
		     <xsl:attribute name="style">
			    border: 1px solid black;
			 </xsl:attribute>
	     	<xsl:value-of select="weblap"/>
		 </xsl:element>
		 <xsl:element name="td">
			 <xsl:attribute name="style">
			    border: 1px solid black;
			 </xsl:attribute>
	     	<xsl:value-of select="minosites"/>
		 </xsl:element>
		 <xsl:element name="td">
			 <xsl:attribute name="style">
			    border: 1px solid black;
			 </xsl:attribute>
	     	<xsl:value-of select="telefonszam"/>
		 </xsl:element>
	   </xsl:element>
    </xsl:for-each>  
 </xsl:template>
 
 <xsl:template match="nyilvantartas/rendezvenyek">
   <xsl:for-each select="rendezveny">
	   <xsl:element name="tr">
	     <xsl:element name="td">
		     <xsl:attribute name="style">
			    border: 1px solid black;
			 </xsl:attribute>
	     	<xsl:value-of select="idopont"/>
		 </xsl:element>
	     <xsl:element name="td">
		     <xsl:attribute name="style">
			    border: 1px solid black;
			 </xsl:attribute>
	     	<xsl:value-of select="cim"/>
		 </xsl:element>
		</xsl:element>
    </xsl:for-each>  
 </xsl:template>

<xsl:template match="nyilvantartas/megrendelesek">
   <xsl:for-each select="megrendeles">
   <xsl:variable name="eid" select="@etterem"/>
   <xsl:variable name="rid" select="@rendezveny"/>
	   <xsl:element name="tr">
	   	<xsl:element name="td">
		     <xsl:attribute name="style">
			    border: 1px solid black;
			 </xsl:attribute>
	     	<xsl:value-of select="ar"/>
		 </xsl:element>
	     <xsl:element name="td">
		     <xsl:attribute name="style">
			    border: 1px solid black;
			 </xsl:attribute>
	     	<xsl:value-of select="../../rendezvenyek/rendezveny[@rid=$rid]/idopont"/>
		 </xsl:element>
	     <xsl:element name="td">
		     <xsl:attribute name="style">
			    border: 1px solid black;
			 </xsl:attribute>
	     	<xsl:value-of select="../../rendezvenyek/rendezveny[@rid=$rid]/cim"/>
		 </xsl:element>
		 <xsl:element name="td">
		     <xsl:attribute name="style">
			    border: 1px solid black;
			 </xsl:attribute>
	     	<xsl:value-of select="../../ettermek/etterem[@eid=$eid]/nev"/>
		 </xsl:element>
		</xsl:element>
    </xsl:for-each>  
 </xsl:template>
 
</xsl:stylesheet>