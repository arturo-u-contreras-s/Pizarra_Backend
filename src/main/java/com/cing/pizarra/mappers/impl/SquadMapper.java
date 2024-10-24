package com.cing.pizarra.mappers.impl;

import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import com.cing.pizarra.domain.dto.SquadDto;
import com.cing.pizarra.domain.entities.SquadEntity;
import com.cing.pizarra.mappers.Mapper;

@Component
public class SquadMapper implements Mapper<SquadEntity, SquadDto> {
    private ModelMapper modelMapper;
    public SquadMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SquadDto mapTo(SquadEntity squadEntity) {
        return modelMapper.map(squadEntity, SquadDto.class);
    }

    @Override
    public SquadEntity mapFrom(SquadDto squadDto) {
        return modelMapper.map(squadDto, SquadEntity.class);
    }
}
