package com.adelean.opennlp.tokenizer;

import opennlp.tools.tokenize.SimpleTokenizer;

public class Tokenizer {

    public String[] tokenize(String sentence) {

        SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;
        return tokenizer.tokenize(sentence);

    }

}
