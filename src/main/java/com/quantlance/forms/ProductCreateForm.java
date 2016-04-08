package com.quantlance.forms;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ProductCreateForm {

	private Integer id;
    
    private Integer version;
	
    @NotEmpty
    private String action = "create";
	
    @NotEmpty
    private String productId;

    private String description;
    
    @NotEmpty
    private String imageUrl;

    @NotNull
    private BigDecimal price;

    public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
    
    public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	 @Override
	 public String toString() {
	 return "ProductCreateForm{" +
	 "productId='" + productId + '\'' +
	 ", ImageUrl=" + imageUrl +
	 '}';
	 }
	
}