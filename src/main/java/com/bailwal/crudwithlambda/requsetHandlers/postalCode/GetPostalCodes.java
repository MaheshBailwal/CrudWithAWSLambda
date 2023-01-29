package com.bailwal.crudwithlambda.requsetHandlers.postalCode;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
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
