package com.challenge.agrotis.mapper;

import org.springframework.stereotype.Component;

import com.challenge.agrotis.domain.laboratory.model.Laboratorio;
import com.challenge.agrotis.dto.LaboratoryDTO;
import com.challenge.agrotis.dto.LaboratoryViewDTO;

@Component
public class LaboratoryMapper {
    
    public LaboratoryDTO mapLaboratoryToDTO(Laboratorio laboratory) {
        LaboratoryDTO laboratoryDTO = new LaboratoryDTO(laboratory.getId(), laboratory.getNome());
        return laboratoryDTO;
    }

    public Laboratorio mapDTOToLaboratory(LaboratoryDTO laboratoryDTO) {
        Laboratorio laboratory = new Laboratorio(laboratoryDTO.id(), laboratoryDTO.nome());
        return laboratory;
    }

    public LaboratoryViewDTO mapLaboratoryToViewDTO(Laboratorio laboratory, Integer numberPeople) {
        LaboratoryViewDTO laboratoryViewDTO = new LaboratoryViewDTO(laboratory.getId(), laboratory.getNome(), numberPeople);
        return laboratoryViewDTO;
    }
      
}
