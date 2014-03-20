package com.crowdchef.datamodel;

public class ValidationException extends RuntimeException {

    private ValidationErrorCode errorCode;
    private String message;

    public ValidationException(ValidationErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ValidationException(ValidationErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getLabel();
    }

    public ValidationException(String message) {
        this.errorCode = ValidationErrorCode.GENERIC_ERROR;
        this.message = message;
    }

    public ValidationErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ValidationException["+errorCode+"]: "+ message;
    }
}
