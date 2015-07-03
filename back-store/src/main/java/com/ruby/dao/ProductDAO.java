package com.ruby.dao;

import java.io.File;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruby.store.Product;
import com.ruby.store.Products;
import com.ruby.store.Review;
import com.ruby.store.Specification;

public class ProductDAO extends DAO<Products>{
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductDAO.class);
	
	private File file; 
	
	public ProductDAO() {
		super();
		
		try{
			file = new File(this.getClass().getResource("/data/products.xml").toURI());
		} catch (URISyntaxException ex) {
			LOG.error("file not find", ex);
		}
	}
	
	public Products load(){
		
		Products products = null;
		
		try {
			
			// create JAXB context and instantiate marshaller
			JAXBContext context = JAXBContext.newInstance(Products.class, Product.class, Specification.class, Review.class);
	
			Unmarshaller unmarshaller = context.createUnmarshaller();
			products = (Products) unmarshaller.unmarshal(file);
			
		} catch (JAXBException ex) {
			LOG.error("jaxb Exception on product unmarshalling element", ex);
		}
		
		return products;

	}
	
	public boolean update(Products products){
		
		boolean updated = false;
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Products.class,
					Product.class, Review.class, Specification.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(products, file);
			
			updated = true;
			
		} catch (JAXBException e) {
			LOG.error("error on mashalling products", e);
		}
		
		return updated;
	}
	
	public boolean delete(Products products){
		
		boolean delete = false;
		
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Products.class,
					Product.class, Review.class, Specification.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(products, file);
			
			delete = true;
			
		} catch (JAXBException e) {
			LOG.error("error on mashalling products", e);
		}
		
		return delete;
	}
	
}
