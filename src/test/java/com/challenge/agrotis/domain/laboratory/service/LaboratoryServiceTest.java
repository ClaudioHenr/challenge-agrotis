package com.challenge.agrotis.domain.laboratory.service;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.agrotis.domain.laboratory.model.Laboratorio;
import com.challenge.agrotis.domain.laboratory.repository.LaboratoryRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class LaboratoryServiceTest {

    @InjectMocks
    private LaboratoryService laboratoryService;

    @Mock
    private LaboratoryRepository laboratoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnLaboratoriesWithMinimumPeople() {
        Laboratorio lab1 = new Laboratorio(1L, "Laboratório A");
        Laboratorio lab2 = new Laboratorio(2L, "Laboratório B");

        when(laboratoryRepository.findByMinPersons(3)).thenReturn(List.of(lab1, lab2));

        List<Laboratorio> result = laboratoryService.filterLaboratoriesByNumberPeople(3);

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(lab1, lab2);
    }

    @Test
    void shouldReturnEmptyListWhenNoLaboratoriesMatch() {
        when(laboratoryRepository.findByMinPersons(5)).thenReturn(List.of());

        List<Laboratorio> result = laboratoryService.filterLaboratoriesByNumberPeople(5);

        assertThat(result).isEmpty();
    }
}
