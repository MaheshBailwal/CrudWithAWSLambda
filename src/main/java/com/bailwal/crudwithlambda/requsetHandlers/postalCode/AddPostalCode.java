package com.bailwal.crudwithlambda.requsetHandlers.postalCode;

import com.bailwal.crudwithlambda.dtos.AddPostalCodeDTO;
import com.bailwal.crudwithlambda.services.PostalCodeService2;
import com.google.gson.Gson;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

public class AddPostalCode implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

		PostalCodeService2 service = new PostalCodeService2();
		AddPostalCodeDTO dto = new Gson().fromJson(input.getBody(), AddPostalCodeDTO.class);
		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();

		try {
			int id = service.AddPostalCode(dto);
			responseEvent.setBody("" + id);
			responseEvent.setStatusCode(200);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responseEvent.setBody(e.toString());
			responseEvent.setStatusCode(500);
		}

		return responseEvent;
	}

}
