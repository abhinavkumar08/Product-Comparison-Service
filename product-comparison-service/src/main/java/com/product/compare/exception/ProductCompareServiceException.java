package com.product.compare.exception;

public class ProductCompareServiceException extends Exception {

    public ProductCompareServiceException() {
        super();
    }

    public ProductCompareServiceException(String message) {
        super(message);
    }

    public ProductCompareServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductCompareServiceException(Throwable cause) {
        super(cause);
    }
}
