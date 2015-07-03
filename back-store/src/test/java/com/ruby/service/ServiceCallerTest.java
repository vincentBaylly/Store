package com.ruby.service;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import com.ruby.component.StockService;
import com.ruby.config.AppConfig;
import com.ruby.store.Products;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class ServiceCallerTest {
    
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
    @Autowired
    private StockService stockServiceValidator;
 
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    private Products products;
    
    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        products = stockServiceValidator.getProducts();
    }
    
    @Test
    public void getProductsTest() throws Exception {
    	ResultActions resultActions = mockMvc.perform(get("/data/products"));
    	resultActions.andExpect(status().isOk());
    	resultActions.andExpect(content().contentType(contentType));
    	resultActions.andExpect(jsonPath("$", hasSize(2)));
    	resultActions.andExpect(jsonPath("$[0].id", is(this.products.getProductList().get(0).getId())));
    	resultActions.andExpect(jsonPath("$[0].name", is(this.products.getProductList().get(0).getName())));
    	resultActions.andExpect(jsonPath("$[0].price", is(this.products.getProductList().get(0).getPrice())));
    	resultActions.andExpect(jsonPath("$[0].purchasable", is(this.products.getProductList().get(0).isPurchasable())));
    	resultActions.andExpect(jsonPath("$[0].description", is(this.products.getProductList().get(0).getDescription())));
    	resultActions.andExpect(jsonPath("$[1].id", is(this.products.getProductList().get(1).getId())));
    	resultActions.andExpect(jsonPath("$[1].name", is(this.products.getProductList().get(1).getName())));
    	resultActions.andExpect(jsonPath("$[1].price", is(this.products.getProductList().get(1).getPrice())));
    	resultActions.andExpect(jsonPath("$[1].purchasable", is(this.products.getProductList().get(1).isPurchasable())));
    	resultActions.andExpect(jsonPath("$[1].description", is(this.products.getProductList().get(1).getDescription())));
    }
    
    @Test
    public void getProductTest() throws Exception {
    	ResultActions resultActions = mockMvc.perform(get("/data/product").param("id", "1"));
    	resultActions.andExpect(status().isOk());
    	resultActions.andExpect(content().contentType(contentType));
    	resultActions.andExpect(jsonPath("$.id", is(this.products.getProductList().get(0).getId())));
    	resultActions.andExpect(jsonPath("$.name", is(this.products.getProductList().get(0).getName())));
    	resultActions.andExpect(jsonPath("$.price", is(this.products.getProductList().get(0).getPrice())));
    	resultActions.andExpect(jsonPath("$.purchasable", is(this.products.getProductList().get(0).isPurchasable())));
    	resultActions.andExpect(jsonPath("$.description", is(this.products.getProductList().get(0).getDescription())));
    }
    
    @Test
    public void getReviewsTest() throws Exception {
    	ResultActions resultActions = mockMvc.perform(get("/data/reviews").param("id", "1"));
    	resultActions.andExpect(status().isOk());
    	resultActions.andExpect(content().contentType(contentType));
    	resultActions.andExpect(jsonPath("$", hasSize(2)));
    	resultActions.andExpect(jsonPath("$[0].nbStar", is(this.products.getProductList().get(0).getReviews()[0].getNbStar())));
    	resultActions.andExpect(jsonPath("$[0].body", is(this.products.getProductList().get(0).getReviews()[0].getBody())));
    	resultActions.andExpect(jsonPath("$[0].author", is(this.products.getProductList().get(0).getReviews()[0].getAuthor())));
    	resultActions.andExpect(jsonPath("$[1].nbStar", is(this.products.getProductList().get(0).getReviews()[1].getNbStar())));
    	resultActions.andExpect(jsonPath("$[1].body", is(this.products.getProductList().get(0).getReviews()[1].getBody())));
    	resultActions.andExpect(jsonPath("$[1].author", is(this.products.getProductList().get(0).getReviews()[1].getAuthor())));
    }
    
    @Test
    public void getDescriptionTest() throws Exception {
    	ResultActions resultActions = mockMvc.perform(get("/data/description").param("id", "1"));
    	resultActions.andExpect(status().isOk());
    	resultActions.andExpect(content().contentType(contentType));
//    	resultActions.andExpect(jsonPath("$", is(this.products.getProductList().get(0).getDescription())));
    }
    
    @Test
    public void getSpecificationTest() throws Exception {
    	ResultActions resultActions = mockMvc.perform(get("/data/specification").param("id", "1"));
    	resultActions.andExpect(status().isOk());
    	resultActions.andExpect(content().contentType(contentType));
    	resultActions.andExpect(jsonPath("$.shine", is(this.products.getProductList().get(0).getSpecs().getShine())));
    	resultActions.andExpect(jsonPath("$.faces", is(this.products.getProductList().get(0).getSpecs().getFaces())));
    	resultActions.andExpect(jsonPath("$.rarity", is(this.products.getProductList().get(0).getSpecs().getRarity())));
    	resultActions.andExpect(jsonPath("$.color", is(this.products.getProductList().get(0).getSpecs().getColor())));
    }
    
    @Test
    public void addReviewTest() throws Exception {
    	
    	String nbStar = "4";
    	String body = "this is a test";
    	String author = "test@testcom";
    	
    	MockHttpServletRequestBuilder mockHttpServletReqB = get("/data/addReview");
    	mockHttpServletReqB.param("id", "1");
    	mockHttpServletReqB.param("nbstar", nbStar);
    	mockHttpServletReqB.param("body", body);
    	mockHttpServletReqB.param("author", author);
    	
    	ResultActions resultActions = mockMvc.perform(mockHttpServletReqB);
    	resultActions.andExpect(status().isOk());
    	resultActions.andExpect(content().contentType(contentType));
    	resultActions.andExpect(jsonPath("$", hasSize(3)));
    	resultActions.andExpect(jsonPath("$[2].nbStar", is(Integer.valueOf(nbStar))));
    	resultActions.andExpect(jsonPath("$[2].body", is(body)));
    	resultActions.andExpect(jsonPath("$[2].author", is(author)));
    }
    
    @Test
    public void removeReviewTest() throws Exception {
    	
    	MockHttpServletRequestBuilder mockHttpServletReqB = get("/data/removeReview");
    	mockHttpServletReqB.param("productid", "1");
    	mockHttpServletReqB.param("review", "2");
    	
    	ResultActions resultActions = mockMvc.perform(mockHttpServletReqB);
    	resultActions.andExpect(status().isOk());
    	resultActions.andExpect(content().contentType(contentType));
    	resultActions.andExpect(jsonPath("$", hasSize(2)));
    }
	
}
