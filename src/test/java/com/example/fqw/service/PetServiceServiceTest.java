package com.example.fqw.service;

import com.example.fqw.dto.PetServiceDto;
import com.example.fqw.entity.Master;
import com.example.fqw.entity.PetService;
import com.example.fqw.exception.MasterNotFoundException;
import com.example.fqw.exception.PetServiceNotFoundException;
import com.example.fqw.mapper.PetServiceMapperImpl;
import com.example.fqw.repositories.MasterRepository;
import com.example.fqw.repositories.PetServiceRepository;
import com.example.fqw.services.PetServiceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceServiceTest {
    @Mock
    private PetServiceRepository petServiceRepository;
    @Mock
    private PetServiceMapperImpl petServiceMapper;
    @Mock
    private MasterRepository masterRepository;

    @InjectMocks
    private PetServiceService petServiceService;

    @Test
    void getAllTestSuccess() {
        var service1 = this.getService(1L, null);
        var service2 = this.getService(2L, null);

        var serviceDto1 = this.getServiceDto(1L);
        var serviceDto2 = this.getServiceDto(2L);

        when(petServiceRepository.findAll()).thenReturn(List.of(service1, service2));
        when(petServiceMapper.toDTO(any(PetService.class))).thenCallRealMethod();

        assertEquals(List.of(serviceDto1, serviceDto2), petServiceService.getAll());
    }

    @Test
    void getServiceByIdTestSuccess() {
        var serviceId = 1L;

        var service = this.getService(1L, null);

        var serviceDto = this.getServiceDto(1L);

        when(petServiceRepository.findById(serviceId)).thenReturn(Optional.of(service));
        when(petServiceMapper.toDTO(service)).thenReturn(serviceDto);

        assertEquals(serviceDto, petServiceService.getServiceById(serviceId));
    }

    @Test
    void getServiceByIdTestFailed() {
        when(petServiceRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(PetServiceNotFoundException.class, () -> petServiceService.getServiceById(1L));

        verifyNoInteractions(petServiceMapper);
    }

    @Test
    void getServicesByMasterIdTestSuccess() {
        var masterId = 1L;

        var master = this.getMaster(masterId, 1);

        var service2 = this.getService(2L, 1);
        var serviceDto = this.getServiceDto(2L);

        when(masterRepository.findById(masterId)).thenReturn(Optional.of(master));
        when(petServiceRepository.findAllByLevelLessThanEqual(1)).thenReturn(List.of(service2));
        when(petServiceMapper.toDTO(service2)).thenCallRealMethod();

        assertEquals(List.of(serviceDto), petServiceService.getServicesByMasterId(masterId));
    }

    @Test
    void getServicesByMasterIdTestFailed() {
        var masterId = 1L;

        when(masterRepository.findById(masterId)).thenReturn(Optional.empty());

        assertThrows(MasterNotFoundException.class, () -> petServiceService.getServicesByMasterId(masterId));
        verifyNoInteractions(petServiceMapper);
    }

    private PetService getService(Long serviceId, Integer serviceLevel) {
        var service = new PetService();
        service.setId(serviceId);
        service.setLevel(serviceLevel);
        return service;
    }

    private PetServiceDto getServiceDto(Long serviceId) {
        var serviceDto = new PetServiceDto();
        serviceDto.setId(serviceId);
        return serviceDto;
    }

    private Master getMaster(Long masterId, Integer masterLevel) {
        var master = new Master();
        master.setId(masterId);
        master.setLevel(masterLevel);
        return master;
    }
}