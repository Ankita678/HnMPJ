package com.infy.productMS.dto;

import com.infy.productMS.entity.Product;

public class ProductDTO {
	
	int prodId;	
	String brand;
	String category;	
	String desc;	
	String image;	
	double price;
	String productName;	
	int rating;	
	int sellerId;	
	int stock;	
	String subcategory;
	
	public ProductDTO() {
		super();
	}
		
		//parameterized constructor
	public ProductDTO(int prodId, String brand, String category, String desc, String image, double price,
				String productName, int rating, int sellerId, int stock, String subcategory) {
		super();
		this.prodId = prodId;
		this.brand = brand;
		this.category = category;
		this.desc = desc;
		this.image = image;
		this.price = price;
		this.productName = productName;
		this.rating = rating;
		this.sellerId = sellerId;
		this.stock = stock;
		this.subcategory = subcategory;
	}
	
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getSubcategory() {
		return subcategory;
	}
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	
	public static ProductDTO valueOf(Product product) {
		ProductDTO productDTO = new ProductDTO();
		
		productDTO.setBrand(product.getBrand());
		productDTO.setCategory(product.getCategory());
		productDTO.setDesc(product.getDesc());
		productDTO.setImage(product.getImage());
		productDTO.setPrice(product.getPrice());
		productDTO.setProdId(product.getProdId());
		productDTO.setProductName(product.getProductName());
		productDTO.setRating(product.getRating());
		productDTO.setSellerId(product.getSellerId());
		productDTO.setStock(product.getStock());
		productDTO.setSubcategory(product.getSubcategory());
		
		return productDTO;
	}
	
	public Product createEntity() {
		Product product = new Product();
		
		product.setBrand(this.getBrand());
		product.setCategory(this.getCategory());
		product.setDesc(this.getDesc());
		product.setImage(this.getImage());
		product.setPrice(this.getPrice());
		product.setProdId(this.getProdId());
		product.setProductName(this.getProductName());
		product.setRating(this.getRating());
		product.setSellerId(this.getSellerId());
		product.setStock(this.getStock());
		product.setSubcategory(this.getSubcategory());
		
		return product;
	}
	
	@Override
	public String toString() {
		return "ProductDTO [productId=" + prodId + ", brand=" + brand + ", category=" + category
				+ ", description=" + desc +  ", image=" + image +  ", price=" + price + ", productName=" + productName + 
				", rating=" + rating +  ", sellerId=" + sellerId +  ", stock=" + stock +  ", subcategory=" + subcategory + "]";
	}

	
}
