package com.product.compare.service;

import com.product.compare.document.Product;
import com.product.compare.document.Vendor;
import com.product.compare.exception.ProductCompareServiceException;
import com.product.compare.repsository.ProductRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            product1 =  productRepository.getProduct(name, category);
            List<Vendor> vendorList = product1.getVendors();
            Collections.sort(vendorList, (vendor1, vendor2) ->{
                double vendor1Price = vendor1.getPrice();
                double vendor2Price = vendor2.getPrice();
                double vendor1Rating = vendor1.getRating();
                double vendor2Rating = vendor2.getRating();
               return vendor1Price==vendor2Price?Double.compare(vendor2Rating, vendor1Rating):Double.compare(vendor1Price, vendor2Price);
            });
            return product1;
        }catch(Exception ex){
            LOGGER.error("Error occurred while trying to search product to databse "+ex.getMessage());
            throw new ProductCompareServiceException(ex);
        }
    }

    @Override
    public List<Product> bulkAddProducts(List<Product> productList) throws ProductCompareServiceException {
        List<Product> productResponse = new ArrayList<Product>();
        for(Product product : productList){
            String category = product.getCategory().toLowerCase();
            Product prod = null;
            try {
                prod = productRepository.addProduct(product, category);
                productResponse.add(prod);
            } catch (Exception e) {
                LOGGER.error("Error occurred" + e.getMessage());
                throw new ProductCompareServiceException(e);
            }
        }
        return productResponse;
    }

}
