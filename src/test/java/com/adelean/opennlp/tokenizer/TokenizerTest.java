package com.adelean.opennlp.tokenizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

    private static final String SENTENCE = "I want to buy an iPhone case";

    @Test
    void tokenize() {

        Tokenizer tokenizer = new Tokenizer();
        String[] tokens = tokenizer.tokenize(SENTENCE);
        assertEquals(7, tokens.length);

    }
}
