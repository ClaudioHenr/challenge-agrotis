package com.challenge.agrotis.domain.person.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.agrotis.domain.person.dto.PersonDTO;
import com.challenge.agrotis.domain.person.exception.NotFoundException;
import com.challenge.agrotis.domain.person.mapper.PersonMapper;
import com.challenge.agrotis.domain.person.model.Laboratorio;
import com.challenge.agrotis.domain.person.model.Pessoa;
import com.challenge.agrotis.domain.person.model.Propriedade;
import com.challenge.agrotis.domain.person.repository.LaboratoryRepository;
import com.challenge.agrotis.domain.person.repository.PersonRepository;
import com.challenge.agrotis.domain.person.repository.PropertyRepository;

@Service
public class PersonService {
    
    @Autowired
    PersonRepository personRepository;

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    LaboratoryRepository laboratoryRepository;

    @Autowired
    PersonMapper personMapper;

    public PersonDTO createPerson(PersonDTO personDTO) {
        Pessoa newPerson = personMapper.mapDtoToPerson(personDTO);
        Pessoa savedPerson = personRepository.save(newPerson);
        return personMapper.mapPersonToDto(savedPerson);
    }

    public List<PersonDTO> getAllPeople() {
        List<Pessoa> listEntity = personRepository.findAll();
        List<PersonDTO> listDTO = new ArrayList<>();
        for (Pessoa pessoa : listEntity) {
            listDTO.add(personMapper.mapPersonToDto(pessoa));
        }
        return listDTO;
    }

    public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
        Pessoa pessoa = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada"));
        Propriedade property = propertyRepository.findById(personDTO.infosPropriedade().id())
                .orElseThrow(() -> new NotFoundException("Propriedade não encontrada"));
        Laboratorio laboratory = laboratoryRepository.findById(personDTO.laboratorio().id())
                .orElseThrow(() -> new NotFoundException("Laboratório não encontrado"));
    
        pessoa.setNome(personDTO.nome());
        pessoa.setDataInicial(personDTO.dataInicial());
        pessoa.setDataFinal(personDTO.dataFinal());
        pessoa.setObservacoes(personDTO.observacoes());
        pessoa.setInfosPropriedade(property);
        pessoa.setLaboratorio(laboratory);

        Pessoa updatedEntity = personRepository.save(pessoa);
        PersonDTO updatedPersonDTO = personMapper.mapPersonToDto(updatedEntity);
        return updatedPersonDTO;
    }

    public PersonDTO getPersonById(Long id) {
        Optional<Pessoa> person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new NotFoundException("Pessoa não encontrada");
        } else {
            PersonDTO personDTO = personMapper.mapPersonToDto(person.get());
            return personDTO;
        }
    }

    public void deletePersonById(Long id) {
        Optional<Pessoa> person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new NotFoundException("Pessoa não encontrada");
        }
        personRepository.deleteById(id);
    }
}
