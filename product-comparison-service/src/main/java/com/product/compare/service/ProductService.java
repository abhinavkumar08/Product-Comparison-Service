package com.product.compare.service;

import com.product.compare.document.Product;
import com.product.compare.exception.ProductCompareServiceException;

import java.util.List;

public interface ProductService {

    public Product addProduct(Product mobile, String category) throws ProductCompareServiceException;

    public Product getProductDetails(String category, String name) throws ProductCompareServiceException;

    public List<Product> bulkAddProducts(List<Product> productList) throws ProductCompareServiceException;
}
