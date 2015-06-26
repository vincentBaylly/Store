package com.ruby.store;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Specification {
	
	@XmlElement(name = "Shine")
	private String shine;
	
	@XmlElement(name = "Faces")
	private int faces;
	
	@XmlElement(name = "Rarity")
	private String rarity;
	
	@XmlElement(name = "Color")
	private String color;
	
	public String getShine() {
		return shine;
	}
	
	public void setShine(String shine) {
		this.shine = shine;
	}
	
	public int getFaces() {
		return faces;
	}
	
	public void setFaces(int faces) {
		this.faces = faces;
	}
	
	public String getRarity() {
		return rarity;
	}
	
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Specification [shine=" + shine + ", faces=" + faces
				+ ", rarity=" + rarity + ", color=" + color + "]";
	}

}
