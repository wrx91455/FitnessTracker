package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping
    public List<TrainingDto> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingDto> getTrainingById(@PathVariable Long id) {
        TrainingDto trainingDto = trainingService.getTrainingById(id);
        return ResponseEntity.ok(trainingDto);
    }

    @PostMapping
    public ResponseEntity<TrainingDto> createTraining(@RequestBody TrainingDto trainingDto) {
        TrainingDto createdTraining = trainingService.createTraining(trainingDto);
        return ResponseEntity.ok(createdTraining);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingDto> updateTraining(@PathVariable Long id, @RequestBody TrainingDto trainingDto) {
        TrainingDto updatedTraining = trainingService.updateTraining(id, trainingDto);
        return ResponseEntity.ok(updatedTraining);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.noContent().build();
    }
}
