package com.example.fqw.service;

import com.example.fqw.dto.MasterDto;
import com.example.fqw.entity.Master;
import com.example.fqw.entity.PetService;
import com.example.fqw.entity.Place;
import com.example.fqw.exception.MasterException.MasterAlreadyExistsException;
import com.example.fqw.exception.MasterException.MasterNotFoundException;
import com.example.fqw.exception.PetServiceException.PetServiceNotFoundException;
import com.example.fqw.exception.PlaceException.PlaceNotFoundException;
import com.example.fqw.mapper.MasterMapperImpl;
import com.example.fqw.repositories.MasterRepository;
import com.example.fqw.repositories.PetServiceRepository;
import com.example.fqw.repositories.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MasterServiceTest {
    @Mock
    private MasterRepository masterRepository;
    @Mock
    private MasterMapperImpl masterMapper;
    @Mock
    private PlaceRepository placeRepository;
    @Mock
    private PetServiceRepository petServiceRepository;

    @InjectMocks
    private MasterService masterService;

    @Test
    void getAllTestSuccess() {
        var master1 = this.getMasterEntity(1L, null, null, null);
        var master2 = this.getMasterEntity(2L, null, null, null);

        var masterDto1 = this.getMasterDto(1L, null, null);
        var masterDto2 = this.getMasterDto(2L, null, null);

        when(masterRepository.findAll()).thenReturn(List.of(master1, master2));
        when(masterMapper.toDTO(any(Master.class))).thenCallRealMethod();

        assertEquals(List.of(masterDto1, masterDto2), masterService.getAll());
    }

    @Test
    void getAllTestFailed() {
        when(masterRepository.findAll()).thenReturn(Collections.emptyList());

        assertTrue(masterService.getAll().isEmpty());

        verifyNoInteractions(masterMapper);
    }

    @Test
    void getByIdTestSuccess() {
        var masterId = 1L;

        var master = this.getMasterEntity(masterId, "Pup", null, null);
        var masterDto = this.getMasterDto(masterId, "Pup", null);

        when(masterRepository.findById(masterId)).thenReturn(Optional.of(master));
        when(masterMapper.toDTO(master)).thenCallRealMethod();

        assertEquals(masterDto, masterService.getById(masterId));
    }

    @Test
    void getByIdTestFailed() {
        when(masterRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(MasterNotFoundException.class, () -> masterService.getById(1L));

        verifyNoInteractions(masterMapper);
    }

    @Test
    void getMastersByPlaceIdTestSuccessEntity() {
        var placeId = 1L;

        var place = this.getPlaceEntity(placeId);

        var master = this.getMasterEntity(1L, null, place, null);
        var masterDto1 = this.getMasterDto(1L, null, null);

        when(placeRepository.findById(placeId)).thenReturn(Optional.of(place));
        when(masterRepository.findAllByPlace(place)).thenReturn(List.of(master));
        when(masterMapper.toDTO(any(Master.class))).thenCallRealMethod();

        assertEquals(List.of(masterDto1), masterService.getMastersByPlaceId(placeId));
    }

    @Test
    void getMastersByPlaceIdTestFailedEntity() {
        when(placeRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(MasterNotFoundException.class, () -> masterService.getMastersByPlaceId(1L));

        verifyNoInteractions(masterMapper);
    }

    @Test
    void getMastersByPlaceIdAndServiceIdTestSuccessEntity() {
        var placeId = 1L;
        var serviceId = 1L;

        var place = this.getPlaceEntity(placeId);
        var service = this.getPetService(1L, 2);

        var master1 = this.getMasterEntity(1L, null, place, 1);
        var master2 = this.getMasterEntity(2L, null, place, 2);

        var masterDto = this.getMasterDto(2L, null, 2);

        when(placeRepository.findById(placeId)).thenReturn(Optional.of(place));
        when(masterRepository.findAllByPlace(place)).thenReturn(List.of(master1, master2));
        when(petServiceRepository.findById(serviceId)).thenReturn(Optional.of(service));
        when(masterMapper.toDTO(any(Master.class))).thenCallRealMethod();

        assertEquals(List.of(masterDto), masterService.getMastersByPlaceIdAndServiceId(placeId, serviceId));
    }

    @Test
    void getMastersByPlaceIdAndServiceIdTestFailedByPlaceEntity() {
        when(placeRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(PlaceNotFoundException.class, () -> masterService.getMastersByPlaceIdAndServiceId(1L, 1L));

        verifyNoInteractions(masterRepository);
        verifyNoInteractions(petServiceRepository);
        verifyNoInteractions(masterMapper);
    }

    @Test
    void getMastersByPlaceIdAndServiceIdTestFailedByPetServiceEntity() {
        var placeId = 1L;

        var place = this.getPlaceEntity(placeId);

        var master = this.getMasterEntity(1L, null, place, 1);

        when(placeRepository.findById(placeId)).thenReturn(Optional.of(place));
        when(masterRepository.findAllByPlace(place)).thenReturn(List.of(master));
        when(petServiceRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(PetServiceNotFoundException.class, () -> masterService.getMastersByPlaceIdAndServiceId(placeId, 1L));

        verifyNoInteractions(masterMapper);
    }

    @Test
    void addNewMasterTestSuccess() {
        var masterDto = this.getMasterDto(1L, null, null);

        when(masterMapper.toEntity(masterDto)).thenCallRealMethod();
        when(masterRepository.save(any(Master.class))).then(invocation -> invocation.getArgument(0));
        when(masterMapper.toDTO(any(Master.class))).thenCallRealMethod();

        assertEquals(masterDto, masterService.addNewMaster(masterDto));
    }

    @Test
    void addNewMasterTestFailed() {
        var masterDto = this.getMasterDto(1L, null, null);

        when(masterMapper.toEntity(masterDto)).thenCallRealMethod();
        //when(masterRepository.save(any(Master.class))).thenThrow(MasterAlreadyExistsException.class);
        when(masterRepository.save(any(Master.class))).thenReturn(null);
        //when(masterMapper.toDTO(any(Master.class))).thenCallRealMethod();

        assertThrows(MasterAlreadyExistsException.class, () -> masterService.addNewMaster(masterDto));

        verify(masterMapper, times(0)).toDTO(any(Master.class));
    }

    @Test
    void inactivateMasterByIdTestSuccess() {
        var masterId = 1L;

        var master = this.getMasterEntity(masterId, null, null, null);
        var masterDto = this.getMasterDto(masterId, null, null);

        when(masterRepository.findById(masterId)).thenReturn(Optional.of(master));
        when(masterMapper.inactivateMaster(master)).thenCallRealMethod();
        when(masterRepository.save(master)).then(invocation -> invocation.getArgument(0));
        when(masterMapper.toDTO(master)).thenCallRealMethod();

        assertEquals(masterDto, masterService.inactivateMasterById(masterId));
        assertFalse(master.getActive());
    }

    @Test
    void inactivateMasterByIdTestFailed() {
        when(masterRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(MasterNotFoundException.class, () -> masterService.inactivateMasterById(1L));

        verifyNoInteractions(masterMapper);
    }


    private Master getMasterEntity(Long masterId,
                                   String name,
                                   Place place,
                                   Integer level) {
        var master = new Master();
        master.setId(masterId);
        master.setName(name);
        master.setPlace(place);
        master.setLevel(level);
        return master;
    }

    private MasterDto getMasterDto(Long masterId, String name, Integer level) {
        var masterDto = new MasterDto();
        masterDto.setId(masterId);
        masterDto.setName(name);
        masterDto.setLevel(level);
        return masterDto;
    }

    private Place getPlaceEntity(Long placeId) {
        var place = new Place();
        place.setId(placeId);
        return place;
    }

    private PetService getPetService(Long serviceId, Integer level) {
        var service = new PetService();
        service.setId(serviceId);
        service.setLevel(level);
        return service;
    }

}