package com.quantlance.validators;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.quantlance.domain.Product;
import com.quantlance.forms.ProductCreateForm;
import com.quantlance.services.ProductService;

@Component
public class ProductCreateFormValidator implements Validator {

    @Autowired
    private ProductService productService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ProductCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	ProductCreateForm form = (ProductCreateForm) target;
    	validateProductId(errors, form);
    }

    private void validateProductId(Errors errors, ProductCreateForm form) {
	    	Collection<Product> products = productService.getProductByProductId(form.getProductId());
	    	if (products.size()>0) {
		    	if (form.getAction().equals("create")) {
		        	errors.reject("product.exists", "Product with this productId already exists");
		        	return;
		    	}
		    	else {
			    	for (Product p : products) {
			    		if (!p.getId().equals(form.getId())) {
				        	errors.reject("product.exists", "Product with this productId already exists");
				        	return;
			    		}
			    	}
		    	}
	    	}
    }
}
