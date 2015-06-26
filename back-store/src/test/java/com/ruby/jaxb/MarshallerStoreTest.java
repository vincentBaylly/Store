package com.ruby.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruby.store.Product;
import com.ruby.store.Products;
import com.ruby.store.Review;
import com.ruby.store.Specification;

public class MarshallerStoreTest {

	private static final Logger LOG = LoggerFactory
			.getLogger(MarshallerStoreTest.class);

	@Test
	public void masharllingStoreObject() {

		Products products = createStoreObject();

		try {

			File file = new File("src/test/resources/file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Products.class,
					Product.class, Review.class, Specification.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(products, file);
//			jaxbMarshaller.marshal(products, System.out);

		} catch (JAXBException e) {
			LOG.error("error on mashalling products", e);
			Assert.fail();
		}

	}

	@Test
	public void unmarshallingStoreObject() {
		
		try {

			File file = new File("src/test/resources/file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Products products = (Products) jaxbUnmarshaller.unmarshal(file);
//			System.out.println(products.toString());

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public Products createStoreObject() {

		LOG.debug("Generate the gem");

		// Dodecahedron Product
		Product dodecahedron = new Product();

		dodecahedron.setId(1);
		dodecahedron.setName("Dodecahedron");
		dodecahedron.setPrice(2.95);
		dodecahedron.setDescription(getDescription(1));
		dodecahedron.setPurchasable(false);

		// Reviews Table for the Dodecahedron Product
		dodecahedron.setReviews(getReviews(1));

		// Icosahedron Product
		Product icosahedron = new Product();

		icosahedron.setId(2);
		icosahedron.setName("Icosahedron");
		icosahedron.setPrice(3.95);
		icosahedron.setDescription(getDescription(2));
		icosahedron.setPurchasable(true);

		// Reviews Table for the Icosahedron Product
		icosahedron.setReviews(getReviews(2));

		// Product List
		Products products = new Products();
		products.getProductList().add(dodecahedron);
		products.getProductList().add(icosahedron);

		return products;

	}

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