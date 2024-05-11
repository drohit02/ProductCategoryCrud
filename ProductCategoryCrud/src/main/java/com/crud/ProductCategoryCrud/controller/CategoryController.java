package com.crud.ProductCategoryCrud.controller;

import java.util.List;
import java.util.Optional;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.crud.ProductCategoryCrud.entity.Category;
import com.crud.ProductCategoryCrud.service.CategoryService;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	@Autowired
	private CategoryService categoryDataService;

	@GetMapping("categories")
	public List<Category> getAllCategories(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "5", required = true) int pageSize) {

		List<Category> categoryList = this.categoryDataService.findAllCategories(page, pageSize);
		return categoryList;
	}

	@GetMapping("categories/{categoryId}")
	public ResponseEntity<Optional<List<Object[]>>> findCategoryById(@PathVariable int categoryId) {
		Optional<List<Object[]>> categoryObj = this.categoryDataService.getCategoryById(categoryId);
		if (categoryObj != null && !categoryObj.isEmpty()) {
			return ResponseEntity.ok(categoryObj);
		} 
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("categories")
	public ResponseEntity<String> createCategory(@RequestBody Category categoryObj) {
		this.categoryDataService.createCategorydb(categoryObj);
		return ResponseEntity.ok().body("Category Saved Successfully");
	}

	@PutMapping("categories/{categoryId}")
	public ResponseEntity<String> updateCategory(@PathVariable int categoryId, @RequestBody Category categoryObj) {
		boolean updateStuatus = this.categoryDataService.updateCategorydb(categoryId, categoryObj);
		if (updateStuatus) {
			return ResponseEntity.ok().body("Category Data Updated Successfully");
		} 
		else {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Category Not Present with given ID : " + categoryId);
		}

	}

	@DeleteMapping("categories/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable int categoryId) {
		boolean deleteStatus = this.categoryDataService.removeCategorydb(categoryId);
		if (deleteStatus) {
			return ResponseEntity.ok().body("Category Removed Successfuly");
		} 
		else {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("Category Not Found for given id : " + categoryId);
		}

	}

}
