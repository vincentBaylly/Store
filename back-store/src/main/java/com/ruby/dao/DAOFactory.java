package com.ruby.dao;

public class DAOFactory {
	
	private static DAO<?> productDAO;
	
	public static DAO<?> getProductDAO(){
		if(productDAO == null){
			productDAO = new ProductDAO();
		}
		
		return productDAO;
	}
}
