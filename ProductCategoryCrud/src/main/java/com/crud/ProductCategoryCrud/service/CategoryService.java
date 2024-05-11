package com.crud.ProductCategoryCrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.crud.ProductCategoryCrud.entity.Category;
import com.crud.ProductCategoryCrud.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryDataRepository;
	
	public List<Category> findAllCategories(int pageNumber,int pageSize){
		 
		PageRequest page = PageRequest.of(pageNumber, pageSize);
		Page<Category> pageCateogries = this.categoryDataRepository.findAll(page);
		
		List<Category> categoryObj = pageCateogries.getContent();
		return categoryObj;
	}
	
	
	// 1 . GetMapping Service by Id Implementations
	public Optional<List<Object[]>> getCategoryById(int categoryId) {
	
		List<Object[]> categoryObj = this.categoryDataRepository.findCategoryWithProducts(categoryId);
		return Optional.ofNullable(categoryObj);
	}
	
	// 2 . PostMapping Service Implmentation
	public void createCategorydb(Category categoryObj) {
		this.categoryDataRepository.save(categoryObj);
	}
	
	// 3 . DeleteMapping Service Implementation
	public boolean removeCategorydb(int categoryId) {
		Optional<Category> categoryObj =  this.categoryDataRepository.findById(categoryId);
		if(categoryObj.isPresent()) {
			this.categoryDataRepository.deleteById(categoryId);
			return true;
		}
		else {
			return false;
		}
	}
	
	// 4 . PutMapping Service Implemenataion
	public boolean updateCategorydb(int categoryId,Category categoryObj) {
		Optional<Category> categoryObjDB = this.categoryDataRepository.findById(categoryId);
		if(categoryObjDB.isPresent()) {
			Category persistentObj = categoryObjDB.get();
			persistentObj.setCategoryName(categoryObj.getCategoryName());
			this.categoryDataRepository.save(persistentObj);
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
