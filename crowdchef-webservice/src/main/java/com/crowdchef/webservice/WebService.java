package com.crowdchef.webservice;

import org.eclipse.jetty.http.HttpStatus;
import spark.*;

import static spark.Spark.get;
import static spark.Spark.post;

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

        get(new Route("/ane/puiumeu")
        {
            @Override
            public Object handle(final Request request, final Response response)
            {
                request.params("chilotei");
                return "Pasarica    "+request.queryParams("chilotei");
            }
        });


        post(new Route("/ane/post") {
            @Override
            public Object handle(final Request request, final Response response) {
                request.body();
                System.out.println(request.body());
                request.params("chilotei");
                return HttpStatus.ACCEPTED_202;
            }
        });

    }
}
