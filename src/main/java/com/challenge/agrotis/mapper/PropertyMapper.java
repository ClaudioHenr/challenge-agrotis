package com.challenge.agrotis.mapper;

import org.springframework.stereotype.Component;

import com.challenge.agrotis.domain.property.model.Propriedade;
import com.challenge.agrotis.dto.PropertyDTO;

@Component
public class PropertyMapper {

    public PropertyDTO mapPropertyToDTO(Propriedade property) {
        PropertyDTO propertyDTO = new PropertyDTO(property.getId(), property.getNome());
        return propertyDTO;
    }

    public Propriedade mapDTOToProperty(PropertyDTO propertyDTO) {
        Propriedade property = new Propriedade(propertyDTO.id(), propertyDTO.nome());
        return property;
    }

}
