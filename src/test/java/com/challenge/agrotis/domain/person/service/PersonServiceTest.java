package com.challenge.agrotis.domain.person.service;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.challenge.agrotis.domain.person.dto.PersonDTO;
import com.challenge.agrotis.domain.person.mapper.PersonMapper;
import com.challenge.agrotis.domain.person.model.Pessoa;
import com.challenge.agrotis.domain.person.repository.LaboratoryRepository;
import com.challenge.agrotis.domain.person.repository.PersonRepository;
import com.challenge.agrotis.domain.person.repository.PropertyRepository;

public class PersonServiceTest {    
// Anotação	Função
// @Mock	Cria um mock (simulação) do objeto.
// @InjectMocks	Injeta os mocks nas dependências do objeto testado.
// Mockito.when()	Define o comportamento do mock.
// verify()	Verifica se o método foi chamado.

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PropertyRepository propertyRepository;
    
    @Mock
    private LaboratoryRepository laboratoryRepository;

    @Mock
    private PersonMapper personMapper;
    
    @Test
    void testCreatePerson() {
    }
    
    @Test
    void testDeletePersonById() {
        
    }
    
    @Test
    void testGetAllPeople() {
        
    }
    
    @Test
    void testGetPersonById() {
        
        when(personRepository.findById(1)).thenReturn(laboratoryRepository);

    }
}
