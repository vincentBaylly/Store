package com.ruby.service;

import java.sql.BatchUpdateException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ruby.component.IStockService;
import com.ruby.store.Product;
import com.ruby.store.Products;
import com.ruby.store.Review;
import com.ruby.store.Specification;

@RestController
@RequestMapping(value="/data", produces="application/json;charset=UTF-8")
public class StockController {
	
	private static final Logger LOG = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	private IStockService productService;

	@RequestMapping("/products")
	public Object[] getProducts() {
		Products products = productService.getProducts();
		
		LOG.debug("Product List : " + products.toString());
		
		return (Object[])products.getProductList().toArray();
	}
	
	@RequestMapping("/product")
	public Object getProduct(@RequestParam(value = "id", required = false, defaultValue = "0") int id) {
		Product product = productService.getProduct(id);
		
		LOG.debug("Product List : " + product.toString());
		
		return (Object)product;
	}

	@RequestMapping("/reviews")
	public Review[] getReviews(
			@RequestParam(value = "id", required = false, defaultValue = "0") int id) {
		Review[] reviews = productService.getReviews(id);
		
		LOG.debug("Reviews : " + Arrays.toString(reviews));
		
		return reviews;
	}

	@RequestMapping("/description")
	public String getDescription(
			@RequestParam(value = "id", required = false, defaultValue = "0") int id) {
		String desc = productService.getDescription(id);
		
		LOG.debug("Description : " + desc.toString());
		
		return desc;
	}

	@RequestMapping("/specification")
	public Specification getSpecification(
			@RequestParam(value = "id", required = false, defaultValue = "0") int id) {
		Specification specs = productService.getSpecification(id);
		
		LOG.debug(specs.toString());
		
		return specs;
	}
	
	@RequestMapping("/addReview")
	public Review[] addReview(
			@RequestParam(value = "id", required = false, defaultValue = "0") int id,
			@RequestParam(value = "nbstar", required = false, defaultValue = "1") int nbStar, 
			@RequestParam(value = "body", required = false) String body, 
			@RequestParam(value = "author", required = false, defaultValue = "jimmyDean@example.org") String author) {
		
		Review[] reviews = null;
		try{
			
			reviews = productService.addReview(id, nbStar, body, author);
			
		}catch(BatchUpdateException e){
			LOG.error("Error On Update review");
		}
		
		LOG.debug("Added Review : NbStar: " + nbStar + " body: " + body + " author: " + author );
		LOG.debug("To the product number :" + id );
		LOG.debug("Reviews : " + Arrays.toString(reviews));
		
		return reviews;
	}
	
	@RequestMapping("/removeReview")
	public Review[] removeReview(
			@RequestParam(value = "productid", required = false, defaultValue = "0") int productId,
			@RequestParam(value = "reviewid", required = false, defaultValue = "0") int reviewId) {
		
		Review[] reviews = null;
		try{
			
			reviews = productService.removeReview(productId, reviewId);
			
		}catch(BatchUpdateException e){
			LOG.error("Error On Delete review");
		}
		
		LOG.debug("Remove Review : " + reviewId );
		LOG.debug("To the product number :" + productId );
		LOG.debug("Reviews : " + Arrays.toString(reviews));
		
		return reviews;
	}
}
