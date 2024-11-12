package com.example.FQW.service;

import com.example.FQW.dto.MasterDto;
import com.example.FQW.entity.Master;
import com.example.FQW.mapper.MasterMapper;
import com.example.FQW.repositories.MasterRepository;
import com.example.FQW.response.exception.MasterException.MasterAlreadyExistsException;
import com.example.FQW.response.exception.MasterException.MasterNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@AllArgsConstructor
public class MasterService {

    private final MasterRepository masterRepository;

    private final MasterMapper masterMapper;

    private final PreOrderService preOrderService;

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

    //dlya adminki

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

}
