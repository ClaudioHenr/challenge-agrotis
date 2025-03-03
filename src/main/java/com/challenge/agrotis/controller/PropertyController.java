package com.challenge.agrotis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.agrotis.domain.property.service.PropertyService;
import com.challenge.agrotis.dto.PropertyDTO;

@RestController
@CrossOrigin
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    PropertyService propertyService;
    
    @PostMapping("/create")
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyDTO PropertyDTO) {
        PropertyDTO newPropertyDTO = propertyService.createProperty(PropertyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPropertyDTO);
    }

    @GetMapping("/listview")
    public ResponseEntity<List<PropertyDTO>> getListViewProperties() {
        List<PropertyDTO> properties = propertyService.getListView();
        if (properties.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(properties);
    }

}
