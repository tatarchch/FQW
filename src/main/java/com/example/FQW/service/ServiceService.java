package com.example.FQW.service;

import com.example.FQW.dto.ServiceDto;
import com.example.FQW.mapper.ServiceMapper;
import com.example.FQW.repositories.ServiceRepository;
import com.example.FQW.response.exception.ServiceException.ServiceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Setter
@Getter
@AllArgsConstructor
public class ServiceService {

    private final ServiceRepository serviceRepository;

    private final ServiceMapper serviceMapper;

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


}