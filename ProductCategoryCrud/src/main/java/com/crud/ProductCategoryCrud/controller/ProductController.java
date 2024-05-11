package com.crud.ProductCategoryCrud.controller;

import java.util.List;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.ProductCategoryCrud.entity.Product;
import com.crud.ProductCategoryCrud.service.ProductService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;



@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	private ProductService productDataService;
	
	@GetMapping("products")
	public List<Product> getAllProducts(
	        @RequestParam(value = "page", defaultValue = "0", required = false) int page,
	        @RequestParam(value = "pageSize", defaultValue = "5", required = true) int pageSize) {
	    List<Product> productList = this.productDataService.findAllProducts(page, pageSize);
	    return productList;
	}

	
	@GetMapping("products/{productId}")
	public ResponseEntity<Optional<Product>> getProductById(@PathVariable int productId) {
		Optional<Product> productObj =  this.productDataService.findProductById(productId);
		if(productObj.isPresent()) {
			return ResponseEntity.ok().body(productObj);
		}
		else {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(null);
		}
	}
	
	
	@PostMapping("products")
	public ResponseEntity<String> createProduct(@RequestBody Product productObj) {
		boolean saveStatus = this.productDataService.saveProductDB(productObj);
		if(saveStatus) {
			return ResponseEntity.ok().body("Product Saved Suceesfully");
		}
		else {
			return ResponseEntity.badRequest().body("Product Not Saved Succesffully");
		}
	}
	
	@PutMapping("products/{productId}")
	public ResponseEntity<String> updateProduct(@PathVariable int productId,@RequestBody Product obj) {
		boolean updateStatus = this.productDataService.updateProductDataDB(productId,obj);
		if(updateStatus) {
			return ResponseEntity.ok().body("Produt Updated Successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Product Not foud for the update with id : "+productId);
		}
		
	}
	
	@DeleteMapping("products/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable int productId) {
		boolean deleteStatus = this.productDataService.removeProductDB(productId);
		if(deleteStatus) {
			return ResponseEntity.ok().body("Product Deleted Successfully");
		}
		else {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Product not found with Id : "+productId);
		}
	}

}
