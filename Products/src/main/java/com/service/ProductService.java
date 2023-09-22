package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.data.Product;

import com.exception.AccountNotFoundException;
@Service
public interface ProductService {
	public  Product saveProduct(Product p);
	
	public Product updateProduct(Product newProduct,int id);
	
	public void deleteProduct(int pid);
	
    public Product getProductById(int pid);
	
	public List< Product > getAllProducts();
	
	public List< Product >getProductByName(String name) ;
	
	//public List< Product > getAccountsByBalanceRange(double minbal,double maxbal) ;
	
}
