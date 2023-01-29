package com.bailwal.crudwithlambda.repositories;

import java.util.*;

import com.bailwal.crudwithlambda.entities.PostalCodeEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostalCodeRepo {

	public PostalCodeRepo() {
		// TODO Auto-generated constructor stub
	}
	public List<PostalCodeEntity> GetAllPostalCodes() throws Exception {
		
		List<PostalCodeEntity> postalCodes = new ArrayList<PostalCodeEntity>();
		try (Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://free-instance.c3enhs8xm5kf.us-east-1.rds.amazonaws.com:5432/purolator", "postgres",
				"password123")) {

		PreparedStatement preparedStatement = conn.prepareStatement("Select * from postalcode");

		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String code = rs.getString("code");
			postalCodes.add(new PostalCodeEntity(id, code));
		}
	//	conn.close();
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return postalCodes;

	}
}
