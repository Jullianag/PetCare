package com.petcare.petcare.services;

import com.petcare.petcare.model.entities.Owner;
import com.petcare.petcare.model.entities.Role;
import com.petcare.petcare.projections.UserDetailsProjection;
import com.petcare.petcare.repositories.OwnerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService implements UserDetailsService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
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

}
