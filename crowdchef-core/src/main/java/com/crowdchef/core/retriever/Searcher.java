package com.crowdchef.core.retriever;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queries.CustomScoreQuery;
import org.apache.lucene.queries.function.FunctionQuery;
import org.apache.lucene.queries.function.valuesource.FloatFieldSource;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Searcher {

    IndexSearcher searcher;
    Analyzer analyzer;

    public Searcher() throws IOException {
        initSearcher();
    }

    public void initSearcher() throws IOException {
        IndexReader reader = DirectoryReader.open(FSDirectory.open(new File("indexes")));
        searcher = new IndexSearcher(reader);
        analyzer = new StandardAnalyzer(Version.LUCENE_44);
    }

    public Map<Long, String> search(final String aSearchQuery,
                                    final String aField) throws IOException, ParseException {
        LinkedHashMap<Long, String> mySearchResults;

        QueryParser parser = new QueryParser(Version.LUCENE_44,
                aField,
                analyzer);

        Query query = parser.parse(aSearchQuery);
        FunctionQuery boostQuery = new FunctionQuery(new FloatFieldSource("rating"));
        Query boostedQuery = new CustomScoreQuery(query, boostQuery);

        TopDocs mySearch = searcher.search(boostedQuery, null, 100);

        mySearchResults = new LinkedHashMap<Long, String>();

        for (ScoreDoc myDoc : mySearch.scoreDocs) {
            Document doc = searcher.doc(myDoc.doc);
            System.out.println(doc.get("id") + " " + myDoc.score);
            mySearchResults.put(Long.valueOf(doc.get("id")), doc.get("name"));
        }
        return mySearchResults;
    }

    private BooleanClause.Occur transformOccurrance(String occurString) {
        BooleanClause.Occur occur = BooleanClause.Occur.SHOULD;
        if ("not".equals(occurString))
            occur = BooleanClause.Occur.MUST_NOT;
        else if ("must".equals(occurString))
            occur = BooleanClause.Occur.MUST;
        return occur;
    }

    public BooleanClause getBooleanClause(String aSearchQuery, String aField, String occurString) throws ParseException {
        QueryParser parser = new QueryParser(Version.LUCENE_44,
                aField,
                analyzer);
        Query query = parser.parse(aSearchQuery);
        return new BooleanClause(query, transformOccurrance(occurString));
    }

    public BooleanClause getBooleanClause(int min, int max, String aField, String occurString) throws ParseException {
        Query query = NumericRangeQuery.newIntRange(aField, min, max, true, true);
        return new BooleanClause(query, transformOccurrance(occurString));
    }

    public Map<Long, String> complexSearch(List<BooleanClause> clauseList) throws IOException, ParseException {
        LinkedHashMap<Long, String> mySearchResults = new LinkedHashMap<Long, String>();
        BooleanQuery query = new BooleanQuery();
        for (BooleanClause clause : clauseList) {
            query.add(clause);
        }
        TopDocs mySearch = searcher.search(query, null, 100);
        for (ScoreDoc myDoc : mySearch.scoreDocs) {
            Document doc = searcher.doc(myDoc.doc);
            System.out.println(doc.get("id") + " " + myDoc.score);
            mySearchResults.put(Long.valueOf(doc.get("id")), doc.get("name"));
        }
        return mySearchResults;
    }
}
