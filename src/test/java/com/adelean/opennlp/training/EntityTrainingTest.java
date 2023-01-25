package com.adelean.opennlp.training;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class EntityTrainingTest {

    @Test
    void trainModel() {

        EntityTraining training = new EntityTraining();
        try {
            training.trainModel();
        } catch (URISyntaxException | IOException e) {
            fail("Problem during training : " + e.getMessage());
        }

    }
}
