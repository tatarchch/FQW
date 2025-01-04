package com.example.FQW.service;

import com.example.FQW.dto.ServiceDto;
import com.example.FQW.entity.Master;
import com.example.FQW.exception.ServiceException.ServiceNotFoundException;
import com.example.FQW.mapper.ServiceMapper;
import com.example.FQW.repositories.MasterRepository;
import com.example.FQW.repositories.ServiceRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@Setter
@Getter
@AllArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;

    private final ServiceMapper serviceMapper;

    private final MasterRepository masterRepository;


    public List<ServiceDto> getAll() {
        return serviceRepository.findAll().stream()
                .map(serviceMapper::toDTO)
                .toList();
    }

    public ServiceDto getById(Long id) {
        return serviceRepository.findById(id)
                .map(serviceMapper::toDTO)
                .orElseThrow(ServiceNotFoundException::new);
    }

    public com.example.FQW.entity.Service getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(ServiceNotFoundException::new);
    }

    public List<ServiceDto> getServicesByMasterId(Long masterId) {
        return masterRepository.findById(masterId)
                .map(Master::getLevel)
                .map(serviceRepository::findAllByLevelLessThanEqual)
                .map(services -> services.stream()
                        .map(serviceMapper::toDTO)
                        .toList())
                .orElseThrow(ServiceNotFoundException::new);
    }

    /*public PreOrderDto pickService(Long serviceId, PreOrderDto preOrderDto) {
        return preOrderService.callService(serviceId, preOrderDto);
    }*/

}