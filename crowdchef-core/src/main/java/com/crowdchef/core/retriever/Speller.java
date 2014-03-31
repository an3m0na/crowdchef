package com.crowdchef.core.retriever;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.analyzing.AnalyzingSuggester;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Speller {
    private HashMap<String, AnalyzingSuggester> suggesters;
    private SpellChecker checker;
    private int suggestCount = 10;

    public Speller() throws IOException {
        initSpeller();
    }

    public void initSpeller() throws IOException {
        DirectoryReader reader = DirectoryReader.open(FSDirectory.open(new File("indexes")));

        checker = new SpellChecker(FSDirectory.open(new File("spellchecker")));
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
        IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_44,
                analyzer);
        String[] fields = new String[]{"name", "description", "directions", "tag"};
        suggesters = new HashMap<String, AnalyzingSuggester>();
        for (String field : fields) {
            AnalyzingSuggester suggester = new AnalyzingSuggester(
                    new StandardAnalyzer(Version.LUCENE_44));
            LuceneDictionary dictionary = new LuceneDictionary(reader, field);
            checker.clearIndex();
            checker.indexDictionary(dictionary, iwc, true);
            suggester.build(dictionary);
            suggesters.put(field, suggester);
        }
    }

    public List<String> autoSuggest(String term, String field) {
        List<Lookup.LookupResult> results;
        AnalyzingSuggester suggester = suggesters.get(field);
        if (suggester == null)
            throw new RetrieverException(RetrieverErrorCode.UNKNOWN_FIELD);
        results = suggester.lookup(term, false, suggestCount);

        if (results == null)
            return new ArrayList<String>();

        ArrayList<String> suggestions = new ArrayList(results.size());
        for (Lookup.LookupResult lookupResult : results) {
            suggestions.add(lookupResult.key.toString());
        }
        return suggestions;
    }

    public List<String> checkSpelling(String term, String field) throws IOException {
        String[] suggestions = checker.suggestSimilar(term, suggestCount);

        if (suggestions == null)
            return new ArrayList<String>();
        return Arrays.asList(suggestions);
    }
}
