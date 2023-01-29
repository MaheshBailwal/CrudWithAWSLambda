package com.bailwal.crudwithlambda.requsetHandlers.postalCode;

import java.util.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.bailwal.crudwithlambda.dtos.PostalCodeDetailDTO;
import com.bailwal.crudwithlambda.services.PostalCodeService2;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.hibernate.result.Output;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetPostalCodes implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

		String json = "empty json mahesh";
		PostalCodeService2 service = new PostalCodeService2();
		List<PostalCodeDetailDTO> codes = new ArrayList<>();
		try {
			codes = service.GetAllPostalCodes();
			// ObjectMapper mapper = new ObjectMapper();
			// json = mapper.writeValueAsString(codes);
			//ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			//json = ow.writeValueAsString(codes);

			json = new Gson().toJson(codes);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		responseEvent.setBody(json + "count:" + codes.size());
		responseEvent.setStatusCode(200);
		return responseEvent;
	}

	// @Override
	public APIGatewayProxyResponseEvent handleRequest12(APIGatewayProxyRequestEvent input, Context context) {
		System.out.println("Hello World mahesh!");
		String output = "output->";
		try (Connection conn = DriverManager.getConnection(
				"jdbc:postgresql://free-instance.c3enhs8xm5kf.us-east-1.rds.amazonaws.com:5432/purolator", "postgres",
				"password123")) {
			if (conn != null) {
				System.out.println("Connected to the database!");
			} else {
				System.out.println("Failed to make connection!");
			}
			PreparedStatement preparedStatement = conn.prepareStatement("Select * from postalcode");
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				output += "code:" + code;
				System.out.println(id + "," + code);
			}
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		responseEvent.setBody("GetPostalCode Invoked :" + output);
		responseEvent.setStatusCode(200);
		return responseEvent;
	}

}
