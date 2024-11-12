package com.example.FQW.mapper;


import com.example.FQW.dto.MasterDto;
import com.example.FQW.entity.Master;
import org.mapstruct.Mapper;


@Mapper
public interface MasterMapper {

    MasterDto toDTO(Master master);

    Master toEntity(MasterDto masterDto);

    default Master inactivateMaster(Master master) {
        master.setActive(false);
        return master;
    }

}
