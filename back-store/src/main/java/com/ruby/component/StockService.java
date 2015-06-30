package com.ruby.component;

import java.io.File;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ruby.store.Product;
import com.ruby.store.Products;
import com.ruby.store.Review;
import com.ruby.store.Specification;

@Component
public class StockService implements IStockService {

	private static final Logger LOG = LoggerFactory
			.getLogger(StockService.class);

	private Products products;
	
	public void init(){
	
		try {
			
			File file = new File(this.getClass().getResource("/data/products.xml").toURI());
			
			
			// create JAXB context and instantiate marshaller
			JAXBContext context = JAXBContext.newInstance(Products.class, Product.class, Specification.class, Review.class);
	
			Unmarshaller unmarshaller = context.createUnmarshaller();
			products = (Products) unmarshaller.unmarshal(file);
			
		} catch (URISyntaxException ex) {
			LOG.error("file not find", ex);
		} catch (JAXBException ex) {
			LOG.error("jaxb Exception on product unmarshalling element", ex);
		}
	}
	
	@Override
	public Products getProducts() {

		init();

		LOG.debug("Send the gem to the frontEnd");

		return products;
	}
	
	@Override
	public Product getProduct(int productId) {
		
		Product productToRetrieve = null;
		
		for(Product product : products.getProductList()){
			if(Integer.valueOf(productId).equals(product.getId())){
				productToRetrieve = product;
				break;
			}
		}
		
		return productToRetrieve;
	}

	@Override
	public Review[] getReviews(int productId) {

		LOG.debug("Send the reviews to the frontEnd");

		return getProduct(productId).getReviews();
	}

	@Override
	public String getDescription(int productId) {

		LOG.debug("Send the description to the frontEnd");

		return getProduct(productId).getDescription();
	}

	@Override
	public Specification getSpecification(int productId) {

		LOG.debug("Send the specification to the frontEnd");

		return getProduct(productId).getSpecs();
	}
}
