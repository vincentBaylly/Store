package com.ruby.store;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Products")
@XmlAccessorType(XmlAccessType.FIELD)
public class Products {

	@XmlElement(type = Product.class)
	private List<Product> productList;

	public Products() {
		productList = new ArrayList<Product>();
	}

	public Products(List<Product> productList) {
		this.productList = productList;
	}
	
	public List<Product> getProductList() {
		return productList;
	}
	
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	@Override
	public String toString(){
		
		String listOfProduct = "Products [";
		
		for(Product product : productList){
			listOfProduct = listOfProduct + product.toString();
		}
		
		listOfProduct = listOfProduct + "]";
		
		return listOfProduct;
	}

}
