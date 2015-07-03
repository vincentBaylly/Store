package com.ruby.store;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Review {
	
	@XmlAttribute(name = "Id")
	private int id;
	
	@XmlElement(name = "NbStar")
	private int nbStar;
	
	@XmlElement(name = "Body")
	private String body;
	
	@XmlElement(name = "Author")
	private String author;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getNbStar() {
		return nbStar;
	}
	
	public void setNbStar(int nbStar) {
		this.nbStar = nbStar;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", nbStar=" + nbStar + ", body=" + body + ", author="
				+ author + "]";
	}
}
