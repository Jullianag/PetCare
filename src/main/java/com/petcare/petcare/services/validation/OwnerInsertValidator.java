package com.petcare.petcare.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.petcare.petcare.model.dto.FieldMessage;
import com.petcare.petcare.model.dto.OwnerInsertDTO;
import com.petcare.petcare.model.entities.Owner;
import com.petcare.petcare.repositories.OwnerRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OwnerInsertValidator implements ConstraintValidator<OwnerInsertValid, OwnerInsertDTO> {
	
	private final OwnerRepository ownerRepository;

    public OwnerInsertValidator(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
	public void initialize(OwnerInsertValid ann) {
	}

	@Override
	public boolean isValid(OwnerInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Optional<Owner> owner = ownerRepository.findByEmail(dto.getEmail());
		if (owner.isPresent()) {
			list.add(new FieldMessage("email", "Email already exists"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
