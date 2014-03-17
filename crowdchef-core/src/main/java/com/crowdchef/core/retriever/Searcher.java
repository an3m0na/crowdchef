package com.crowdchef.core.retriever;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Searcher {

    public List<Long> search(final String aSearchQuery,
                             final String aField) {
        List<Long> mySearchResults = null;
        try {
            IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("indexes")));
            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
            BufferedReader in = null;
            if (aSearchQuery != null) {
                in = new BufferedReader(new InputStreamReader(new FileInputStream(aSearchQuery), "UTF-8"));
            } else {
                in = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
            }

            QueryParser parser = new QueryParser(Version.LUCENE_44,
                    aField,
                    analyzer);

            Query query = parser.parse(aSearchQuery);

            TopDocs mySearch = searcher.search(query, null, 100);

            mySearchResults = new ArrayList<Long>();

            for (ScoreDoc myDoc : mySearch.scoreDocs) {
                Document doc = searcher.doc(myDoc.doc);
                mySearchResults.add(Long.parseLong(doc.get("id")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mySearchResults;
    }
}
