package com.msa.mdm.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "matcher", type = "product-data")
public class ProductData {

	@Id
	private Long id;

	private String itemDescription;

	private String manufacturer;

	private String brandFamily;

	private String brand;
	
	private String colorExtension;
	
	private String size;
	
	private String flavor;

	private int quantity;

	private String url;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getBrandFamily() {
		return brandFamily;
	}

	public void setBrandFamily(String brandFamily) {
		this.brandFamily = brandFamily;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getColorExtension() {
		return colorExtension;
	}

	public void setColorExtension(String colorExtension) {
		this.colorExtension = colorExtension;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	@Override
	public String toString() {
		return "ProductData [id=" + id + ", itemDescription=" + itemDescription + ", manufacturer=" + manufacturer
				+ ", brandFamily=" + brandFamily + ", brand=" + brand + ", colorExtension=" + colorExtension + ", size="
				+ size + ", flavor=" + flavor + ", quantity=" + quantity + ", url=" + url + "]";
	}

	

}
