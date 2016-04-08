package com.quantlance.controllers;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quantlance.domain.Product;
import com.quantlance.forms.ProductCreateForm;
import com.quantlance.services.ProductService;
import com.quantlance.validators.ProductCreateFormValidator;

@Controller
public class ProductController {

	private final ProductService productService;
    private final ProductCreateFormValidator productCreateFormValidator;
    
    @Autowired
    public ProductController(ProductService productService, ProductCreateFormValidator productCreateFormValidator) {
        this.productService = productService;
        this.productCreateFormValidator = productCreateFormValidator;
    }
    
    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(productCreateFormValidator);
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("products", productService.listAllProducts());
        return "products";
    }

    @RequestMapping("/product/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
    	Product product = productService.getProductById(id);
    	if (product != null) {
    		model.addAttribute("product", product);
    		return "productshow";
    	}
    	else {
    		throw new NoSuchElementException(String.format("Product=%s not found", id));
    	}
    }
    
    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
    	Product product = productService.getProductById(id);
    	if (product == null) {
    		throw new NoSuchElementException(String.format("Product=%s not found", id));
    	}
    	
    	ProductCreateForm form = new ProductCreateForm();
    	form.setAction("update");
    	form.setId(product.getId());
    	form.setDescription(product.getDescription());
    	form.setImageUrl(product.getImageUrl());
    	form.setPrice(product.getPrice());
    	form.setProductId(product.getProductId());
    	
        model.addAttribute("form", form);
        return "productform";
    }

    @RequestMapping(value = "/product/create", method = RequestMethod.GET)
    public String getProductCreatePage(Model model) {
        model.addAttribute("form", new ProductCreateForm());
        return "productform";
    }

    @RequestMapping(value = "/product/create", method = RequestMethod.POST)
    public String handleProductCreateForm(@Valid @ModelAttribute("form") ProductCreateForm form, BindingResult bindingResult) {

    	if (bindingResult.hasErrors()) {
            return "productform";
        }
        try {
            productService.create(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("product.create.error", e.getMessage());
            return "productform";
        }
        return "redirect:/products";
    }


    @RequestMapping(value = "/product/update", method = RequestMethod.POST)
    public String handleProductUpdateForm(@Valid @ModelAttribute("form") ProductCreateForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "productform";
        }
        try {
            productService.update(form);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("product.update.error", e.getMessage());
            return "productform";
        }
        return "redirect:/products";
    }
    
    @RequestMapping("product/delete/{id}")
    public String delete(@PathVariable Integer id, Model model){
    	Product product = productService.getProductById(id);
    	if (product == null) {
    		throw new NoSuchElementException(String.format("Product=%s not found", id));
    	}
    	productService.delete(id);
        return "redirect:/products";
    }
}
