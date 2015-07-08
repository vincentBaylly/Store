package com.ruby.component;

import java.sql.BatchUpdateException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ruby.dao.DAOFactory;
import com.ruby.dao.ProductDAO;
import com.ruby.store.Product;
import com.ruby.store.Products;
import com.ruby.store.Review;
import com.ruby.store.Specification;

@Component
public class StockService implements IStockService {

	private static final Logger LOG = LoggerFactory.getLogger(StockService.class);

	private Products products;

	@Override
	public Products getProducts() {

		ProductDAO productDAO = (ProductDAO) DAOFactory.getProductDAO();

		products = productDAO.load();

		LOG.debug("Send the gem to the frontEnd");

		return products;
	}

	@Override
	public Product getProduct(int productId) {

		Product productToRetrieve = null;

		for (Product product : products.getProductList()) {
			if (Integer.valueOf(productId).equals(product.getId())) {
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

	@Override
	public Review[] addReview(int productId, int nbStar, String body, String author) throws BatchUpdateException {

		ProductDAO productDAO = (ProductDAO) DAOFactory.getProductDAO();

		Product productToUpdate = getProduct(productId);

		int newReviewsNb = productToUpdate.getReviews().length + 1;

		Review reviewToAdd = new Review();
		reviewToAdd.setNbStar(nbStar);
		reviewToAdd.setBody(body);
		reviewToAdd.setAuthor(author);

		Review[] reviewsUpdated = new Review[newReviewsNb];
		for (int i = 0; i < productToUpdate.getReviews().length; i++) {
			reviewsUpdated[i] = productToUpdate.getReviews()[i];
		}
		reviewsUpdated[newReviewsNb - 1] = reviewToAdd;

		productToUpdate.setReviews(reviewsUpdated);

		if (!productDAO.update(products)) {
			throw new BatchUpdateException();
		}

		return reviewsUpdated;
	}

	@Override
	public Review[] removeReview(int productId, int reviewId) throws BatchUpdateException {

		ProductDAO productDAO = (ProductDAO) DAOFactory.getProductDAO();

		Product productToUpdate = getProduct(productId);

		int newReviewsNb = productToUpdate.getReviews().length - 1;

		Review[] reviewsUpdated = new Review[newReviewsNb];
		for (int i = 0; i < productToUpdate.getReviews().length; i++) {
			if (reviewId != productToUpdate.getReviews()[i].getId()) {
				reviewsUpdated[i] = productToUpdate.getReviews()[i];
			}
		}

		productToUpdate.setReviews(reviewsUpdated);

		if (!productDAO.update(products)) {
			throw new BatchUpdateException();
		}

		return reviewsUpdated;
	}
}
