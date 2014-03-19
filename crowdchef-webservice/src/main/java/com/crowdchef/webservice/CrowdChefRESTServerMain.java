package com.crowdchef.webservice;

import static spark.Spark.setPort;

public class CrowdChefRESTServerMain {
    public static void main(String[] args) {
        String port = System.getenv("PORT");
        setPort(port == null ? 9090 : Integer.parseInt(port));
        new WebService().init();

    }
}
