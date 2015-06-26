package com.ruby.component;

import com.ruby.store.Products;
import com.ruby.store.Review;
import com.ruby.store.Specification;

public interface IStockService {
	
	/**
	 * return the list of the product
	 * in a table 
	 * 
	 * @return Products the list of product 
	 */
	public Products getProducts();
	
	/**
	 * return the list of the review
	 * 
	 * @param productId the id of the product
	 * @return Review[] the tab of review
	 */
	public Review[] getReviews(int productId);
	
	/**
	 * return the description of a product
	 * 
	 * @param productId the id of the product
	 * @return StringBuilder description
	 */
	public StringBuilder getDescription(int productId);
	
	/**
	 * return the specification of a product
	 * 
	 * @param productId the id of the product
	 * @return Specification
	 */
	public Specification getSpecification(int productId);
}
