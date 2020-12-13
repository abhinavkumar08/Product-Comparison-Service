package com.product.compare.repsository;

import com.product.compare.document.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {

    public Product addProduct(Product product, String category) throws Exception;

    public Product getProduct(String name, String category) throws  Exception;

}
