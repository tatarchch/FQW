package com.example.fqw.mapper;


import com.example.fqw.dto.MasterDto;
import com.example.fqw.entity.Master;
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
