package com.bailwal.crudwithlambda.dtos;

public class AddPostalCodeDTO {
	// https://stackoverflow.com/questions/241003/how-to-get-a-value-from-the-last-inserted-row

	private String postalCode;

	public AddPostalCodeDTO() {

	}
	public AddPostalCodeDTO(String postalCode) {

		this.setPostalCode(postalCode);
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
