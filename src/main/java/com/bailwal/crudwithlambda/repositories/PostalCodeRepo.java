package com.bailwal.crudwithlambda.repositories;

import java.util.*;

import com.bailwal.crudwithlambda.entities.PostalCodeEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostalCodeRepo {

	public PostalCodeRepo() {
		// TODO Auto-generated constructor stub
	}

	public List<PostalCodeEntity> GetAllPostalCodes() throws Exception {

		List<PostalCodeEntity> postalCodes = new ArrayList<PostalCodeEntity>();
		Connection conn = getConnection();
		PreparedStatement preparedStatement = conn.prepareStatement("Select * from postalcode");
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("id");
			String code = rs.getString("code");
			postalCodes.add(new PostalCodeEntity(id, code));
		}
		conn.close();

		return postalCodes;
	}

	public int Add(PostalCodeEntity postalCodeEntity) throws Exception {

		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		String sql = "INSERT INTO postalcode (code) " + "VALUES ('" + postalCodeEntity.getPostalCode()
				+ "') RETURNING id;";

		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int newId = rs.getInt("id");
		stmt.close();
		conn.close();

		return newId;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://free-instance.c3enhs8xm5kf.us-east-1.rds.amazonaws.com:5432/purolator", "postgres",
				"password123");
		return conn;
	}
}
