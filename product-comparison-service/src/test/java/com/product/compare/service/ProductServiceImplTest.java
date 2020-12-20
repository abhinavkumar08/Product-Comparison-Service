package com.product.compare.service;

import com.product.compare.document.Product;
import com.product.compare.document.Vendor;
import com.product.compare.exception.ProductCompareServiceException;
import com.product.compare.repsository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class ProductServiceImplTest {

    private ProductServiceImpl productService;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    public void initialize() {
        productService = new ProductServiceImpl();
        productService.setProductRepository(productRepository);
    }

    @Test
    public void addProductTest() throws Exception {

        Product product = getProduct1();
        Mockito.when(productRepository.addProduct(product, "Mobile")).thenReturn(product);
        productService.addProduct(product, "Mobile");
        Assertions.assertEquals(product, product);

    }

    @Test
    public void addInvalidProductTest() {

        Product product = getProduct1();
        Assertions.assertThrows(ProductCompareServiceException.class, ()->{
            Mockito.when(productRepository.addProduct(product, "Mobile")).thenThrow(ProductCompareServiceException.class);
            productService.addProduct(product, "Mobile");
        });
    }

    @Test
    public void getProductDetailsTest() throws Exception {

        Product product = getProduct1();
        Mockito.when(productRepository.getProduct("Iphone 12", "Mobile")).thenReturn(product);
        Product pd = productService.getProductDetails("Mobile", "Iphone 12");
        Assertions.assertEquals(pd.getVendors().get(0).getName(), "croma");
    }

    @Test
    public void getInvalidDetailTest() {

        Assertions.assertThrows(ProductCompareServiceException.class, ()->{
            Mockito.when(productRepository.getProduct("Iphone 12", "mobile")).thenThrow(ProductCompareServiceException.class);
            productService.getProductDetails("Mobile", "Iphone 12");
        });
    }

    @Test
    public void bulkAddProductsTest() throws Exception {

        List<Product> productList = getProductList();
        Mockito.when(productRepository.addProduct(productList.get(0), "mobile")).thenReturn(productList.get(0));
        Mockito.when(productRepository.addProduct(productList.get(1), "mobile")).thenReturn(productList.get(1));
        List<Product> list = productService.bulkAddProducts(productList);
        Assertions.assertEquals(list, productList);
    }

    private List<Product> getProductList(){
        List<Product> productList =  new ArrayList<Product>();
        productList.add(getProduct1());
        productList.add(getProduct2());
        return productList;
    }

    private Product getProduct1() {

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

    private Product getProduct2() {

        Product product = new Product();
        product.setId("2");
        product.setName("Samsung S20");
        product.setManufacturer("Samsung");
        product.setCategory("Mobile");
        product.setTitle("New Samsung S20.");
        product.setProductRating(4.1);
        product.setDescription("Display : 6.1inch Super Retina XDR display with True Tone, Capacity : 64GB, 128GB, 256GB, Camera and Video : Dual 12MP cameras with Portrait mode, Depth Control, Portrait Lighting, Smart HDR, and 4K Dolby Vision HDR video up to 60 fpsFront Camera12MP TrueDepth front camera with Portrait.");

        Vendor amazon = new Vendor("amazon", 1200.32, "$", 4, "ecommerce");
        Vendor flipkart = new Vendor("flipkart", 1250, "$", 4, "ecommerce");
        Vendor croma = new Vendor("croma", 1199, "$", 4.3, "retail");

        List<Vendor> vendors = new ArrayList<>();
        vendors.add(amazon);
        vendors.add(flipkart);
        vendors.add(croma);

        product.setVendors(vendors);

        return product;
    }

}
