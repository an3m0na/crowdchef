package com.crowdchef;

import com.crowdchef.datamodel.*;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.standard.*;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.store.*;
import org.apache.lucene.util.*;

import java.io.*;
import java.util.*;

public class Indexer {

    public void index()
    {
        try
        {

            List<Recipe> mySelectAllRecipes = new CrowdChefDatabase().retrieve("SelectAllRecipesWithIngredients",
                                                                               Recipe.class);


            Directory dir = FSDirectory.open(new File("indexes"));

            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_44,
                                                          analyzer);

            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            IndexWriter writer = new IndexWriter(dir, iwc);
            indexDocs(writer, mySelectAllRecipes);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void indexDocs(final IndexWriter aWriter,
                           final List<Recipe> aRecipes) throws IOException
    {
        for(Recipe myRecipe : aRecipes)
        {
            Document myDocument = new Document();

            myDocument.add(new StringField("id",
                                           myRecipe.getRecipeId(),
                                           Field.Store.YES));
            myDocument.add(new TextField("title",
                                         myRecipe.getRecipeTitle(),
                                         Field.Store.YES));
            myDocument.add(new TextField("description",
                                         myRecipe.getRecipeDescription(),
                                         Field.Store.NO));
            myDocument.add(new StringField("ingredient",
                                           "//recipe-title",
                                           Field.Store.NO));

            aWriter.addDocument(myDocument);
        }
    }

    public static void main(String[] args)
    {
        new Indexer().index();
    }
}
