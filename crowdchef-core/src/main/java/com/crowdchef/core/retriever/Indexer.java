package com.crowdchef.core.retriever;

import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.entities.Recipe;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Indexer {

    public void index(List<Recipe> recipeList) {
        try {

            List<Recipe> mySelectAllRecipes = recipeList;


            Directory dir = FSDirectory.open(new File("indexes"));

            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_44,
                    analyzer);

            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            IndexWriter writer = new IndexWriter(dir, iwc);
            indexDocs(writer, mySelectAllRecipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void indexDocs(final IndexWriter aWriter,
                           final List<Recipe> aRecipes) throws IOException {
        for (Recipe myRecipe : aRecipes) {
            Document myDocument = new Document();

            myDocument.add(new StringField("id",
                    "" + myRecipe.getId(),
                    Field.Store.YES));
            myDocument.add(new TextField("title",
                    myRecipe.getName(),
                    Field.Store.YES));
            myDocument.add(new TextField("description",
                    myRecipe.getDescription(),
                    Field.Store.NO));
            myDocument.add(new StringField("ingredient",
                    "//recipe-title",
                    Field.Store.NO));

            aWriter.addDocument(myDocument);
        }
    }

    public static void main(String[] args) {
        new Indexer().index(new CrowdChefDatabase().retrieve("SelectAllRecipesWithIngredients",
                Recipe.class));
    }
}
