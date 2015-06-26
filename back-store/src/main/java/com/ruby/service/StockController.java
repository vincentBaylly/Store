package com.ruby.service;

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
	public Product[] getProducts() {
		Products products = productService.getProducts();
		
		LOG.debug("Product List : " + products.toString());
		
		return (Product[])products.getProductList().toArray();
	}

	@RequestMapping("/reviews")
	public Review[] getReviews(
			@RequestParam(value = "id", required = false, defaultValue = "0") int id) {
		Review[] reviews = productService.getReviews(id);
		
		LOG.debug("Reviews : " + Arrays.toString(reviews));
		
		return reviews;
	}

	@RequestMapping("/description")
	public StringBuilder getDescription(
			@RequestParam(value = "id", required = false, defaultValue = "0") int id) {
		StringBuilder desc = productService.getDescription(id);
		
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
}
