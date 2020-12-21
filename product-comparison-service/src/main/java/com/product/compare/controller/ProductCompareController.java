package com.product.compare.controller;

import com.product.compare.document.Product;
import com.product.compare.document.ProductPayload;
import com.product.compare.exception.ProductCompareServiceException;
import com.product.compare.exception.PullDataToImportException;
import com.product.compare.service.ProductService;
import com.product.compare.validation.RequestValidationException;
import com.product.compare.validation.RequestValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductCompareController {

    private static final Log LOGGER = LogFactory.getLog(ProductCompareController.class);

    private ProductService productService;

    public ProductCompareController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProductToInventory(@RequestBody Product product) throws ProductCompareServiceException, RequestValidationException {

        Product prod = null;
        try {
            RequestValidator.isValid(product);
            LOGGER.info("Adding product with name "+product.getName() +" and category "+ product.getCategory() +" to the inverntory");
            String category = product.getCategory().toLowerCase();
            prod = productService.addProduct(product, category);

        } catch (RequestValidationException ex) {
            LOGGER.error("Invalid Request, Please check the payload and try again " + ex.getMessage());
            throw ex;
        } catch (ProductCompareServiceException e) {
            LOGGER.error("Error occurred while adding one product to the inventory " + e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }


    @GetMapping("/pull/data")
    public ResponseEntity pullDataFromQueuOrExternalSource() throws PullDataToImportException {

        throw new PullDataToImportException("Method Not Implemented yet, Work in Progress...");
    }


    @PostMapping("/bulk/add")
    public ResponseEntity<List<Product>> bulkImportToInventory(@RequestBody ProductPayload productPayload) throws ProductCompareServiceException, RequestValidationException {

        List<Product> productResponse = productPayload.getProductList();
        try {
            RequestValidator.isValid(productResponse);
            LOGGER.info("Adding list of products to the inverntory");
            productResponse = productService.bulkAddProducts(productResponse);
        } catch (RequestValidationException ex) {
            LOGGER.error("Invalid Request, Please check the payload and try again " + ex.getMessage());
            throw ex;
        } catch (ProductCompareServiceException e) {
            LOGGER.error("Error occurred while adding list of products to inverntory" + e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping("/search/{category}/{productName}")
    public Product getProductInfo(@PathVariable String category, @PathVariable String productName) throws ProductCompareServiceException {

        Product prod = null;
        try {
            LOGGER.info("Searching for the product based on " + category + " and name " + productName);
            prod = productService.getProductDetails(category.toLowerCase(), productName);
        } catch (ProductCompareServiceException e) {
            LOGGER.error("Error occurred" + e.getMessage());
            throw e;
        }
        return prod;

    }
}
