package com.infy.productMS.validator;

import com.infy.productMS.dto.ProductDTO;

public class Validator {
	
	public static void validateAdd(ProductDTO productDTO) throws Exception {
		
		if(!validateName(productDTO.getProductName())) {
			throw new Exception("Validator.INVALID_NAME");
		}		
		if(productDTO.getDesc().length() > 500) {
			throw new Exception("Validator.INVALID_DESCRIPTION");
		}
		if(productDTO.getPrice() < 200) {
			throw new Exception("Validator.INVALID_PRICE");
		}
		if(!validateStock(productDTO.getStock())) {
			throw new Exception("Validator.INVALID_STOCK");
		}
		if(!validateImage(productDTO.getImage())){
			throw new Exception("Validator.INVALID_IMAGE");
		}
		
	}
	
	public static boolean validateName(String productName) {
		int len = productName.length();
		String regex = "[a-zA-Z]+(\\s[a-zA-Z])*[a-zA-Z]+";
		if(productName.matches(regex) && len <= 100) {
			return true;
		}
		return false;
	}
	
	public static boolean validateImage(String image) {
		if(image.contains(".jpeg") || image.contains(".png")) {
			return true;
		}
		return false;
	}
	
	public static boolean validateStock(int stock) {
		if(stock >= 10) {
			return true;
		}
		return false;
	}


}
