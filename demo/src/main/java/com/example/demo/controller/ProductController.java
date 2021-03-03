package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> fetchProduct(@PathVariable String id) {
		Product product = productService.fetchProduct(id);
		ResponseEntity<Product> resp = new ResponseEntity<Product>(product, HttpStatus.OK);
		return resp;
	}

	@PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		String id = productService.saveProduct(product);
		ResponseEntity<String> resp = new ResponseEntity<String>(id, HttpStatus.OK);
		return resp;
	}

}
