package com.product.compare.service;

import com.product.compare.document.Product;
import com.product.compare.exception.ProductCompareServiceException;
import com.product.compare.repsository.ProductRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Log LOGGER = LogFactory.getLog(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product, String category) throws ProductCompareServiceException {
        Product product1 = null;
        try{
            return productRepository.addProduct(product, category);
        }catch(Exception ex){
            LOGGER.error("Error occurred while trying to add product to databse "+ex.getMessage());
            throw new ProductCompareServiceException(ex);
        }
    }

    @Override
    public Product getProductDetails(String category, String name) throws ProductCompareServiceException {
        Product product1 = null;
        try{
            return productRepository.getProduct(name, category);
        }catch(Exception ex){
            LOGGER.error("Error occurred while trying to search product to databse "+ex.getMessage());
            throw new ProductCompareServiceException(ex);
        }
    }

}
