package com.example.fqw.mapper;


import com.example.fqw.dto.MasterDto;
import com.example.fqw.entity.Master;
import org.mapstruct.Mapper;


@Mapper
public interface MasterMapper {

    Master toEntity(MasterDto masterDto);

    MasterDto toDTO(Master master);

    default Master inactivateMaster(Master master) {
        master.setActive(false);
        return master;
    }

}
