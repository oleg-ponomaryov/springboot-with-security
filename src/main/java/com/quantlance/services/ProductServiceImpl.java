package com.quantlance.services;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.quantlance.domain.Product;
import com.quantlance.domain.User;
import com.quantlance.forms.ProductCreateForm;
import com.quantlance.forms.UserCreateForm;
import com.quantlance.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findOne(id);
    }

   @Override
    public Collection<Product> getProductByProductId(String id) {
        return productRepository.findAllByProductId(id);
    }
    
    @Override
    public Product create(ProductCreateForm form) {
        Product product = new Product();
        product.setDescription(form.getDescription());
        product.setImageUrl(form.getImageUrl());
        product.setPrice(form.getPrice());
        product.setProductId(form.getProductId());
        return productRepository.save(product);
    }
    
    @Override
    public Product update(ProductCreateForm form) {
    	Product product = getProductById(form.getId());
    	if (product == null) {
    		throw new NoSuchElementException(String.format("Product=%s not found", form.getId()));
    	}

        product.setDescription(form.getDescription());
        product.setImageUrl(form.getImageUrl());
        product.setPrice(form.getPrice());
        product.setProductId(form.getProductId());
        return productRepository.save(product);
    }
    
    @Override
    public void delete(Integer id) {
        productRepository.delete(id);
    }
}
