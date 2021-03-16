package com.infy.productMS;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.productMS.dto.ProductDTO;
import com.infy.productMS.entity.Product;
import com.infy.productMS.repository.ProductMSRepository;
import com.infy.productMS.service.ProductMSService;
import com.infy.productMS.validator.Validator;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductMSApplicationTests {
	@Mock
	ProductMSRepository productRepository;
	
	@Mock
	Validator validator;
	
	@InjectMocks
	ProductMSService productMSService=new ProductMSService();
	
	@Test
	public void getAllProductsValid() throws Exception {
		Product product=new Product(1,"Alisha","Clothing","Key Features of Alisha Solid Womens Cycling Shorts Cotton Lycra Navy, Red, Navy,Specifications of Alisha Solid Womens Cycling Shorts Shorts","http://img5a.flixcart.com/image/short/u/4/a/altht-3p-21-alisha-38-original-imaeh2d5vm5zbtgg.jpeg",500.0,"Alisha Solid Womens Cycling Shorts",0,7,460,"Womens Clothing");
		Product product2=new Product(2,"FabHomeDecor","Furniture","FabHomeDecor Fabric Double Sofa Bed (Finish Color - Leatherette Black Mechanism Type - Pull Out)","http://img6a.flixcart.com/image/sofa-bed/j/f/y/fhd112-double-foam-fabhomedecor-leatherette-black-leatherette-1100x1100-imaeh3gemjjcg9ta.jpeg",32157.00,"FabHomeDecor Fabric Double Sofa Bed",0,4,490,"Living Room Furniture");
				
		List<Product> products=new ArrayList<>();
		products.add(product2);
		products.add(product);
		
		Mockito.when(productRepository.findAll()).thenReturn(products);
		List<ProductDTO> actual=productMSService.getAllProducts();
		Assertions.assertEquals(products.isEmpty(), actual.isEmpty());
		
	}
	
	@Test
	public void getProductById() throws Exception{
		int id=4;
		productMSService.getSpecificProductById(id);
		verify(productRepository, times(1)).findById(id);
		
	}
	
	@Test
	public void getProductByIdInvalid() throws Exception{
		int id=4;
		Mockito.when(productRepository.findById(id)).thenReturn(null);
		productMSService.getSpecificProductById(id);
		verify(productRepository, times(1)).findById(id);
		
	}
	
	@Test
	public void getProductByCategoryValid() throws Exception {
		Product product=new Product(1,"Alisha","Clothing","Key Features of Alisha Solid Womens Cycling Shorts Cotton Lycra Navy, Red, Navy,Specifications of Alisha Solid Womens Cycling Shorts Shorts","http://img5a.flixcart.com/image/short/u/4/a/altht-3p-21-alisha-38-original-imaeh2d5vm5zbtgg.jpeg",500.0,"Alisha Solid Womens Cycling Shorts",0,7,460,"Womens Clothing");
		Product product2=new Product(2,"FabHomeDecor","Furniture","FabHomeDecor Fabric Double Sofa Bed (Finish Color - Leatherette Black Mechanism Type - Pull Out)","http://img6a.flixcart.com/image/sofa-bed/j/f/y/fhd112-double-foam-fabhomedecor-leatherette-black-leatherette-1100x1100-imaeh3gemjjcg9ta.jpeg",32157.00,"FabHomeDecor Fabric Double Sofa Bed",0,4,490,"Living Room Furniture");
		
		List<Product> products=new ArrayList<>();
		products.add(product2);
		products.add(product);
		
		Mockito.when(productRepository.findByCategory("Clothing")).thenReturn(products);
		List<ProductDTO> actual=productMSService.getSpecificProductByCategory("Clothing");
		Assertions.assertEquals(products.isEmpty(), actual.isEmpty());
		
	}
	
	@Test
	public void getProductByNameValid() throws Exception {
		Product product=new Product(1,"Alisha","Clothing","Key Features of Alisha Solid Womens Cycling Shorts Cotton Lycra Navy, Red, Navy,Specifications of Alisha Solid Womens Cycling Shorts Shorts","http://img5a.flixcart.com/image/short/u/4/a/altht-3p-21-alisha-38-original-imaeh2d5vm5zbtgg.jpeg",500.0,"Alisha Solid Womens Cycling Shorts",0,7,460,"Womens Clothing");
		Product product2=new Product(2,"FabHomeDecor","Furniture","FabHomeDecor Fabric Double Sofa Bed (Finish Color - Leatherette Black Mechanism Type - Pull Out)","http://img6a.flixcart.com/image/sofa-bed/j/f/y/fhd112-double-foam-fabhomedecor-leatherette-black-leatherette-1100x1100-imaeh3gemjjcg9ta.jpeg",32157.00,"FabHomeDecor Fabric Double Sofa Bed",0,4,490,"Living Room Furniture");
		
		List<Product> products=new ArrayList<>();
		products.add(product2);
		products.add(product);
		
		Mockito.when(productRepository.findByProductName("shorts")).thenReturn(products);
		List<ProductDTO> actual=productMSService.getSpecificProductByName("shorts");
		Assertions.assertEquals(products.isEmpty(), actual.isEmpty());
		
	}

}
