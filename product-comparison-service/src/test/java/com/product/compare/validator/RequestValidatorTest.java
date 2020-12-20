package com.product.compare.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.product.compare.document.Product;
import com.product.compare.document.Vendor;
import com.product.compare.validation.RequestValidationException;
import com.product.compare.validation.RequestValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class RequestValidatorTest {

    private List<Product> products;
    private Product product;

    @BeforeEach
    public void init() throws JsonProcessingException {
        products = getListOfProducts();
        product = getProduct();

    }

    private List<Product> getListOfProducts() {

        List<Product> products = new ArrayList<>();
        Product product = getProduct();
        products.add(product);
        return products;
    }

    private Product getProduct() {

        Product product = new Product();
        product.setId("1");
        product.setName("Iphone 12");
        product.setManufacturer("Apple");
        product.setCategory("Mobile");
        product.setTitle("New Apple Iphone 12.");
        product.setProductRating(4.3);
        product.setDescription("Display : 6.1inch Super Retina XDR display with True Tone, Capacity : 64GB, 128GB, 256GB, Camera and Video : Dual 12MP cameras with Portrait mode, Depth Control, Portrait Lighting, Smart HDR, and 4K Dolby Vision HDR video up to 60 fpsFront Camera12MP TrueDepth front camera with Portrait ");

        Vendor amazon = new Vendor("amazon", 1000.32, "$", 4, "ecommerce");
        Vendor flipkart = new Vendor("flipkart", 1000, "$", 4, "ecommerce");
        Vendor croma = new Vendor("croma", 980, "$", 4.3, "retail");

        List<Vendor> vendors = new ArrayList<>();
        vendors.add(amazon);
        vendors.add(flipkart);
        vendors.add(croma);

        product.setVendors(vendors);

        return product;
    }

    @Test
    public void productPayloadValidTest() throws RequestValidationException {
        Assertions.assertEquals(true, RequestValidator.isValid(product));
    }

    @Test
    public void productListValidTest() throws RequestValidationException {
        Assertions.assertEquals(true, RequestValidator.isValid(products));
    }

    @Test
    public void productPayloadInvalidRatingTest() {

        Assertions.assertThrows(RequestValidationException.class, ()->{
            product.setProductRating(10);
            RequestValidator.isValid(product);
        });
    }

    @Test
    public void productListIsEmpty() {

        Assertions.assertThrows(RequestValidationException.class, ()->{
            products = new ArrayList<>();
            RequestValidator.isValid(products);
        });
    }

}
