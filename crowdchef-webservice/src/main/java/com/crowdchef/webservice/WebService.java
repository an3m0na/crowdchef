package com.crowdchef.webservice;

import spark.*;

import static spark.Spark.get;

public class WebService {
    public void start()
    {
        get(new Route("/")
        {
            @Override
            public Object handle(final Request request, final Response response)
            {
                return "CrowdChefServer is On!";
            }
        });

    }
}
