package com.crowdchef.core.retriever;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.analyzing.AnalyzingSuggester;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Suggester {
    private AnalyzingSuggester nameSuggester;
    private AnalyzingSuggester directionsSuggester;
    private AnalyzingSuggester descriptionSuggester;
    private AnalyzingSuggester tagSuggester;

    public Suggester() throws IOException {
        initSuggester();
    }

    public void initSuggester() throws IOException {
        DirectoryReader reader = DirectoryReader.open(FSDirectory.open(new File("indexes")));

        nameSuggester = new AnalyzingSuggester(
                new StandardAnalyzer(Version.LUCENE_44));
        nameSuggester.build(new LuceneDictionary(reader, "name"));
        directionsSuggester = new AnalyzingSuggester(
                new StandardAnalyzer(Version.LUCENE_44));
        directionsSuggester.build(new LuceneDictionary(reader, "directions"));
        descriptionSuggester = new AnalyzingSuggester(
                new StandardAnalyzer(Version.LUCENE_44));
        descriptionSuggester.build(new LuceneDictionary(reader, "description"));
        tagSuggester = new AnalyzingSuggester(
                new StandardAnalyzer(Version.LUCENE_44));
        tagSuggester.build(new LuceneDictionary(reader, "tag"));

    }

    public ArrayList<String> autoSuggest(String query, String field) {
        List<Lookup.LookupResult> results;
        if ("name".equals(field))
            results = nameSuggester.lookup(query, false, 100);
        else if ("directions".equals(field))
            results = directionsSuggester.lookup(query, false, 100);
        else if ("description".equals(field))
            results = descriptionSuggester.lookup(query, false, 100);
        else
            results = tagSuggester.lookup(query, false, 100);

        ArrayList<String> suggestions = new ArrayList(results.size());
        for (Lookup.LookupResult lookupResult : results) {
            suggestions.add(lookupResult.key.toString());
        }
        return suggestions;
    }
}
