package com.capgemini.wsb.fitnesstracker.repository;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class TrainingRepositoryTest {

    @Autowired
    private TrainingRepository trainingRepository;

    private Training training;

    @BeforeEach
    void setUp() {
        training = new Training();
        training.setId(1L);
        trainingRepository.save(training);
    }

    @Test
    void testFindById() {
        Optional<Training> result = trainingRepository.findById(1L);
        assertTrue(result.isPresent());
    }

    @Test
    void testSave() {
        Training newTraining = new Training();
        newTraining.setId(2L);
        trainingRepository.save(newTraining);

        Optional<Training> result = trainingRepository.findById(2L);
        assertTrue(result.isPresent());
    }
}
