package com.cing.pizarra.mappers.impl;

import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import com.cing.pizarra.domain.dto.PlayerDto;
import com.cing.pizarra.domain.entities.PlayerEntity;
import com.cing.pizarra.mappers.Mapper;

@Component
public class PlayerMapper implements Mapper<PlayerEntity, PlayerDto> {
    private ModelMapper modelMapper;

    public PlayerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PlayerDto mapTo(PlayerEntity playerEntity) {
        return modelMapper.map(playerEntity, PlayerDto.class);
    }

    @Override
    public PlayerEntity mapFrom(PlayerDto playerDto) {
        return modelMapper.map(playerDto, PlayerEntity.class);
    }
}