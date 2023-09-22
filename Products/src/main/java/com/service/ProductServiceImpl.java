package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductDAO;
import com.data.Product;
import com.exception.AccountNotFoundException;

@Service("productService")
public class  ProductServiceImpl implements ProductService{

	@Autowired
	ProductDAO productDAO;
	
	@Transactional
	@Override
	public  Product  saveProduct( Product  p) {
			return productDAO.save(p);
	}

	@Transactional
	@Override
	public  Product  updateProduct( Product  newProduct,int id) {
		// TODO Auto-generated method stub
		 Optional< Product > optac=productDAO.findById(id);
		 Product  ac=optac.get();
		 if(ac==null) {
				throw new AccountNotFoundException("Could not find Account with id "+id);
			}
//		 System.out.println(ac+ " in update");
		 ac.setPid(newProduct.getPid());
	     ac.setProductName(newProduct.getProductName());
	     ac.setPrice(newProduct .getPrice());
	       
	        Product  o= productDAO.save(ac);
	//       System.out.println(o + "After update");
		return o;
	}

	@Transactional
	@Override
	public void deleteProduct(int pid) {
		// TODO Auto-generated method stub
		productDAO.deleteById(pid);
	}

	@Transactional
	@Override
	public List< Product > getAllProducts() {
		// TODO Auto-generated method stub
		return productDAO.findAll();
	}

	@Transactional
	@Override
	public Product  getProductById(int pid)  {
	
		Optional< Product > ac=productDAO.findById(pid);
		if(!ac.isPresent()) {
			throw new AccountNotFoundException("Could not find Account with id "+pid);
		}
		else
			return ac.get();
	}

	@Override
	public List< Product > getProductByName(String name)  {
		// TODO Auto-generated method stub
		List<Product> alist=productDAO.findByName(name);
		if(alist.size()==0) {
			throw new AccountNotFoundException("Could not find any Account with customer "+name);
		}
		else
			return alist;
		
	}

//	@Override
//	public List< Product > getAccountsByBalanceRange(double minbal, double maxbal)  {
//		List< Product > alist=productDAO.findByBalanceBetween(minbal, maxbal);
//		if(alist.size()==0) {
//			throw new AccountNotFoundException("Could not find any Account in balance range of  "+minbal +" and "+maxbal);
//		}
//		else
//			return alist;
//	}

}

