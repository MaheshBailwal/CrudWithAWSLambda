package com.bailwal.crudwithlambda.requsetHandlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.bailwal.crudwithlambda.requsetHandlers.postalCode.*;

public class MainHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	@Override
	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

		context.getLogger().log("Input: " + input.toString());

		try {
			RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> handler = getHandler(
					input.getPath(), input.getHttpMethod());

			return handler.handleRequest(input, context);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
		responseEvent.setBody("Error Ocuured, please check mahesh bailwal ->" + input.toString());
		responseEvent.setStatusCode(500);
		return responseEvent;

	}

	private RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> getHandler(String path,
			String httpMethod) throws Exception {
		switch (path) {
		case "/postalcode":
			switch (httpMethod) {
			case "get":
				return new GetPostalCodes();

			case "post":
				return new AddPostalCode();

			default:
				throw new Exception("Not Found");
			}

		default:
			throw new Exception("Not Found");
		}
	}

}
