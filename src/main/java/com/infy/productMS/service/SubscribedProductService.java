package com.infy.productMS.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.infy.productMS.dto.SubscribedProductDTO;
import com.infy.productMS.entity.SubscribedProduct;
import com.infy.productMS.repository.SubscribedProductRepository;


@Service
public class SubscribedProductService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SubscribedProductRepository subscribedProductRepo;
	
	public void addSubscribedProduct(SubscribedProductDTO subscribedProductDTO) throws Exception{
		logger.info("Add request for product {}", subscribedProductDTO);
		SubscribedProduct subscribedProduct = subscribedProductDTO.createEntity();
		subscribedProductRepo.save(subscribedProduct);
	}
	
	public List<SubscribedProductDTO> getSubscribedProducts(int buyerId) throws Exception{
		logger.info("All subscribed Products details for buyer {}", buyerId);
		List<SubscribedProduct> subscribedProducts = subscribedProductRepo.findByBuyerId(buyerId);
		List<SubscribedProductDTO> subscribedProductDTOs = new ArrayList<>();
		if(subscribedProducts.isEmpty()) {
			throw new Exception("Service.NO_SUBSCRIBED_PRODUCT_FOUND");
		}else {
			for(SubscribedProduct subscribedProduct : subscribedProducts ) {
				SubscribedProductDTO subscribedProductDTO = SubscribedProductDTO.valueOf(subscribedProduct);
				subscribedProductDTOs.add(subscribedProductDTO);
			}
		return subscribedProductDTOs;
	}

}
}
