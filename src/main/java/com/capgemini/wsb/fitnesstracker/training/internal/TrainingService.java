package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingNotFoundException;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.internal.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final TrainingMapper trainingMapper;

    public TrainingService(TrainingRepository trainingRepository, UserRepository userRepository, TrainingMapper trainingMapper) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
        this.trainingMapper = trainingMapper;
    }

    public List<TrainingDto> getAllTrainings() {
        return trainingRepository.findAll().stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
    }

    public TrainingDto getTrainingById(Long id) {
        return trainingRepository.findById(id)
                .map(trainingMapper::toDto)
                .orElseThrow(() -> new TrainingNotFoundException("Training not found with id: " + id));
    }

    public TrainingDto createTraining(TrainingDto trainingDto) {
        User user = userRepository.findById(trainingDto.getUserId())
                .orElseThrow(() -> new TrainingNotFoundException("User not found with id: " + trainingDto.getUserId()));
        Training training = new Training(
                user,
                trainingDto.getStartTime(),
                trainingDto.getEndTime(),
                ActivityType.valueOf(trainingDto.getActivityType().toUpperCase()),
                trainingDto.getDistance(),
                trainingDto.getAverageSpeed()
        );
        return trainingMapper.toDto(trainingRepository.save(training));
    }

    public TrainingDto updateTraining(Long id, TrainingDto trainingDto) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException("Training not found with id: " + id));

        training.setUser(userRepository.findById(trainingDto.getUserId())
                .orElseThrow(() -> new TrainingNotFoundException("User not found with id: " + trainingDto.getUserId())));
        training.setStartTime(trainingDto.getStartTime());
        training.setEndTime(trainingDto.getEndTime());
        training.setActivityType(ActivityType.valueOf(trainingDto.getActivityType().toUpperCase()));
        training.setDistance(trainingDto.getDistance());
        training.setAverageSpeed(trainingDto.getAverageSpeed());

        return trainingMapper.toDto(trainingRepository.save(training));
    }

    public void deleteTraining(Long id) {
        if (!trainingRepository.existsById(id)) {
            throw new TrainingNotFoundException("Training not found with id: " + id);
        }
        trainingRepository.deleteById(id);
    }

    public Training saveTraining(Training training) {
        return null;
    }
}
