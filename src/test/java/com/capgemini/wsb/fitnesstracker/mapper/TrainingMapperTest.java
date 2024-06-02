package com.capgemini.wsb.fitnesstracker.mapper;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import com.capgemini.wsb.fitnesstracker.training.internal.TrainingMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainingMapperTest {

    private final TrainingMapper trainingMapper = new TrainingMapperImpl();

    @Test
    void testToDto() {
        Training training = new Training();
        training.setId(1L);
        TrainingDto dto = trainingMapper.toDto(training);

        assertEquals(training.getId(), dto.getId());
    }

    @Test
    void testToEntity() {
        TrainingDto dto = new TrainingDto();
        dto.setId(1L);
        Training training = trainingMapper.toEntity(dto);

        assertEquals(dto.getId(), training.getId());
    }
}
