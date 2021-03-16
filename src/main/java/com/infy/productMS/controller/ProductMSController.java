package com.infy.productMS.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.infy.productMS.dto.ProductDTO;
import com.infy.productMS.dto.SubscribedProductDTO;
import com.infy.productMS.service.ProductMSService;
import com.infy.productMS.service.SubscribedProductService;

@RestController
@CrossOrigin
public class ProductMSController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductMSService productService;
	
	@Autowired
	SubscribedProductService subscribedProductService;
	
	@Autowired
	Environment environment;
	
	@PostMapping(value = "/api/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO) throws Exception{
		logger.info("Add request for product {} ", productDTO);
		ResponseEntity<String> response = null;
		try {
			productService.addProduct(productDTO);
			String success_message = environment.getProperty("Controller.PRODUCT_ADDED_SUCCESSFULLY");
			response = new ResponseEntity<String>(success_message,HttpStatus.CREATED);
		}catch(Exception e){
			
			response = new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		return response;			
	}
	
	@DeleteMapping(value="/api/products/{prodId}")
	public ResponseEntity<String> removeProduct(@PathVariable int prodId) throws Exception{
		logger.info("Remove request for product {} ", prodId);
		ResponseEntity<String> response = null;
		try {
			productService.removeProduct(prodId);
			String message = environment.getProperty("Controller.PRODUCT_DELETED");
			response = new ResponseEntity<String>(message,HttpStatus.OK);
		}catch (Exception e) {
			//throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
			response = new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@DeleteMapping(value="/api/products/seller/{sellerId}")
	public ResponseEntity<String> removeAllProduct(@PathVariable int sellerId)throws Exception {
		logger.info("Remove request for product from seller {} ", sellerId);
		ResponseEntity<String> response = null;
		try {
			productService.deleteAllProducts(sellerId);
			String message = environment.getProperty("Controller.PRODUCTS_DELETED");
			response = new ResponseEntity<String>(message,HttpStatus.OK);
		}catch (Exception e) {
			//throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
			response = new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.NOT_FOUND);
		}
		return response;
	}

	
	@GetMapping(value="/api/products", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getAllProducts() throws Exception {
		logger.info("Fetching all products");
		ResponseEntity<List<ProductDTO>> response = null;
		try{
			List<ProductDTO> productsList = productService.getAllProducts();
			response = new ResponseEntity<List<ProductDTO>>(productsList,HttpStatus.OK);
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
			//response = new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
		}		
		return response;	
	}
	
	@GetMapping(value="/api/products/{prodId}", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getProductById(@PathVariable int prodId) throws Exception {
		logger.info("Fetching product by Id {}");
		ResponseEntity<ProductDTO> response = null;
		try {
			ProductDTO product = productService.getSpecificProductById(prodId);
			response = new ResponseEntity<ProductDTO>(product,HttpStatus.OK);
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		}	
		return response;
	}
	
	@GetMapping(value="/api/products/bycategory/{category}", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String category ) throws Exception {
		logger.info("Fetching all products for category {} ", category);
		ResponseEntity<List<ProductDTO>> response = null;
		try{
			List<ProductDTO> productsList = productService.getSpecificProductByCategory(category);
			response = new ResponseEntity<List<ProductDTO>>(productsList,HttpStatus.OK);
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		}	
		return response;
	}
	
	@GetMapping(value="/api/products/byname/{productName}", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable String productName ) throws Exception{
		logger.info("Fetching all products for category {} ", productName);
		ResponseEntity<List<ProductDTO>> response = null;
		try{
			List<ProductDTO> productsList = productService.getSpecificProductByName(productName);
			response = new ResponseEntity<List<ProductDTO>>(productsList,HttpStatus.OK);
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		}	
		return response;
	}
	
	@PutMapping(value="/api/products/updatestock",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateStock(@RequestBody ProductDTO productdto) throws Exception{
		logger.info("Updating stock for product {}", productdto.getProdId() );
		ResponseEntity<String> response = null;
		try {
			productService.updateStock(productdto.getProdId(), productdto.getStock());
			String success_message = environment.getProperty("Controller.STOCK_UPDATED_SUCCESSFULLY");
			response = new ResponseEntity<String>(success_message,HttpStatus.OK);
		}catch(Exception e){
			//throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
			response = new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		return response;		
		
	}
	
	
	@PostMapping(value = "/api/subscriptions/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProductToSubscription(@RequestBody SubscribedProductDTO subscribedProductDTO) throws Exception{
		logger.info("Add to subscription request for product {} ", subscribedProductDTO);
		ResponseEntity<String> response = null;
		try {
			subscribedProductService.addSubscribedProduct(subscribedProductDTO);
			String success_message = environment.getProperty("Controller.PRODUCT_ADDED_TO_SUBSCRIPTION_SUCCESSFULLY");
			response = new ResponseEntity<String>(success_message,HttpStatus.CREATED);
		}catch(Exception e){
			response = new ResponseEntity<String>(environment.getProperty(e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		return response;			
	}
	
	
	@GetMapping(value="/api/subscriptions/{buyerId}", produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SubscribedProductDTO>> getSubscribedProductByBuyerId(@PathVariable int buyerId) throws Exception {
		logger.info("Fetching product by Buyer Id {}", buyerId);
		ResponseEntity<List<SubscribedProductDTO>> response = null;
		try {
			List<SubscribedProductDTO> subscribedProducts = subscribedProductService.getSubscribedProducts(buyerId);
			response = new ResponseEntity<List<SubscribedProductDTO>>(subscribedProducts,HttpStatus.OK);
		}catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,environment.getProperty(e.getMessage()),e);
		}	
		return response;
	}
}
