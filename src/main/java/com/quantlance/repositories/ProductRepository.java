package com.quantlance.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.quantlance.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	
    Collection<Product> findAllByProductId(String id);

}
