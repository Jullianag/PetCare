package com.petcare.petcare.services.validation;

import com.petcare.petcare.model.dto.FieldMessage;
import com.petcare.petcare.model.dto.OwnerUpdateDTO;
import com.petcare.petcare.model.entities.Owner;
import com.petcare.petcare.repositories.OwnerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OwnerUpdateValidator implements ConstraintValidator<OwnerUpdateValid, OwnerUpdateDTO> {

	private final HttpServletRequest request;

	private final OwnerRepository ownerRepository;

    public OwnerUpdateValidator(HttpServletRequest request, OwnerRepository ownerRepository) {
        this.request = request;
        this.ownerRepository = ownerRepository;
    }

    @Override
	public void initialize(OwnerUpdateValid ann) {
	}

	@Override
	public boolean isValid(OwnerUpdateDTO dto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long ownerId = Long.parseLong(uriVars.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		Optional<Owner> owner = ownerRepository.findByEmail(dto.getEmail());
		if (owner.isPresent() && ownerId != owner.get().getId()) {
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
