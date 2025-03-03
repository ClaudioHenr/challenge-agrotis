package com.challenge.agrotis.domain.person.repository;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.challenge.agrotis.domain.person.dto.LaboratoryDTO;
import com.challenge.agrotis.domain.person.dto.PersonDTO;
import com.challenge.agrotis.domain.person.dto.PropertyDTO;
import com.challenge.agrotis.domain.person.model.Laboratorio;
import com.challenge.agrotis.domain.person.model.Pessoa;
import com.challenge.agrotis.domain.person.model.Propriedade;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    private Pessoa createPersonForTests(PersonDTO personDTO) {
        Propriedade property = new Propriedade(null, personDTO.infosPropriedade().nome());
        Laboratorio laboratory = new Laboratorio(null, personDTO.laboratorio().nome());
        Pessoa newPerson = new Pessoa(personDTO.nome(), personDTO.dataInicial(), personDTO.dataFinal(), property, laboratory, personDTO.observacoes());
        return personRepository.save(newPerson);
    }
      
    @Test
    @DisplayName("Should return the person by id succesfully")
    void testGetPersonById() {
        PropertyDTO propertyDTO = new PropertyDTO(1L, "Propriedade teste");
        LaboratoryDTO laboratoryDTO = new LaboratoryDTO(1L, "Laboratório teste");
        Pessoa personTest = this.createPersonForTests(new PersonDTO("Ronaldo test", Instant.parse("2024-03-02T10:15:30Z"), Instant.parse("2024-03-02T10:15:30Z"), propertyDTO, laboratoryDTO, "test"));
        Optional<Pessoa> personFound = personRepository.findById(personTest.getId());

        assertThat(personFound.isPresent()).isTrue();
        assertThat(personFound.get().getNome()).isEqualTo("Ronaldo test");
        assertThat(personFound.get().getInfosPropriedade().getNome()).isEqualTo("Propriedade teste");
        assertThat(personFound.get().getLaboratorio().getNome()).isEqualTo("Laboratório teste");
    }

    @Test
    @DisplayName("Should return empty when person not found")
    void testGetPersonByIdNotFound() {
        Optional<Pessoa> personFound = personRepository.findById(881475L);
        assertThat(personFound.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should delete person successfully")
    void testDeletePerson() {
        PropertyDTO propertyDTO = new PropertyDTO(1L, "Propriedade teste");
        LaboratoryDTO laboratoryDTO = new LaboratoryDTO(1L, "Laboratório teste");
        Pessoa personTest = this.createPersonForTests(new PersonDTO("Ronaldo test", Instant.parse("2024-03-02T10:15:30Z"), Instant.parse("2024-03-02T10:15:30Z"), propertyDTO, laboratoryDTO, "test"));
        Long personId = personTest.getId();

        personRepository.deleteById(personId);
        Optional<Pessoa> personDeleted = personRepository.findById(personId);

        assertThat(personDeleted.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should update person successfully")
    void testUpdatePerson() {
        PropertyDTO propertyDTO = new PropertyDTO(1L, "Propriedade teste");
        LaboratoryDTO laboratoryDTO = new LaboratoryDTO(1L, "Laboratório teste");
        Pessoa personTest = this.createPersonForTests(new PersonDTO("Ronaldo test", Instant.parse("2024-03-02T10:15:30Z"), Instant.parse("2024-03-02T10:15:30Z"), propertyDTO, laboratoryDTO, "test"));

        personTest.setNome("Ronaldo updated");
        personRepository.save(personTest);
        Optional<Pessoa> personFound = personRepository.findById(personTest.getId());

        assertThat(personFound.isPresent()).isTrue();
        assertThat(personFound.get().getNome()).isEqualTo("Ronaldo updated");
        assertThat(personFound.get().getInfosPropriedade().getNome()).isEqualTo("Propriedade teste");
        assertThat(personFound.get().getLaboratorio().getNome()).isEqualTo("Laboratório teste");
    }
}
