package com.product.compare.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class Product implements Serializable {

    @Id
    private String id;
    private String category;
    @Indexed(unique = true)
    private String name;
    private String manufacturer;
    private String title;
    private double productRating;
    private String description;

    private List<Vendor> vendors;

    public Product() {
    }

    public Product(String id, String category, String productName, String manufacturer, String title, double productRating, String description, List<Vendor> vendors) {
        this.id = id;
        this.category = category;
        this.name = productName;
        this.manufacturer = manufacturer;
        this.title = title;
        this.productRating = productRating;
        this.description = description;
        this.vendors = vendors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return name;
    }

    public void setProductName(String productName) {
        this.name = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getProductRating() {
        return productRating;
    }

    public void setProductRating(double productRating) {
        this.productRating = productRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
