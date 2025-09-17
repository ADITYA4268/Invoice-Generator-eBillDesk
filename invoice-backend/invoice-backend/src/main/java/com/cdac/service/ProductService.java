package com.cdac.service;

import java.util.List;

import com.cdac.dto.ProductReqDto;
import com.cdac.dto.ProductRespDto;

public interface ProductService {

	ProductRespDto createProductByUserId(ProductReqDto dto, Long userId);

	List<ProductRespDto> getProductByUser_Id(Long id);

	List<ProductRespDto> getAllProducts();

	ProductRespDto updateProduct(Long id, ProductReqDto dto);

	void deleteProduct(Long id);
}
