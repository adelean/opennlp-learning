package com.adelean.opennlp.words;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MostImportantTermTest {

    private static final String SIMPLE_SENTENCE = "I want to buy an iPhone case";
    private static final String SIMPLE_PHRASE_WITH_PARTICLE = "case for iPhone";
    private static final String SIMPLE_PHRASE = "iPhone case";
    private static final String COMPLEX_SENTENCE = "I want to buy a small orange Apple iPhone case";
    private static final String COMPLEX_SENTENCE_YELLOW = "I want to buy a small yellow Apple iPhone case";
    private static final String CASE_WORD = "case";
    private static final String IPHONE_WORD = "iPhone";
    private static final String ORANGE_WORD = "orange";

    @Test
    void find_most_important_word_from_simple_sentence() {

        findMostImportantWordFrom(SIMPLE_SENTENCE, CASE_WORD);

    }

    @Test
    void ambigous_noun_complex_sentence() {

        MostImportantTerm mostImportantTerm = new MostImportantTerm();

        List<String> words = null;
        try {
            words = mostImportantTerm.findMostImportantTerm(COMPLEX_SENTENCE);
        } catch (IOException e) {
            fail("Error while looking at principal word with organization : " + e.getMessage());
        }
        assertNotNull(words);
        assertEquals(2, words.size());
        assertEquals(ORANGE_WORD, words.get(0));
        assertEquals(CASE_WORD, words.get(1));

    }

    @Test
    void find_most_important_word_from_non_ambiguous_complex_sentence() {

        findMostImportantWordFrom(COMPLEX_SENTENCE_YELLOW, CASE_WORD);

    }

    @Test
    void find_most_important_word_from_simple_phrase() {

        findMostImportantWordFrom(SIMPLE_PHRASE, CASE_WORD);

    }

    @Test
    void find_most_important_word_from_simple_phrase_with_particle() {

        findMostImportantWordFrom(SIMPLE_PHRASE_WITH_PARTICLE, CASE_WORD);

    }

    private void findMostImportantWordFrom(String input, String expected) {

        MostImportantTerm mostImportantTerm = new MostImportantTerm();

        List<String> words = null;
        try {
            words = mostImportantTerm.findMostImportantTerm(input);
        } catch (IOException e) {
            fail("Error while looking at principal word with organization : " + e.getMessage());
        }
        assertNotNull(words);
        assertEquals(1, words.size());
        assertEquals(expected, words.get(0));

    }

    @Test
    void removeEntities() {

        MostImportantTerm mostImportantTerm = new MostImportantTerm();
        String[] tokens = mostImportantTerm.tokenizeSentence(SIMPLE_SENTENCE);
        List<String> entities = null;
        List<String> nouns = null;
        List<String> cleaned = null;
        try {
            entities = mostImportantTerm.extractEntities(tokens);
            nouns = mostImportantTerm.extractNouns(tokens);
        } catch (IOException e) {
            fail("Error in entities extraction : " + e.getMessage());
        }
        cleaned = mostImportantTerm.removeEntities(nouns, entities);

        assertNotNull(cleaned);
        assertEquals(1, cleaned.size());
        assertEquals(CASE_WORD, cleaned.get(0));

    }

    @Test
    void extractEntities() {

        MostImportantTerm mostImportantTerm = new MostImportantTerm();
        String[] tokens = mostImportantTerm.tokenizeSentence(SIMPLE_SENTENCE);
        List<String> entities = null;
        try {
            entities = mostImportantTerm.extractEntities(tokens);
        } catch (IOException e) {
            fail("Error in entities extraction : " + e.getMessage());
        }

        assertNotNull(entities);
        assertEquals(1, entities.size());
        assertEquals(IPHONE_WORD, entities.get(0));

    }

    @Test
    void extractNouns() {

        MostImportantTerm mostImportantTerm = new MostImportantTerm();
        String[] tokens = mostImportantTerm.tokenizeSentence(SIMPLE_SENTENCE);
        List<String> nouns = null;
        try {
            nouns = mostImportantTerm.extractNouns(tokens);
        } catch (IOException e) {
            fail("Error in nouns extraction : " + e.getMessage());
        }

        assertNotNull(nouns);
        assertEquals(2, nouns.size());

    }

    @Test
    void getTags() {

        MostImportantTerm mostImportantTerm = new MostImportantTerm();
        String[] tokens = mostImportantTerm.tokenizeSentence(SIMPLE_SENTENCE);
        List<String> tags = null;
        try {
            tags = mostImportantTerm.getTags(tokens);
        } catch (IOException e) {
            fail("Error in tags extraction : " + e.getMessage());
        }

        assertNotNull(tags);
        assertEquals(7, tags.size());

    }

    @Test
    void tokenizeSentence() {

        MostImportantTerm mostImportantTerm = new MostImportantTerm();
        String[] tokens = mostImportantTerm.tokenizeSentence(SIMPLE_SENTENCE);
        assertEquals(7, tokens.length);

    }
}
