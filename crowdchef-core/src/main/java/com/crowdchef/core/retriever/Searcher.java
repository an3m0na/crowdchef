package com.crowdchef.core.retriever;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;
import org.apache.lucene.util.*;
import org.json.*;

import java.io.*;

public class Searcher {

    public void search(final String aSearchQuery,
                       final String aField)
    {
        try
        {
            IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("indexes")));
            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
            BufferedReader in = null;
            if (aSearchQuery != null)
            {
                in = new BufferedReader(new InputStreamReader(new FileInputStream(aSearchQuery), "UTF-8"));
            }
            else
            {
                in = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
            }

            QueryParser parser = new QueryParser(Version.LUCENE_44,
                                                 aField,
                                                 analyzer);

            Query query = parser.parse(aSearchQuery);

            TopDocs mySearch = searcher.search(query, null, 100);

            JSONArray mySearchResults = new JSONArray();

            for (ScoreDoc myDoc : mySearch.scoreDocs)
            {
                JSONObject myDocument = new JSONObject();

                Document doc = searcher.doc(myDoc.doc);
                myDocument.put("title", doc.get("title"));

                mySearchResults.put(myDocument);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
