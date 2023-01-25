package com.adelean.opennlp.words;

import com.adelean.opennlp.tokenizer.Tokenizer;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.Span;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MostImportantTerm {

    private static final String NOUN_TAG = "NN";

    public List<String> findMostImportantTerm(String sentence) throws IOException {

        // tokenize the sentence
        String[] sentenceTokens = tokenizeSentence(sentence);

        // extract nouns from the sentence
        List<String> nouns = extractNouns(sentenceTokens);

        // extract entity names from the sentence
        List<String> entities = extractEntities(sentenceTokens);

        // remove entities and return remaining nouns
        return removeEntities(nouns, entities);

    }

    protected List<String> removeEntities(List<String> nouns, List<String> entities) {

        List<String> cleanedNouns = new ArrayList<>();

        for (String noun : nouns) {

            if (entities.contains(noun)) {
                continue;
            }

            cleanedNouns.add(noun);

        }

        return cleanedNouns;

    }

    protected List<String> extractEntities(String[] tokens) throws IOException {

        InputStream modelBin = this.getClass().getClassLoader().getResourceAsStream("ner-adelean-entity-model.bin");

        assert modelBin != null;
        TokenNameFinderModel model = new TokenNameFinderModel(modelBin);

        NameFinderME nameFinder = new NameFinderME(model);

        Span[] spans = nameFinder.find(tokens);

        List<String> entities = new ArrayList<>();

        for (Span entitySpan:spans) {
            entities.add(tokens[entitySpan.getStart()]);
        }

        return entities;

    }

    protected List<String> extractNouns(String[] tokens) throws IOException {

        // find tags
        List<String> tags = getTags(tokens);

        // return nouns
        List<String> nouns = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++) {

            String token = tokens[i];

            if (tags.get(i).equals(NOUN_TAG)) {
                nouns.add(token);
            }

        }

        return nouns;

    }

    protected List<String> getTags(String[] sentenceTokens) throws IOException {

        InputStream posBin = this.getClass().getClassLoader().getResourceAsStream("en-pos-maxent.bin");

        assert posBin != null;
        POSModel posModel = new POSModel(posBin);

        POSTaggerME tagsFinder = new POSTaggerME(posModel);
        return Arrays.asList(tagsFinder.tag(sentenceTokens));

    }

    protected String[] tokenizeSentence(String sentence) {

        Tokenizer tokenizer = new Tokenizer();

        return tokenizer.tokenize(sentence);

    }

}
