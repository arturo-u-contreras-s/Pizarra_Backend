package com.cing.pizarra.mappers.impl;

import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import com.cing.pizarra.domain.dto.PlayerPositionDto;
import com.cing.pizarra.domain.entities.PlayerPositionEntity;
import com.cing.pizarra.mappers.Mapper;

@Component
public class PlayerPositionMapper implements Mapper<PlayerPositionEntity, PlayerPositionDto> {
    private ModelMapper modelMapper;
    public PlayerPositionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PlayerPositionDto mapTo(PlayerPositionEntity playerPositionEntity) {
        return modelMapper.map(playerPositionEntity, PlayerPositionDto.class);
    }

    @Override
    public PlayerPositionEntity mapFrom(PlayerPositionDto playerPositionDto) {
        return modelMapper.map(playerPositionDto, PlayerPositionEntity.class);
    }
}