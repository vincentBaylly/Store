package com.ruby.component;

import java.sql.BatchUpdateException;

import com.ruby.store.Product;
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
	 * 
	 * @param productId the id of the product
	 * @return Product the product in the list of product
	 */
	public Product getProduct(int productId);
	
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
	 * @return String description
	 */
	public String getDescription(int productId);
	
	/**
	 * return the specification of a product
	 * 
	 * @param productId the id of the product
	 * @return Specification
	 */
	public Specification getSpecification(int productId);
	
	/**
	 * add a review to the list and
	 * return the list of the review
	 * @param productId the product id
	 * @param nbStar the number of star
	 * @param body the message body review
	 * @param author the email author
	 * @return Review[] the tab of review
	 * @throws BatchUpdateException 
	 */
	public Review[] addReview(int productId, int nbStar, String body, String author) throws BatchUpdateException;
	
	/**
	 * remove a review to the list and
	 * return the list of the review
	 * @param productId
	 * @param reviewId
	 * @return Review[] the tab of review
	 * @throws BatchUpdateException 
	 */
	public Review[] removeReview(int productId, int reviewId) throws BatchUpdateException;
}
