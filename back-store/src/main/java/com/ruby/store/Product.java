package com.ruby.store;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
	
	@XmlAttribute(name = "Id")
	private int id;
	
	@XmlElement(name = "Name")
	private String name;
	
	@XmlElement(name = "Price")
	private double price;
	
	@XmlElement(name = "Purchasable")
	private boolean purchasable;
	
	@XmlElement(name = "Description", type = StringBuilder.class)
	private StringBuilder description;
	
	@XmlElement(name = "Specification", type = Specification.class)
	private Specification specs;
	
	@XmlElementWrapper(name = "Reviews")
	@XmlElement(name = "Review", type = Review.class)
	private Review[] reviews;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public boolean isPurchasable() {
		return purchasable;
	}
	
	public void setPurchasable(boolean purchasable) {
		this.purchasable = purchasable;
	}
	
	public StringBuilder getDescription() {
		return description;
	}

	public void setDescription(StringBuilder description) {
		this.description = description;
	}

	public Specification getSpecs() {
		return specs;
	}

	public void setSpecs(Specification specs) {
		this.specs = specs;
	}

	public Review[] getReviews() {
		return reviews;
	}
	
	public void setReviews(Review[] reviews) {
		this.reviews = reviews;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", purchasable=" + purchasable + ", Description="
				+ description + ", specs=" + specs + ", reviews="
				+ Arrays.toString(reviews) + "]";
	}
	
}
