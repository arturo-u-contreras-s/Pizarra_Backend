package com.cing.pizarra.mappers.impl;

import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import com.cing.pizarra.domain.dto.TeamDto;
import com.cing.pizarra.domain.entities.TeamEntity;
import com.cing.pizarra.mappers.Mapper;

@Component
public class TeamMapper implements Mapper<TeamEntity, TeamDto> {
    private ModelMapper modelMapper;
    public TeamMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TeamDto mapTo(TeamEntity teamEntity) {
        return modelMapper.map(teamEntity, TeamDto.class);
    }

    @Override
    public TeamEntity mapFrom(TeamDto teamDto) {
        return modelMapper.map(teamDto, TeamEntity.class);
    }
}
