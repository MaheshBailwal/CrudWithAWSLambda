package com.bailwal.crudwithlambda.entities;

public class PostalCodeEntity {

	private int id;
	private String postalCode;

	public PostalCodeEntity(int id, String postalCode) {
		this.setId(id);
		this.setPostalCode(postalCode);
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getPostalCode() {
		return postalCode;
	}

	private void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
