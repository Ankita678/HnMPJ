package com.infy.productMS.dto;

import com.infy.productMS.entity.SubscribedProduct;

public class SubscribedProductDTO {
	
	int subId;
	
	int buyerId;
	
	int prodId;
	
	int quantity;
	
	public SubscribedProductDTO() {
		super();
	}
	
	public SubscribedProductDTO(int subId, int buyerId, int prodId, int quantity) {
		super();
		this.subId = subId;
		this.buyerId = buyerId;
		this.prodId = prodId;
		this.quantity = quantity;
	}

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public SubscribedProduct createEntity() {
		SubscribedProduct subscribedProduct = new SubscribedProduct();
		
		subscribedProduct.setBuyerId(this.getBuyerId());
		subscribedProduct.setProdId(this.getProdId());
		subscribedProduct.setQuantity(this.getQuantity());
		subscribedProduct.setSubId(this.getSubId());
		
		return subscribedProduct;
	}
	
	public static SubscribedProductDTO valueOf(SubscribedProduct subscribedProduct) {
		SubscribedProductDTO subscribeProductDTO = new SubscribedProductDTO();
		subscribeProductDTO.setBuyerId(subscribedProduct.getBuyerId());
		subscribeProductDTO.setProdId(subscribedProduct.getProdId());
		subscribeProductDTO.setQuantity(subscribedProduct.getQuantity());
		subscribeProductDTO.setSubId(subscribedProduct.getSubId());
		return subscribeProductDTO;
	}

	@Override
	public String toString() {
		return "SubscribedProductDTO [subId=" + subId + ", buyerId=" + buyerId + ", prodId=" + prodId + ", quantity="
				+ quantity + "]";
	}
	
	

}
