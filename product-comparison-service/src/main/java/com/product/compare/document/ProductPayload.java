package com.product.compare.document;

import java.util.List;

public class ProductPayload {

    List<Product> productList;

    public ProductPayload() {
    }

    public ProductPayload(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {

        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
