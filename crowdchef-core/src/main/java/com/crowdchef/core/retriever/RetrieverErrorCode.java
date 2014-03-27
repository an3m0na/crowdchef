package com.crowdchef.core.retriever;

public enum RetrieverErrorCode {
    GENERIC_ERROR("Generic error"),
    CANNOT_INIT_RETRIEVER("Retriever could not be initialized"),
    UNKNOWN_FIELD("The specified index field was not recognized");


    private final String label;

    private RetrieverErrorCode(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }
}
