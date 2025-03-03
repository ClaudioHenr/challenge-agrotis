package com.challenge.agrotis.domain.person.service;

import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.agrotis.domain.laboratory.model.Laboratorio;
import com.challenge.agrotis.domain.laboratory.repository.LaboratoryRepository;
import com.challenge.agrotis.domain.person.model.Pessoa;
import com.challenge.agrotis.domain.person.repository.PersonRepository;
import com.challenge.agrotis.domain.property.model.Propriedade;
import com.challenge.agrotis.domain.property.repository.PropertyRepository;
import com.challenge.agrotis.dto.LaboratoryDTO;
import com.challenge.agrotis.dto.PersonDTO;
import com.challenge.agrotis.dto.PropertyDTO;
import com.challenge.agrotis.exception.NotFoundException;
import com.challenge.agrotis.mapper.PersonMapper;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@SpringBootTest
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PropertyRepository propertyRepository;

    @Mock
    private LaboratoryRepository laboratoryRepository;

    @Mock
    private PersonMapper personMapper;

    private PersonDTO personDTO;
    private Pessoa person;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        PropertyDTO propertyDTO = new PropertyDTO(1L, "Propriedade teste");
        LaboratoryDTO laboratoryDTO = new LaboratoryDTO(1L, "Laboratório teste");
        personDTO = new PersonDTO("Ronaldo test", Instant.parse("2024-03-02T10:15:30Z"), Instant.parse("2024-03-02T10:15:30Z"), propertyDTO, laboratoryDTO, "test");
        person = new Pessoa(1L, "Ronaldo test", Instant.parse("2024-03-02T10:15:30Z"), Instant.parse("2024-03-02T10:15:30Z"), null, null, "test");

    }

    @Test
    @DisplayName("Should return the person by id")
    void testShouldGetPersonById() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
    }

    @Test
    @DisplayName("Should throw NotFoundException when person is not found")
    void testShouldThrowNotFountException() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            personService.getPersonById(1L);
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Pessoa não encontrada");
        }
    }

    @Test
    @DisplayName("Should create a person successfully")
    void testShouldCreatePersonSuccessfully() {
        when(personMapper.mapDtoToPerson(any(PersonDTO.class))).thenReturn(person);
        when(personRepository.save(any(Pessoa.class))).thenReturn(person);
        when(personMapper.mapPersonToDto(any(Pessoa.class))).thenReturn(personDTO);

        PersonDTO result = personService.createPerson(personDTO);

        assertThat(result).isNotNull();
        assertThat(result.nome()).isEqualTo("Ronaldo test");
        verify(personRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    @DisplayName("Should update a person successfully")
    void testShouldUpdatePersonSuccesfully() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(propertyRepository.findById(any())).thenReturn(Optional.of(new Propriedade()));
        when(laboratoryRepository.findById(any())).thenReturn(Optional.of(new Laboratorio()));
        when(personRepository.save(any(Pessoa.class))).thenReturn(person);
        when(personMapper.mapPersonToDto(any(Pessoa.class))).thenReturn(personDTO);

        PersonDTO result = personService.updatePerson(1L, personDTO);

        assertThat(result).isNotNull();
        verify(personRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    @DisplayName("Should delete a person successfully")
    void testShouldDeletePersonByIdSuccessfully() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        doNothing().when(personRepository).deleteById(1L);

        personService.deletePersonById(1L);

        verify(personRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Should throw NotFoundException when deleting a person not found")
    void testDeletePersonByIdShouldThrowNotFoundException() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        try {
            personService.deletePersonById(1L);
        } catch (NotFoundException ex) {
            assertThat(ex.getMessage()).isEqualTo("Pessoa não encontrada");
        }
    }

}
