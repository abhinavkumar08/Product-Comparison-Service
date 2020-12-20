package com.product.compare.exception;

public class PullDataToImportException extends Exception {

    public PullDataToImportException() {
        super();
    }

    public PullDataToImportException(String s) { super(s); }

    public PullDataToImportException(String message, Throwable cause) {
        super(message, cause);
    }

    public PullDataToImportException(Throwable cause) {
        super(cause);
    }
}
