package com.challenge.agrotis.domain.property.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.agrotis.domain.property.model.Propriedade;
import com.challenge.agrotis.domain.property.repository.PropertyRepository;
import com.challenge.agrotis.dto.PropertyDTO;
import com.challenge.agrotis.mapper.PropertyMapper;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    PropertyMapper propertyMapper;

    public PropertyDTO createProperty(PropertyDTO PropertyDTO) {
        Propriedade Property = propertyRepository.save(propertyMapper.mapDTOToProperty(PropertyDTO));
        return propertyMapper.mapPropertyToDTO(Property);
    }

    public List<PropertyDTO> getListView() {
        List<Propriedade> listEntity = propertyRepository.findAll();
        List<PropertyDTO> listDTO = new ArrayList<>();
        for (Propriedade Property : listEntity) {
            listDTO.add(propertyMapper.mapPropertyToDTO(Property));
        }
        return listDTO;
    }
}
