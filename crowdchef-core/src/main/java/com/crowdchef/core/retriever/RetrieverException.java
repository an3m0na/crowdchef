package com.crowdchef.core.retriever;


public class RetrieverException extends RuntimeException {

    private RetrieverErrorCode errorCode;
    private String message;

    public RetrieverException(RetrieverErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public RetrieverException(RetrieverErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getLabel();
    }

    public RetrieverException(String message) {
        this.errorCode = RetrieverErrorCode.GENERIC_ERROR;
        this.message = message;
    }

    public RetrieverErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "RetrieverException[" + errorCode + "]: " + message;
    }
}
