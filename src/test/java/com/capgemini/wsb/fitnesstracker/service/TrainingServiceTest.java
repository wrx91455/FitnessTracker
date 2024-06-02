package com.capgemini.wsb.fitnesstracker.service;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingRepository;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TrainingServiceTest {

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private TrainingService trainingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTrainingById() {
        Training training = new Training();
        training.setId(1L);

        when(trainingRepository.findById(anyLong())).thenReturn(Optional.of(training));

        TrainingDto result = trainingService.getTrainingById(1L);

        assertEquals(training, result);
        verify(trainingRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveTraining() {
        Training training = new Training();

        when(trainingRepository.save(any(Training.class))).thenReturn(training);

        Training result = trainingService.saveTraining(training);

        assertEquals(training, result);
        verify(trainingRepository, times(1)).save(training);
    }

    // Add more test methods as needed
}
