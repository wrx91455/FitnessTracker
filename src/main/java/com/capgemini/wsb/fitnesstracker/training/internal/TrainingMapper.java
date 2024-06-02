package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainingMapper {

    @Mapping(source = "user.id", target = "userId")
    TrainingDto toDto(Training training);

    @Mapping(source = "userId", target = "user.id")
    Training toEntity(TrainingDto trainingDto);
}
