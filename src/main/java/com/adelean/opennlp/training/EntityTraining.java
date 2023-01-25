package com.adelean.opennlp.training;

import opennlp.tools.namefind.*;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Collections;

public class EntityTraining {

    public void trainModel() throws URISyntaxException, IOException {

        File trainingData = Paths.get(this.getClass().getClassLoader().getResource("en-ner-products.train").toURI()).toFile();

        ObjectStream<String> lineStream = new PlainTextByLineStream(new MarkableFileInputStreamFactory(trainingData),
                StandardCharsets.UTF_8);

        TokenNameFinderModel model;

        try (ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream)) {
            model = NameFinderME.train("en", "entity", sampleStream, TrainingParameters.defaultParams(),
                    TokenNameFinderFactory.create(null, null, Collections.emptyMap(), new BilouCodec()));
        }

        if (model != null) {
            File saved = new File("src/main/resources/ner-adelean-entity-model.bin");
            FileOutputStream fos = new FileOutputStream(saved);
            model.serialize(fos);
        }

    }

}
