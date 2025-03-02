package com.challenge.agrotis.domain.person.mapper;

import org.springframework.stereotype.Component;

import com.challenge.agrotis.domain.person.dto.LaboratoryDTO;
import com.challenge.agrotis.domain.person.dto.PersonDTO;
import com.challenge.agrotis.domain.person.dto.PropertyDTO;
import com.challenge.agrotis.domain.person.model.Laboratorio;
import com.challenge.agrotis.domain.person.model.Pessoa;
import com.challenge.agrotis.domain.person.model.Propriedade;

@Component
public class PersonMapper {
    
    public PersonDTO mapPersonToDto(Pessoa person) {
        PropertyDTO propertyDTO = new PropertyDTO(person.getInfosPropriedade().getId(), person.getInfosPropriedade().getNome());
        LaboratoryDTO laboratoryDTO = new LaboratoryDTO(person.getLaboratorio().getId(), person.getLaboratorio().getNome());
        PersonDTO personDTO = new PersonDTO(person.getNome(), person.getDataInicial(), person.getDataFinal(), propertyDTO, laboratoryDTO, person.getObservacoes());
        return personDTO;
    }

    public Pessoa mapDtoToPerson(PersonDTO personDTO) {
        Propriedade property = new Propriedade(personDTO.infosPropriedade().id(), personDTO.infosPropriedade().nome());
        Laboratorio laboratory = new Laboratorio(personDTO.laboratorio().id(), personDTO.laboratorio().nome());
        Pessoa person = new Pessoa(personDTO.nome(), personDTO.dataInicial(), personDTO.dataFinal(), property, laboratory, personDTO.observacoes());    
        return person;
    }
}
