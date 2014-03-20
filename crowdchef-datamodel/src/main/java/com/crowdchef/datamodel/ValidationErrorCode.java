package com.crowdchef.datamodel;

public enum ValidationErrorCode {
    GENERIC_ERROR("Generic error"),
    ID_NOT_EXIST("The object with the given ID does not exist"),
    TOO_MANY_RESULTS("Too many objects match your query"),
    USER_NOT_PASSED("The user ID was not passed in the request"),
    USER_INCORRECT("User ID or credentials incorrect");

    private final String label;

    private ValidationErrorCode(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}
