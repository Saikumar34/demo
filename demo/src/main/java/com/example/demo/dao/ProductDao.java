package com.example.demo.dao;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Product;
import com.example.demo.repositoy.ProductRepository;
import com.example.demo.util.IdGenerator;

@Component
public class ProductDao {

	@Autowired
	private ProductRepository repo;

	private Logger log = LoggerFactory.getLogger(ProductDao.class);

	public void saveProduct(Product product) {
		try {
			product.setId(IdGenerator.generateId());
			repo.insert(product);
		} catch (DataAccessException ex) {
			log.info("Exception is {}", ex);
			saveProduct(product);
		}
	}
	
	public Optional<Product> fetchProduct(String id) {
		return repo.findById(id);
	}
}
