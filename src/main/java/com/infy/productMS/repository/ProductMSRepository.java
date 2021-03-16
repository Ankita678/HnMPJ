package com.infy.productMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.productMS.entity.Product;

@Repository
public interface ProductMSRepository extends JpaRepository<Product, Integer>{
	
	List<Product> findByCategory(String category);
	List<Product> findByProductName(String productName);
	List<Product> findBySellerId(int sellerId);

}
