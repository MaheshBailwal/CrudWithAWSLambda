package com.bailwal.crudwithlambda.requsetHandlers.postalCode;

import java.util.*;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.bailwal.crudwithlambda.dtos.PostalCodeDetailDTO;
import com.bailwal.crudwithlambda.services.PostalCodeService2;
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
			json = new Gson().toJson(codes);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		responseEvent.setBody(json);
		responseEvent.setStatusCode(200);
		return responseEvent;
	}


}
