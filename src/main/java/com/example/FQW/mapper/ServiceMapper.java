package com.example.FQW.mapper;


import com.example.FQW.dto.ServiceDto;
import com.example.FQW.entity.Service;
import org.mapstruct.Mapper;

@Mapper
public interface ServiceMapper {

    //ServiceMapper INSTANCE = Mappers.getMapper( ServiceMapper.class );
    //private final MasterMapper masterMapper;

    ServiceDto toDTO(Service service);

    Service toEntity(Service service);

    /*default List<MasterDto> getMasterDTOs(List<Master> masters) {
        return masters.stream()
                .map(masterMapper::toDTO)
                .toList();
    };*/

}
