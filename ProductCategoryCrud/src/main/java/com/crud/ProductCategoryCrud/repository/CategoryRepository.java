package com.crud.ProductCategoryCrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crud.ProductCategoryCrud.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	@Query("SELECT DISTINCT c.categoryId, c.categoryName, p.productId, p.productName, p.price FROM Category c LEFT JOIN c.products p WHERE c.categoryId = :categoryId")
    List<Object[]> findCategoryWithProducts(@Param("categoryId") int categoryId);
}
