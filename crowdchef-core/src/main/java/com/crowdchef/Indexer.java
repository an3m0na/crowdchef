package com.crowdchef;

import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.util.*;

public class Indexer {

    public void index()
    {
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
    }
}
