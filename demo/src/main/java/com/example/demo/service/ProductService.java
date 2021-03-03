package com.example.demo.service;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dao.ProductDao;
import com.example.demo.entity.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;

	private Logger log = LoggerFactory.getLogger(ProductService.class);

	public Product fetchProduct(String id) {
		Optional<Product> product = productDao.fetchProduct(id);
		if (product.isEmpty()) {
			log.info("Product Not Exists");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not exists");
		}
		return product.get();
	}

	public String saveProduct(Product product) {
		log.info("Product {}", product);
		product.setLastModifiedDate(new Date());
		productDao.saveProduct(product);
		return product.getId();
	}

}
