package com.challenge.agrotis.domain.laboratory.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.agrotis.domain.laboratory.model.Laboratorio;
import com.challenge.agrotis.domain.laboratory.repository.LaboratoryRepository;
import com.challenge.agrotis.domain.person.model.Pessoa;
import com.challenge.agrotis.dto.LaboratoryDTO;
import com.challenge.agrotis.dto.LaboratoryViewDTO;
import com.challenge.agrotis.mapper.LaboratoryMapper;

@Service
public class LaboratoryService {
    
    @Autowired
    LaboratoryRepository laboratoryRepository;
    @Autowired
    LaboratoryMapper laboratoryMapper;

    public LaboratoryDTO createLaboratory(LaboratoryDTO laboratoryDTO) {
        Laboratorio laboratory = laboratoryRepository.save(laboratoryMapper.mapDTOToLaboratory(laboratoryDTO));
        return laboratoryMapper.mapLaboratoryToDTO(laboratory);
    }

    public List<LaboratoryDTO> getListView() {
        List<Laboratorio> listEntity = laboratoryRepository.findAll();
        List<LaboratoryDTO> listDTO = new ArrayList<>();
        for (Laboratorio laboratory : listEntity) {
            listDTO.add(laboratoryMapper.mapLaboratoryToDTO(laboratory));
        }
        return listDTO;
    }

    public List<Laboratorio> filterLaboratoriesByNumberPeople(Integer minNumberPeople) {
        return laboratoryRepository.findByMinPersons(minNumberPeople);
    }

    public List<LaboratoryViewDTO> searchLaboratories(
                                        Integer minNumberPeople, 
                                        String findWordObservation, 
                                        Instant inicialDateIni, 
                                        Instant inicialDateFin, 
                                        Instant finalDateIni,
                                        Instant finalDateFin, 
                                        Boolean sortByInicialDate
        ) {
        if (minNumberPeople == 0) {
            throw new IllegalArgumentException("Quantidade de pessoas deve ser maior que zero");
        }
        List<Laboratorio> listLaboratories = filterLaboratoriesByNumberPeople(minNumberPeople);
        
        System.out.println(listLaboratories);
        for (Laboratorio laboratory : listLaboratories) {
            System.out.println(laboratory.getPersons().size());
        }

        // Filtrar pela palavra em observação
        if (findWordObservation != null && !findWordObservation.isBlank()) {
            Iterator<Laboratorio> iterator = listLaboratories.iterator();
            while (iterator.hasNext()) {
                Laboratorio laboratory = iterator.next();
                
                boolean hasObservation = laboratory.getPersons().stream()
                    .anyMatch(person -> person.getObservacoes() != null &&
                            person.getObservacoes().toLowerCase().contains(findWordObservation.toLowerCase()));
                
                if (!hasObservation) {
                    iterator.remove();
                    System.out.println("REMOVIDO " + laboratory);
                }
            }
            System.out.println("======== FILTRADO PELA OBSERVAÇÃO =======");
        }

        // Filtragem pela Data Inicial (Começo e Fim)
        if (inicialDateIni != null && inicialDateFin != null) {
            Iterator<Laboratorio> iterator = listLaboratories.iterator();
        
            while (iterator.hasNext()) {
                Laboratorio lab = iterator.next();
                boolean temDataValida = false;
        
                for (Pessoa person : lab.getPersons()) {
                    if (person.getDataInicial() != null &&
                        !person.getDataInicial().isBefore(inicialDateIni) &&
                        !person.getDataInicial().isAfter(inicialDateFin)) {
                        temDataValida = true;
                        break;
                    }
                }
        
                if (!temDataValida) {
                    iterator.remove();
                }
            }
        }

        // Filtragem pela Data Final (Começo e Fim)
        if (finalDateIni != null && finalDateFin != null) {
            Iterator<Laboratorio> iterator = listLaboratories.iterator();
        
            while (iterator.hasNext()) {
                Laboratorio lab = iterator.next();
                boolean temDataValida = false;
        
                for (Pessoa person : lab.getPersons()) {
                    if (person.getDataFinal() != null &&
                        !person.getDataFinal().isBefore(finalDateIni) &&
                        !person.getDataFinal().isAfter(finalDateFin)) {
                        temDataValida = true;
                        break;
                    }
                }
        
                if (!temDataValida) {
                    iterator.remove();
                }
            }
        }

        // Ordenação pela Data Inicial
        if (sortByInicialDate) {
            for (Laboratorio lab : listLaboratories) {
                Collections.sort(lab.getPersons(), Comparator.comparing(Pessoa::getDataInicial));
            }
        }
        
        List<LaboratoryViewDTO> listDTO = new ArrayList<>();
        for (Laboratorio laboratory : listLaboratories) {
            System.out.println(laboratory.getPersons().size());
            listDTO.add(laboratoryMapper.mapLaboratoryToViewDTO(laboratory, laboratory.getPersons().size()));
        }

        return listDTO;
    }

}
