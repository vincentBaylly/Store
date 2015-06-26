package com.ruby.service;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    public void getProductTest() throws Exception {
    	ResultActions resultActions = mockMvc.perform(get("/data/products"));
    	resultActions.andExpect(status().isOk());
    	resultActions.andExpect(content().contentType(contentType));
    	resultActions.andExpect(jsonPath("$[0].size", hasSize(2)));
    	resultActions.andExpect(jsonPath("$productList[0].id", is(this.products.getProductList().get(0).getId())));
    	resultActions.andExpect(jsonPath("$productList[0].name", is(this.products.getProductList().get(0).getName())));
    	resultActions.andExpect(jsonPath("$productList[0].price", is(this.products.getProductList().get(0).getPrice())));
    	resultActions.andExpect(jsonPath("$productList[0].purchasable", is(this.products.getProductList().get(0).isPurchasable())));
    	//resultActions.andExpect(jsonPath("$productList[0].description", is(this.products.getProductList().get(0).getDescription())));
    	resultActions.andExpect(jsonPath("$productList[1].id", is(this.products.getProductList().get(1).getId())));
    	resultActions.andExpect(jsonPath("$productList[1].name", is(this.products.getProductList().get(1).getName())));
    	resultActions.andExpect(jsonPath("$productList[1].price", is(this.products.getProductList().get(1).getPrice())));
    	resultActions.andExpect(jsonPath("$productList[1].purchasable", is(this.products.getProductList().get(1).isPurchasable())));
    	//resultActions.andExpect(jsonPath("$productList[1].description", is(this.products.getProductList().get(0).getDescription())));
    }
	
}
