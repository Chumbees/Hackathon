package com.qvc.product.product;

public class Product {

	private String productNumber;
	private String prodSalesDivCode;
	private String productType;

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProdSalesDivCode() {
		return prodSalesDivCode;
	}

	public void setProdSalesDivCode(String prodSalesDivCode) {
		this.prodSalesDivCode = prodSalesDivCode;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String toString() {
		char quote = '"';
		return "{" + quote + "create" + quote + ":{" + quote + "_index" + quote + ":" + quote + "product_search_service"
				+ quote + "," + quote + "_type" + quote + ":" + quote + "product" + quote + "," + quote + "_id" + quote
				+ ":" + quote + productNumber + quote + "}}" + "\n" + "{" + quote + "product_number" + quote + ":"
				+ quote + productNumber + quote + ", " + quote + "prod_sales_div_code" + quote + ":" + quote
				+ prodSalesDivCode + quote + ", " + quote + "product_type" + quote + ":" + quote + productType + quote
				+ "}" + "\n";
	}

}
