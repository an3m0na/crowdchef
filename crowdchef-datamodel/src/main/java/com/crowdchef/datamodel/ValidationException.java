package com.crowdchef.datamodel;

public class ValidationException extends RuntimeException {

    private ValidationErrorCode errorCode;
    private String errorMessage;

    public ValidationException(ValidationErrorCode errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ValidationException(ValidationErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getLabel();
    }

    public ValidationException(String errorMessage) {
        this.errorCode = ValidationErrorCode.GENERIC_ERROR;
        this.errorMessage = errorMessage;
    }

    public ValidationErrorCode getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ValidationException["+errorCode+"]: "+errorMessage;
    }
}
