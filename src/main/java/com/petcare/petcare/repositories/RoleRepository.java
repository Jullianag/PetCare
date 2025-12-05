package com.petcare.petcare.repositories;

import com.petcare.petcare.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
