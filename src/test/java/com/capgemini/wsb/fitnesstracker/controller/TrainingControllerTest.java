package com.capgemini.wsb.fitnesstracker.controller;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TrainingControllerTest {

    @Mock
    private TrainingService trainingService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private TrainingController trainingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTrainingById() {
        Training training = new Training();
        training.setId(1L);

        when(trainingService.getTrainingById(anyLong())).thenReturn(training);

        String viewName = trainingController.getTrainingById(1L, model);

        verify(model, times(1)).addAttribute("training", training);
        verify(trainingService, times(1)).getTrainingById(1L);
    }

    @Test
    void testSaveTraining() {
        Training training = new Training();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(trainingService.saveTraining(any(Training.class))).thenReturn(training);

        String viewName = trainingController.saveTraining(training, bindingResult);

        verify(trainingService, times(1)).saveTraining(training);
        verify(bindingResult, times(1)).hasErrors();
    }
}
