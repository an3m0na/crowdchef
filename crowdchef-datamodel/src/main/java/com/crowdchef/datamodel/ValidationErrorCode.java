package com.crowdchef.datamodel;

public enum ValidationErrorCode {
    GENERIC_ERROR("Generic error"),
    ID_NOT_EXIST("The object with the given ID does not exist"),
    TOO_MANY_RESULTS("Too many objects match your query");


    private final String label;

    private ValidationErrorCode(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}
