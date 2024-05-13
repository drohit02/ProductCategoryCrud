package com.crud.ProductCategoryCrud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "product")
public class Product {
    
    @Id
    @Column(name = "product_id")
    @GeneratedValue
    private int productId;
    
    @Column(name="product_name")
    private String productName;
    
    @Column(name="price")
    private double price;
    
    @ManyToOne
    @JoinColumn(name = "category_data")
    private Category category;
    
    
    public Product() {
		// TODO Auto-generated constructor stub
	}


	public Product(int productId, String productName, double price, Category category) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.category = category;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return " {productId=" + productId + ", productName=" + productName + ", price=" + price + ", category="
				+ category + "}";
	}
    
	
    
}
