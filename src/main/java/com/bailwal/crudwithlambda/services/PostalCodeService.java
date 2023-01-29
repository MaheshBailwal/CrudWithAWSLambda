package com.bailwal.crudwithlambda.services;

import java.util.ArrayList;
import java.util.List;

import com.bailwal.crudwithlambda.dtos.PostalCodeDetailDTO;
import com.bailwal.crudwithlambda.entities.PostalCodeEntity;
import com.bailwal.crudwithlambda.repositories.PostalCodeRepo;

public class PostalCodeService {

	PostalCodeRepo _repo;

	public PostalCodeService() {
		_repo = new PostalCodeRepo();
	}

	public List<PostalCodeDetailDTO> GetAllPostalCodes() throws Exception {

		List<PostalCodeEntity> entities = _repo.GetAllPostalCodes();
		List<PostalCodeDetailDTO> dtos = new ArrayList<PostalCodeDetailDTO>();

		for (PostalCodeEntity postalCodeEntity : entities) {
			dtos.add(new PostalCodeDetailDTO(postalCodeEntity.getId(), postalCodeEntity.getPostalCode()));
		}

		return dtos;
	}

}
