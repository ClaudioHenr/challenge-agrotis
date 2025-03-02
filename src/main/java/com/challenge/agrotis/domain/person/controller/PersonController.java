package com.challenge.agrotis.domain.person.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.agrotis.domain.person.dto.PersonDTO;
import com.challenge.agrotis.domain.person.mapper.PersonMapper;
import com.challenge.agrotis.domain.person.service.PersonService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@CrossOrigin
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonMapper personMapper;

    @PostMapping("/create")
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonDTO personDTO) {
        personService.createPerson(personDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(personDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PersonDTO>> getPeople() {
        List<PersonDTO> listDTO = personService.getAllPeople();
        if (listDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(listDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> putPerson(@PathVariable Long id, @Valid @RequestBody PersonDTO updatePersonDTO) {
        PersonDTO personDTO = personService.updatePerson(id, updatePersonDTO);
        return ResponseEntity.status(HttpStatus.OK).body(personDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id) {
        PersonDTO personDTO = personService.getPersonById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(personDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        personService.deletePersonById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }   
    
}
