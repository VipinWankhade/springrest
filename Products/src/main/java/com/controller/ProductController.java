package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.advice.ErrorResponse;
import com.dao.ProductDAO;
import com.data.Product;
import com.data.Product;
import com.exception.AccountNotFoundException;
import com.service.ProductService;


@RestController
//@CrossOrigin(origins="http://localhost:4200",methods = {RequestMethod.GET})
@CrossOrigin(origins="http://localhost:8081",methods = {RequestMethod.GET})
public class ProductController {
	@Autowired
	ProductService productService;
	

	
	@GetMapping("/products")
	public List<Product> showAllproducts()
	{
		List<Product> alist=productService.getAllProducts();
		return alist;
	}
	
	 @PostMapping("/products")
	  public Product newProduct(@Valid @RequestBody Product newProduct) {
		 return productService.saveProduct(newProduct);
	  }
	   
	 @GetMapping("/products/{id}")
	  public Product showByPid(@PathVariable Integer id)  {

		 return productService.getProductById(id);
	  }

	 @GetMapping("/ProductsByName/{name}")
	  public List<Product> showByName(@PathVariable String name)  {

		 if(name==null || name.isEmpty()) {
			 throw new InputMismatchException("Customer can not be Empty");
		 }
		 else
			 return productService.getProductByName(name) ;
	  }

//	 @GetMapping("/accountsByBalance/{minbal}/{maxbal}")
//	  public List<Product> showByBalanceRange(@PathVariable double minbal,@PathVariable double maxbal) throws AccountNotFoundException {
//
//		 if(minbal<0 || maxbal<0 || minbal>maxbal) {
//			 throw new InputMismatchException("Enter proper range of balance");
//		 }
//		 else
//			  return productService.getAccountsByBalanceRange(minbal, maxbal);
//	  }
	    
	 @PutMapping("/products/{id}")
	  public Product replaceProduct(@Valid @RequestBody Product newProduct, @PathVariable Integer id) {
		 
	
	        return productService.updateProduct(newProduct,id);

	  }
	        
	 @DeleteMapping("/products/{id}")
	  public void deleteProduct(@PathVariable Integer id) {
		 productService.deleteProduct(id);
	  }
	 
	 
	 // local to the RestController
	 @ExceptionHandler(InputMismatchException.class)
	    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	        List<String> details = new ArrayList<>();
	        details.add(ex.getLocalizedMessage());
	        ErrorResponse error = new ErrorResponse("Server Error From controller", details);
	        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
}
