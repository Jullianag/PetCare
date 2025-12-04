package com.petcare.petcare.repositories;

import com.petcare.petcare.model.entities.CareSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareScheduleRepository extends JpaRepository<CareSchedule, Long> {
}
