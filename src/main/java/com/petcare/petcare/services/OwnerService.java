package com.petcare.petcare.services;

import com.petcare.petcare.model.dto.OwnerDTO;
import com.petcare.petcare.model.dto.OwnerInsertDTO;
import com.petcare.petcare.model.dto.OwnerUpdateDTO;
import com.petcare.petcare.model.dto.RoleDTO;
import com.petcare.petcare.model.entities.Owner;
import com.petcare.petcare.model.entities.Role;
import com.petcare.petcare.projections.UserDetailsProjection;
import com.petcare.petcare.repositories.OwnerRepository;
import com.petcare.petcare.repositories.RoleRepository;
import com.petcare.petcare.services.exceptions.DatabaseException;
import com.petcare.petcare.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OwnerService implements UserDetailsService {

    private final OwnerRepository ownerRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public OwnerService(OwnerRepository ownerRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.ownerRepository = ownerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> result = ownerRepository.searchOwnerAndRolesByEmail(username);
        if (result.size() == 0) {
            throw new UsernameNotFoundException("Email not found");
        }

        Owner owner = new Owner();
        owner.setEmail(result.get(0).getUsername());
        owner.setPassword(result.get(0).getPassword());
        for (UserDetailsProjection projection : result) {
            owner.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return owner;
    }

    protected Owner authenticated() {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");

            return ownerRepository.findByEmail(username).get();

        } catch (Exception e) {
            throw new UsernameNotFoundException("Email not found");
        }

    }

    @Transactional(readOnly = true)
    public OwnerDTO getMe() {
        Owner owner = authenticated();
        return new OwnerDTO(owner);
    }

    @Transactional(readOnly = true)
    public Page<OwnerDTO> findAll(Pageable pageable) {
        Page<Owner> ownerList = ownerRepository.findAll(pageable);
        return ownerList.map(OwnerDTO::new);
    }

    @Transactional(readOnly = true)
    public OwnerDTO findById(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Owner not found for this id: " + id + " .")
        );
        return new OwnerDTO(owner);
    }

    @Transactional
    public OwnerDTO insert(OwnerInsertDTO dto) {

        Owner entity = new Owner();
        copyDtoToEntity(dto, entity);

        entity.getRoles().clear();
        Role role = roleRepository.findByAuthority("ROLE_CLIENT");
        entity.getRoles().add(role);

        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        return new OwnerDTO(ownerRepository.save(entity));
    }

    @Transactional
    public OwnerDTO update(Long id, OwnerUpdateDTO dto) {

        try {
            Owner entity = ownerRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new OwnerDTO(ownerRepository.save(entity));

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Owner not found for this id: " + id + " .");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {

        if (!ownerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Owner not found for this id: " + id + " .");
        }
        try {
            ownerRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity violation");
        }
    }

    private void copyDtoToEntity(OwnerDTO dto, Owner entity) {

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setBirthDate(dto.getBirthDate());

        entity.getRoles().clear();
        for (RoleDTO roleDto : dto.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDto.getId());
            entity.getRoles().add(role);
        }
    }

}
