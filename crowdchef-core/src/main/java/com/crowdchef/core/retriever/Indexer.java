package com.crowdchef.core.retriever;

import com.crowdchef.datamodel.CrowdChefDatabase;
import com.crowdchef.datamodel.entities.Ingredient;
import com.crowdchef.datamodel.entities.Recipe;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.spell.TermFreqIterator;
import org.apache.lucene.search.suggest.analyzing.AnalyzingSuggester;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.BytesRefIterator;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Indexer {

    public void index(List<Recipe> recipeList) throws IOException {

            List<Recipe> mySelectAllRecipes = recipeList;

            Directory dir = FSDirectory.open(new File("indexes"));

            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
            AnalyzingSuggester suggester = new AnalyzingSuggester(analyzer);

            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_44,
                    analyzer);

            iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

            IndexWriter writer = new IndexWriter(dir, iwc);
            indexDocs(writer, mySelectAllRecipes, suggester);
            writer.commit();
            writer.close();
    }

    private void indexDocs(final IndexWriter aWriter,
                           final List<Recipe> aRecipes,
                           AnalyzingSuggester suggester) throws IOException {
        for (Recipe myRecipe : aRecipes) {
            Document myDocument = new Document();

            myDocument.add(new StringField("id",
                    "" + myRecipe.getId(),
                    Field.Store.YES));
            myDocument.add(new TextField("name",
                    myRecipe.getName(),
                    Field.Store.YES));
            myDocument.add(new TextField("description",
                    myRecipe.getDescription(),
                    Field.Store.NO));
            myDocument.add(new TextField("directions",
                    myRecipe.getDirections(),
                    Field.Store.NO));
            myDocument.add(new TextField("tag",
                    myRecipe.getTags(),
                    Field.Store.NO));
            //myDocument.add(new NumericDocValuesField("rating", 1L));
            List<Ingredient> ingredients = myRecipe.getIngredients();
            if (ingredients != null) {
                for (Ingredient i : ingredients) {
                    myDocument.add(new StringField("ingredient",
                            i.getName(),
                            Field.Store.NO));
                }
            }

            aWriter.addDocument(myDocument);
        }
    }

    public static void main(String[] args) {
        try {
            new Indexer().index(new CrowdChefDatabase().retrieve("SelectAllRecipesWithIngredients",
                    Recipe.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
