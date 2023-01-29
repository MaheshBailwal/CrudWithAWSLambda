package com.bailwal.crudwithlambda.services;

import java.util.ArrayList;
import java.util.List;

import com.bailwal.crudwithlambda.dtos.AddPostalCodeDTO;
import com.bailwal.crudwithlambda.dtos.PostalCodeDetailDTO;
import com.bailwal.crudwithlambda.entities.PostalCodeEntity;
import com.bailwal.crudwithlambda.repositories.PostalCodeRepo;

public class PostalCodeService2 {

	PostalCodeRepo _repo;

	public PostalCodeService2() {
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

	public int AddPostalCode(AddPostalCodeDTO dto) throws Exception {

		PostalCodeEntity entity = new PostalCodeEntity(0, dto.getPostalCode());
		return _repo.Add(entity);
	}

}
