package com.crowdchef.webservice;

import com.crowdchef.*;
import spark.*;

import static spark.Spark.get;

public class WebService {
    private final Searcher theSearcher;
    private final Indexer theIndexer;

    public WebService(final Searcher aSearcher, final Indexer aIndexer)
    {
        theSearcher = aSearcher;
        theIndexer = aIndexer;
    }

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

        get(new Route("/search/:searchQuery/:field")
        {
            @Override
            public Object handle(final Request request, final Response response)
            {

                theSearcher.search(request.params("searchQuery"),
                                   request.params("field"));
                return "CrowdChefServer is On!";
            }
        });

    }
}
