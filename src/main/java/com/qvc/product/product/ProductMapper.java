package com.qvc.product.product;

import java.util.Collection;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
	public Collection<Product> findProduct();
}