package com.crud.ProductCategoryCrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.ProductCategoryCrud.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
}
