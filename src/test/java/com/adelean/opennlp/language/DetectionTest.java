package com.adelean.opennlp.language;

import java.io.IOException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class DetectionTest {

    private static final String ENGLISH_SENTENCE = "Hello, how are you dear friend?";
    private static final String FRENCH_SENTENCE = "Bonjour, comment vas-tu mon ami?";

    @org.junit.jupiter.api.Test
    void languageDetection_English() {

        Detection detection = new Detection();

        try {
            String language = detection.languageDetection(ENGLISH_SENTENCE);
            assertEquals(Locale.ENGLISH.getISO3Language(), language);

        } catch (IOException e) {
            fail("Error in english language detection");
        }

    }

    @org.junit.jupiter.api.Test
    void languageDetection_French() {

        Detection detection = new Detection();

        try {
            String language = detection.languageDetection(FRENCH_SENTENCE);
            assertEquals(Locale.FRENCH.getISO3Language(), language);
        } catch (IOException e) {
            fail("Error in french language detection");
        }

    }
}
