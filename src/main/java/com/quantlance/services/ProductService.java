package com.quantlance.services;


import java.util.Collection;

import com.quantlance.domain.Product;
import com.quantlance.forms.ProductCreateForm;

public interface ProductService {
    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

	Collection<Product> getProductByProductId(String id);

	Product create(ProductCreateForm form);

	Product update(ProductCreateForm form);

	void delete(Integer id);
}
