package com.crowdchef.core.controller;


public class CoreControllerFactory {

    public CoreControllerFactory() {
    }

    public static CoreController getControllerInstance() {
        return new CoreControllerImpl();
    }

}
