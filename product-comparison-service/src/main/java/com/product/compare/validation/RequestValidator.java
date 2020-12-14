package com.product.compare.validation;

import com.product.compare.document.Product;
import com.product.compare.document.Vendor;

import java.util.List;

public final class RequestValidator {

    private RequestValidator(){
    }

    public static final boolean isValid(List<Product> products) throws RequestValidationException {

        if(products==null || products.isEmpty()) throw new RequestValidationException("Invalid request, Product list cannot be empty.");

        for(Product product : products){
            try {
                isValid(product);
            }catch (RequestValidationException ex){
                throw ex;
            }
        }
        return true;
    }

    public static final boolean isValid(Product product) throws RequestValidationException{

        String category = product.getCategory();
        String name = product.getName();
        String manufacturer = product.getManufacturer();
        String title = product.getTitle();
        double productRating = product.getProductRating();
        String description = product.getDescription();

        List<Vendor> vendors = product.getVendors();

        if(isNullOrEmpty(category)) throw new RequestValidationException("Category cannot be empty.");
        if(isNullOrEmpty(name)) throw new RequestValidationException("Name cannot be left blank.");
        if(isNullOrEmpty(manufacturer)) throw new RequestValidationException("Manufacturer cannot be left blank.");
        if(isNullOrEmpty(title)) throw new RequestValidationException("Title cannot be empty.");
        try {
            isInValidRating(productRating);
        }catch (RequestValidationException ex){
            throw ex;
        }
        if(isNullOrEmpty(description)) throw new RequestValidationException("Descriptoin cannot be left blank.");

        //validate vendors
        if(vendors.size()==0) throw new RequestValidationException("Vendor list is empty, Minimum one vendor is required. ");
        for(Vendor vendor : vendors){
           try {
               isValidVendor(vendor);
           }catch (RequestValidationException ex){
               throw ex;
           }
        }
        return true;
    }

    private static final boolean isInValidRating(double rating) throws RequestValidationException {
        if(rating<=0 || rating>5)throw new RequestValidationException("Invalid rating, Please rate between 1 to 5.");
        return true;
    }

    private static final boolean isNullOrEmpty(String obj){
        return obj==null || obj.isEmpty();
    }

    private static final boolean isInValidPrice(double price){
        return price<=0?true:false;
    }

    private static final boolean isValidVendor(Vendor vendor) throws RequestValidationException{
       String name = vendor.getName();
       double price = vendor.getPrice();
       String currency = vendor.getCurrency();
       double rating = vendor.getRating();
       String type = vendor.getType();

       if(isNullOrEmpty(name)) throw new RequestValidationException("Vendor name cannot be left blank.");
       if(isNullOrEmpty(currency)) throw new RequestValidationException("Currency cannot be left blank.");
       try {
           isInValidRating(rating);
       }catch (RequestValidationException ex){
           throw ex;
       }
       if(isNullOrEmpty(type)) throw new RequestValidationException("Vendor Type is not valid, cannot be left blank");
       if(isInValidPrice(price)) throw new RequestValidationException("Price is Invalid, cannot be less than zero.");

       return true;
    }
}
