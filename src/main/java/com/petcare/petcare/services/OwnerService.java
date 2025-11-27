package com.petcare.petcare.services;

import com.petcare.petcare.model.dto.OwnerDTO;
import com.petcare.petcare.model.entities.Owner;
import com.petcare.petcare.model.entities.Role;
import com.petcare.petcare.projections.UserDetailsProjection;
import com.petcare.petcare.repositories.OwnerRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

}
