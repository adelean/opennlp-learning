package com.adelean.opennlp.language;

import opennlp.tools.langdetect.Language;
import opennlp.tools.langdetect.LanguageDetector;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;

import java.io.IOException;
import java.io.InputStream;

public class Detection {

    public String languageDetection(String input) throws IOException {

        InputStream modelBin = this.getClass().getClassLoader().getResourceAsStream("langdetect-183.bin");

        LanguageDetectorModel model = new LanguageDetectorModel(modelBin);

        LanguageDetector detector = new LanguageDetectorME(model);

        Language language = detector.predictLanguage(input);

        return language.getLang();

    }

}
