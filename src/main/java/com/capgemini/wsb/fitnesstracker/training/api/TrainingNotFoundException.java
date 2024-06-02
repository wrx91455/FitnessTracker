package com.capgemini.wsb.fitnesstracker.training.api;

public class TrainingNotFoundException extends RuntimeException {
    public TrainingNotFoundException(String message) {
        super(message);
    }
}
