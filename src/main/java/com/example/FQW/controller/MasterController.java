package com.example.FQW.controller;


import com.example.FQW.dto.MasterDto;
import com.example.FQW.service.MasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/master")
public class MasterController {

    private final MasterService masterService;

    @GetMapping("/masters")
    public List<MasterDto> getAllMaster() {
        return masterService.getAll();
    }

    @GetMapping("/masterById/{masterId}")
    public MasterDto getMasterById(@PathVariable("masterId") Long masterID) {
        return masterService.getById(masterID);
    }

    @PostMapping("/addNewMaster")
    public MasterDto addNewMaster(MasterDto masterDto) {
        return masterService.addNew(masterDto);
    }

}
