package com.bailwal.crudwithlambda.dtos;

public class PostalCodeDetailDTO {

	private int id;
	private String postalCode;

	public PostalCodeDetailDTO(int id, String postalCode) {
		this.setId(id);
		this.setPostalCode(postalCode);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}