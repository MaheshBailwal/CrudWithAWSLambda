package com.bailwal.crudwithlambda.requsetHandlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.bailwal.crudwithlambda.exceptions.HandlerNotFoundException;
import com.bailwal.crudwithlambda.requsetHandlers.postalCode.*;

public class MainHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

		context.getLogger().log("Input: " + input.toString());
		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();

		try {
			RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> handler = getHandler(
					input.getPath(), input.getHttpMethod());

			return handler.handleRequest(input, context);

		} catch (HandlerNotFoundException e) {
			responseEvent.setBody("Handler Not Found, please check mahesh bailwal ->" + input.toString());
			responseEvent.setStatusCode(404);
			return responseEvent;
		}

		catch (Exception e) {
			e.printStackTrace();
			responseEvent.setBody("Error Ocuured, please check mahesh bailwal ->" + input.toString());
			responseEvent.setStatusCode(500);
			return responseEvent;
		}
		
	}

	private RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> getHandler(String path,
			String httpMethod) throws HandlerNotFoundException {
		switch (path) {
		case "/postalcode":
			switch (httpMethod) {
			case "GET":
				return new GetPostalCodes();
			case "POST":
				return new AddPostalCode();
			}
		}
		
		throw new HandlerNotFoundException("Not Found");
	}

}
