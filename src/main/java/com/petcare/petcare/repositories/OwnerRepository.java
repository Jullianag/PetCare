package com.petcare.petcare.repositories;

import com.petcare.petcare.model.entities.Owner;
import com.petcare.petcare.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query(nativeQuery = true, value = """
            SELECT tb_owner.email AS username, tb_owner.password, tb_role.id AS roleId, tb_role.authority
            FROM tb_owner
            INNER JOIN tb_owner_role ON tb_owner.id = tb_owner_role.owner_id
            INNER JOIN tb_role ON tb_role.id = tb_owner_role.role_id
            WHERE tb_owner.email = :email
            """)
    List<UserDetailsProjection> searchOwnerAndRolesByEmail(String email);

    Optional<Owner> findByEmail(String email);
}
