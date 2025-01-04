package com.example.FQW.service;

import com.example.FQW.dto.MasterDto;
import com.example.FQW.dto.PreOrderDto;
import com.example.FQW.entity.Master;
import com.example.FQW.exception.MasterException.MasterAlreadyExistsException;
import com.example.FQW.exception.MasterException.MasterNotFoundException;
import com.example.FQW.exception.PlaceException.PlaceNotFoundException;
import com.example.FQW.exception.ServiceException.ServiceNotFoundException;
import com.example.FQW.mapper.MasterMapper;
import com.example.FQW.repositories.MasterRepository;
import com.example.FQW.repositories.PlaceRepository;
import com.example.FQW.repositories.ServiceRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class MasterService {

    private final MasterRepository masterRepository;

    private final MasterMapper masterMapper;

    private final PlaceRepository placeRepository;

    private final ServiceRepository serviceRepository;

    public List<MasterDto> getAll() {
        return masterRepository.findAll().stream()
                .map(masterMapper::toDTO)
                .toList();
    }

    public MasterDto getById(Long id) {
        return masterRepository.findById(id)
                .map(masterMapper::toDTO)
                .orElseThrow(MasterNotFoundException::new);
    }

    public MasterDto addNew(MasterDto masterDto) {
        return Optional.of(masterDto)
                .map(masterMapper::toEntity)
                .map(masterRepository::save)
                .map(masterMapper::toDTO)
                .orElseThrow(MasterAlreadyExistsException::new);
    }

    public MasterDto inactivateMasterById(Long id) {
        return masterRepository.findById(id)
                .map(masterMapper::inactivateMaster)
                .map(masterRepository::save)
                .map(masterMapper::toDTO)
                .orElseThrow(MasterNotFoundException::new);
    }

    public Master getMasterById(Long id) {
        return masterRepository.findById(id)
                .orElseThrow(MasterNotFoundException::new);
    }

    public List<MasterDto> getMastersByPlaceId(Long placeId) {
        return placeRepository.findById(placeId)
                .map(masterRepository::findAllByPlace)
                .map(masters -> masters.stream()
                        .map(masterMapper::toDTO)
                        .toList())
                .orElseThrow(MasterNotFoundException::new);
    }

    /*public PreOrderDto pickMaster(Long masterId, PreOrderDto preOrderDto) {
        return preOrderService.callMaster(masterId, preOrderDto);
    }*/

    public List<MasterDto> getMastersByPlaceIdAndServiceId(Long placeId, Long serviceId) {
        //mastersWithPlace
        return placeRepository.findById(placeId)
                .map(masterRepository::findAllByPlace)
                .map(masters -> serviceRepository.findById(serviceId)
                        .map(com.example.FQW.entity.Service::getLevel)
                        .map(level -> masters.stream()
                                .filter(service -> service.getLevel() >= level)
                                .toList())
                        .orElseThrow(ServiceNotFoundException::new))
                .map(master -> master.stream()
                        .map(masterMapper::toDTO)
                        .toList())
                .orElseThrow(PlaceNotFoundException::new);

    }

    /*public List<MasterDto> getMastersByPlaceIdAndServiceId(Long placeId, Long serviceId) {
        //mastersWithPlace
        return this.getMastersByPlaceId(placeId)
                //сравнение двух массивов
                .stream()
                //.distinct()
                .filter(
                        //mastersWithLevel
                        serviceRepository.findById(serviceId)
                                .map(com.example.FQW.entity.Service::getLevel)
                                .map(masterRepository::findAllByLevelGreaterThanEqual)
                                .map(masters -> masters.stream()
                                        .map(masterMapper::toDTO)
                                        .toList())
                                .orElseThrow(ServiceNotFoundException::new)

                                ::contains)
                .toList();

    }*/

    /*public List<MasterDto> getMastersByPlaceIdAndServiceId(Long placeId, Long serviceId) {
        List<MasterDto> mastersWithPlaces = this.getMastersByPlaceId(placeId);

        List<MasterDto> mastersWithLevel = serviceRepository.findById(serviceId)
                .map(com.example.FQW.entity.Service::getLevel)
                .map(masterRepository::findAllByLevelGreaterThanEqual)
                .map(masters -> masters.stream()
                        .map(masterMapper::toDTO)
                        .toList())
                .orElseThrow(ServiceNotFoundException::new);

        return mastersWithPlaces.stream()
                .distinct()
                .filter(mastersWithLevel::contains)
                .toList();

    }*/

}
