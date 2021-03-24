package com.stir.cscu9t4assignment2021;

import java.io.Serializable;
import javax.xml.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class xmlData implements Serializable {
	
	
	private String xmltitle;
	private String xmlpubtype;
	private String xmlauthor;
	private String xmlyear;
	private String xmlpubname;
	private String xmldoi;
	private String xmldate;
	private String xmljourname;
	private String xmljourvol;
	private String xmljourissue;
	private String xmlconfname;
	private String xmlconfloc;
	private String xmlbooktitle;
	private String xmlbookeditor;
	
	
	
	
	public String getXmltitle() {
		return xmltitle;
	}
	public void setXmltitle(String xmltitle) {
		this.xmltitle = xmltitle;
	}
	public String getXmlpubtype() {
		return xmlpubtype;
	}
	public void setXmlpubtype(String xmlpubtype) {
		this.xmlpubtype = xmlpubtype;
	}
	public String getXmlauthor() {
		return xmlauthor;
	}
	public void setXmlauthor(String xmlauthor) {
		this.xmlauthor = xmlauthor;
	}
	public String getXmlyear() {
		return xmlyear;
	}
	public void setXmlyear(String xmlyear) {
		this.xmlyear = xmlyear;
	}
	public String getXmlpubname() {
		return xmlpubname;
	}
	public void setXmlpubname(String xmlpubname) {
		this.xmlpubname = xmlpubname;
	}
	public String getXmldoi() {
		return xmldoi;
	}
	public void setXmldoi(String xmldoi) {
		this.xmldoi = xmldoi;
	}
	public String getXmldate() {
		return xmldate;
	}
	public void setXmldate(String xmldate) {
		this.xmldate = xmldate;
	}
	public String getXmljourname() {
		return xmljourname;
	}
	public void setXmljourname(String xmljourname) {
		this.xmljourname = xmljourname;
	}
	public String getXmljourvol() {
		return xmljourvol;
	}
	public void setXmljourvol(String xmljourvol) {
		this.xmljourvol = xmljourvol;
	}
	public String getXmljourissue() {
		return xmljourissue;
	}
	public void setXmljourissue(String xmljourissue) {
		this.xmljourissue = xmljourissue;
	}
	public String getXmlconfname() {
		return xmlconfname;
	}
	public void setXmlconfname(String xmlconfname) {
		this.xmlconfname = xmlconfname;
	}
	public String getXmlconfloc() {
		return xmlconfloc;
	}
	public void setXmlconfloc(String xmlconfloc) {
		this.xmlconfloc = xmlconfloc;
	}
	public String getXmlbooktitle() {
		return xmlbooktitle;
	}
	public void setXmlbooktitle(String xmlbooktitle) {
		this.xmlbooktitle = xmlbooktitle;
	}
	public String getXmlbookeditor() {
		return xmlbookeditor;
	}
	public void setXmlbookeditor(String xmlbookeditor) {
		this.xmlbookeditor = xmlbookeditor;
	}
	
	

}
