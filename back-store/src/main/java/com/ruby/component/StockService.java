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
			JAXBContext context = null;
			context = JAXBContext.newInstance(Products.class, Product.class, Specification.class, Review.class);
	
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
	public Review[] getReviews(int productId) {

		LOG.debug("Generate the reviews");

		Review[] reviews = null;

		// Review 1
		Review review1 = new Review();
		review1.setNbStar(5);
		review1.setBody("I love this product!");
		review1.setAuthor("joe@thomas.com");

		// Review 2
		Review review2 = new Review();
		review2.setNbStar(1);
		review2.setBody("This product sucks");
		review2.setAuthor("tim@hater.com");

		if (productId == 2) {
			// Review 3
			Review review3 = new Review();
			review3.setNbStar(5);
			review3.setBody("This one shine a lots!");
			review3.setAuthor("vince@flash.com");

			reviews = new Review[] { review1, review2, review3 };

		} else {
			reviews = new Review[] { review1, review2 };
		}

		LOG.debug("Send the reviews to the frontEnd");

		return reviews;
	}

	@Override
	public StringBuilder getDescription(int productId) {

		LOG.debug("Generate the description");

		StringBuilder sb = new StringBuilder();

		if (productId == 1) {
			sb.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ");
			sb.append("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ");
			sb.append("Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. ");
			sb.append("Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
		}

		if (productId == 2) {
			sb.append("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, ");
			sb.append("totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. ");
			sb.append("Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, ");
			sb.append("sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ");
			sb.append("Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, ");
			sb.append("sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. ");
			sb.append("Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? ");
			sb.append("Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?");
		}

		LOG.debug("Send the description to the frontEnd");

		return sb;
	}

	@Override
	public Specification getSpecification(int productId) {

		LOG.debug("Generate the specification");

		Specification specs = new Specification();

		if (productId == 1) {
			specs.setShine("low");
			specs.setFaces(12);
			specs.setRarity("rare");
			specs.setColor("blue");
		}

		if (productId == 2) {
			specs.setShine("high");
			specs.setFaces(20);
			specs.setRarity("often");
			specs.setColor("red");
		}

		LOG.debug("Send the specification to the frontEnd");

		return specs;
	}
}
