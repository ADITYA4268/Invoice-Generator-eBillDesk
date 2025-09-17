package com.cdac.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.dao.ProductDao;
import com.cdac.dao.UserDao;
import com.cdac.dto.ProductReqDto;
import com.cdac.dto.ProductRespDto;
import com.cdac.entities.Product;
import com.cdac.entities.User;
import com.cdac.exceptions.ProductNotFoundException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private ModelMapper modelMapper;

	private UserDao userDao;

	@Override
	public ProductRespDto createProductByUserId(ProductReqDto dto, Long userId) {

		User user = userDao.findById(userId)
				.orElseThrow(() -> new RuntimeException("user not found with ID: " + userId));

		Product product = modelMapper.map(dto, Product.class);
		product.setUser(user);
		return modelMapper.map(productDao.save(product), ProductRespDto.class);
	}

	@Override
	public List<ProductRespDto> getProductByUser_Id(Long id) {
		return productDao.findByUser_Id(id).stream().map(p -> modelMapper.map(p, ProductRespDto.class))
				.collect(Collectors.toList());

	}

	@Override
	public List<ProductRespDto> getAllProducts() {
		return productDao.findAll().stream().map(product -> modelMapper.map(product, ProductRespDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProductRespDto updateProduct(Long id, ProductReqDto dto) {
		Product product = productDao.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found"));
		modelMapper.map(dto, product);
		Product updated = productDao.save(product);
		return modelMapper.map(updated, ProductRespDto.class);
	}

	@Override
	public void deleteProduct(Long id) {
		if (!productDao.existsById(id)) {
			throw new ProductNotFoundException("Product not found");
		}
		productDao.deleteById(id);

	}

}
