package com.petcare.petcare.services;

import com.petcare.petcare.model.dto.CareScheduleDTO;
import com.petcare.petcare.model.entities.CareSchedule;
import com.petcare.petcare.repositories.CareScheduleRepository;
import com.petcare.petcare.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CareScheduleService {

    private final CareScheduleRepository careScheduleRepository;

    @Autowired
    private AuthService authService;

    public CareScheduleService(CareScheduleRepository careScheduleRepository) {
        this.careScheduleRepository = careScheduleRepository;
    }

    @Transactional(readOnly = true)
    public CareScheduleDTO findById(Long id) {
        CareSchedule careSchedule = careScheduleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("CareSchedule not found for this id: " + id + " .")
        );
        authService.validateSelfOrAdmin(careSchedule.getPet().getOwner().getId());
        return new CareScheduleDTO(careSchedule);
    }
}
