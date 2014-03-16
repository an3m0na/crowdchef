package com.crowdchef.webservice;

import static spark.Spark.setPort;

public class CrowdChefRESTServerMain {
    public static void main(String[] args) {
        setPort(9090);
        new WebService().init();

    }
}
