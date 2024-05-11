package com.crud.ProductCategoryCrud.service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.crud.ProductCategoryCrud.entity.Product;
import com.crud.ProductCategoryCrud.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productDataRepository;
	
	// 0 . GetMapping for All Product
	public List<Product> findAllProducts(int pageNumber,int pageSize) {
		PageRequest page =  PageRequest.of(pageNumber, pageSize);
		
		Page<Product> pageProduct = this.productDataRepository.findAll(page);
		List<Product> productList = pageProduct.getContent();
		
		return productList;
	}
	
		
	// 1 . GetMapping by ID Service Implementations
	public Optional<Product> findProductById(int productId) {
		Optional<Product> productObj = this.productDataRepository.findById(productId);
		return productObj;
	}
	
	// 2. PostMapping Service implementation
	public boolean saveProductDB(Product productObj) {
		if(productObj!=null) {
			this.productDataRepository.save(productObj);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removeProductDB(int productId) {
		Optional<Product> productObj = this.productDataRepository.findById(productId);
		if(productObj.isPresent()) {
			this.productDataRepository.deleteById(productId);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean updateProductDataDB(int productId,Product obj) {
		Optional<Product> DBObject = this.productDataRepository.findById(productId);
		if(DBObject.isPresent()) {
			Product productObj = DBObject.get();
			productObj.setProductName(obj.getProductName());
			productObj.setPrice(obj.getPrice());
			productObj.setCategory(obj.getCategory());
			this.productDataRepository.save(productObj);
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
