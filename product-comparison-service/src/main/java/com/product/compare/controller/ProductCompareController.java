package com.product.compare.controller;

import com.product.compare.document.Product;
import com.product.compare.exception.ProductCompareServiceException;
import com.product.compare.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductCompareController {

    private static final Log LOGGER = LogFactory.getLog(ProductCompareController.class);

    private ProductService productService;

    public ProductCompareController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProductToInventory(@RequestBody Product product) throws ProductCompareServiceException {

        String category = product.getCategory().toLowerCase();
        Product prod = null;
        try {
            prod = productService.addProduct(product, category);
        } catch (ProductCompareServiceException e) {
            LOGGER.error("Error occurred" + e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }

    @GetMapping("/search/{category}/{productName}")
    public Product getProductInfo(@PathVariable String category, @PathVariable String productName) throws ProductCompareServiceException {

        Product prod = null;
        try {
            LOGGER.info("Searching for the product based on "+ category + " and name "+productName);
            prod = productService.getProductDetails(category.toLowerCase(), productName);
        } catch (ProductCompareServiceException e) {
            LOGGER.error("Error occurred" + e.getMessage());
            throw e;
        }
        return prod;

    }
}
