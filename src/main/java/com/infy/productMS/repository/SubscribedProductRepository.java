package com.infy.productMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.productMS.entity.SubscribedProduct;

@Repository
public interface SubscribedProductRepository extends JpaRepository<SubscribedProduct,Integer> {
	
	public List<SubscribedProduct> findByBuyerId(int buyerId);

}
