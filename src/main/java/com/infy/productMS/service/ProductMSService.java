package com.infy.productMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.productMS.dto.ProductDTO;
import com.infy.productMS.entity.Product;
import com.infy.productMS.repository.ProductMSRepository;
import com.infy.productMS.validator.Validator;

@Service
public class ProductMSService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductMSRepository productRepo;
	
	public void addProduct(ProductDTO productDTO) throws Exception{
		Validator.validateAdd(productDTO);
		logger.info("Add request for product {}", productDTO);
		Product product = productDTO.createEntity();
		productRepo.save(product);
	}
	
	public void removeProduct(int prodId) throws Exception {
		logger.info("Remove request for product {}", prodId);
		Optional<Product> product = productRepo.findById(prodId);
		if (product.isPresent()) {
			productRepo.deleteById(prodId);
		}else {
			throw new Exception("Service.NO_PRODUCT_FOUND");
		}
	}
	
	public void deleteAllProducts(int sellerId) throws Exception{
		logger.info("Remove request for products from Seller {}", sellerId);
		List<Product> products = productRepo.findBySellerId(sellerId);
		if(products.isEmpty()) {
			throw new Exception("Service.NO_PRODUCT_FOUND");
		}else {
			for(Product p: products) {
				productRepo.delete(p);
			}	
		}
	}
	
	public void updateStock(int prodId, int updatedStock) throws Exception {
		logger.info("Update Stock request for product {}", prodId);
		Optional<Product> optProduct = productRepo.findById(prodId);
		Product product = null;
		if(optProduct.isPresent()) {			
			product = optProduct.get();
		}		
		if(Validator.validateStock(updatedStock)) {
			product.setStock(updatedStock);
			productRepo.save(product);
		}else {
			throw new Exception("Validator.INVALID_STOCK");
		}
	}
		
	public List<ProductDTO> getAllProducts() throws Exception{
		logger.info("All Products details ");
		List<Product> products = productRepo.findAll();
		List<ProductDTO> productDTOs = new ArrayList<>();
		if(products.isEmpty()) {
			throw new Exception("Service.NO_PRODUCT_FOUND");
		}else {
			for(Product product: products) {
				ProductDTO productDTO = ProductDTO.valueOf(product);
				productDTOs.add(productDTO);
			}
		}		
		return productDTOs;
	}
	
	public List<ProductDTO> getSpecificProductByCategory(String category) throws Exception {
		logger.info("Product details for category {}", category);
		
		List<Product> products = productRepo.findByCategory(category);
		List<ProductDTO> productDTOs = new ArrayList<>();
		if(products.isEmpty()) {
			throw new Exception("Service.NO_PRODUCT_FOUND");
		}else {
			for(Product product: products) {
				ProductDTO productDTO = ProductDTO.valueOf(product);
				productDTOs.add(productDTO);
			}	
		}
		return productDTOs;
		
	}
	
	public ProductDTO getSpecificProductById(int prodId) throws Exception {
		logger.info("Product details : {}", prodId);
		ProductDTO productDTO = null;
		Optional<Product> optProduct = productRepo.findById(prodId);
		if (optProduct.isPresent()) {
			Product product = optProduct.get();
			productDTO = ProductDTO.valueOf(product);
		}else {
			throw new Exception("Service.NO_PRODUCT_FOUND");
		}
		return productDTO;

	}

	
	public List<ProductDTO> getSpecificProductByName(String productName) throws Exception{
		logger.info("Product details for Product Name : {}", productName);		
		List<Product> products = productRepo.findByProductName(productName);
		List<ProductDTO> productDTOs = new ArrayList<>();
		
		if(products.isEmpty()) {
			throw new Exception("Service.NO_PRODUCT_FOUND");
		}else {
			for(Product product: products) {
				ProductDTO productDTO = ProductDTO.valueOf(product);
				productDTOs.add(productDTO);
			}
		}
		return productDTOs;
	}
	
	

}
