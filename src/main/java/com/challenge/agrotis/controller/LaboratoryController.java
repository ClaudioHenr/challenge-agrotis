package com.challenge.agrotis.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.agrotis.domain.laboratory.service.LaboratoryService;
import com.challenge.agrotis.dto.LaboratoryDTO;
import com.challenge.agrotis.dto.LaboratoryViewDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin
@RequestMapping("/laboratory")
public class LaboratoryController {

    @Autowired
    LaboratoryService laboratoryService;
    
    @PostMapping("/create")
    public ResponseEntity<LaboratoryDTO> postMethodName(@RequestBody LaboratoryDTO laboratoryDTO) {
        LaboratoryDTO newLaboratoryDTO = laboratoryService.createLaboratory(laboratoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newLaboratoryDTO);
    }

    @GetMapping("/listview")
    public ResponseEntity<List<LaboratoryDTO>> getListViewLaboratories() {
        List<LaboratoryDTO> laboratories = laboratoryService.getListView();
        if (laboratories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(laboratories);
    }

    @GetMapping("/search")
    public ResponseEntity<List<LaboratoryViewDTO>> getMethodName(
        @RequestParam Integer minNumberPeople,
        @RequestParam(required = false) String findWordObservation,
        @RequestParam(required = false) Instant inicialDateIni,
        @RequestParam(required = false) Instant inicialDateFin,
        @RequestParam(required = false) Instant finalDateIni,
        @RequestParam(required = false) Instant finalDateFin,
        @RequestParam(required = false) Boolean sortByInicialDate
    ) {
        List<LaboratoryViewDTO> laboratories = laboratoryService.searchLaboratories(
                                                                    minNumberPeople, 
                                                                    findWordObservation,
                                                                    inicialDateIni,
                                                                    inicialDateFin,
                                                                    finalDateIni,
                                                                    finalDateFin,
                                                                    sortByInicialDate
        );
        if (laboratories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(laboratories);
    }
        
}
