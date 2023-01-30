import static org.junit.Assert.*;

import java.util.List;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.amazonaws.services.lambda.runtime.events.CloudFrontEvent.Request;
import com.bailwal.crudwithlambda.dtos.AddPostalCodeDTO;
import com.bailwal.crudwithlambda.dtos.PostalCodeDetailDTO;
import com.bailwal.crudwithlambda.entities.PostalCodeEntity;
import com.bailwal.crudwithlambda.repositories.PostalCodeRepo;
import com.bailwal.crudwithlambda.services.PostalCodeService2;
import com.google.gson.Gson;
import com.bailwal.crudwithlambda.requsetHandlers.postalCode.*;

import org.junit.Test;

public class testme {

	@Test
	public void test() {
		PostalCodeService2 service = new PostalCodeService2();

		// PostalCodeRepo service = new PostalCodeRepo();
		List<PostalCodeDetailDTO> codes = null;
		try {
			codes = service.GetAllPostalCodes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("count->" + codes.size());
		assertNotNull(codes);
	}

	@Test
	public void test12() {
		GetPostalCodes handler = new GetPostalCodes();
		APIGatewayProxyResponseEvent res = handler.handleRequest(null, null);

		System.out.println("count->" + res.getBody());
		assertNotNull(res);
	}

	@Test
	public void AddPostalCode() {
		PostalCodeService2 service = new PostalCodeService2();
		int id = 0;
		try {
			id = service.AddPostalCode(new AddPostalCodeDTO("test"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotEquals(0, id);
	}

	@Test
	public void AddPostalCodeHandler() {
		AddPostalCode handler = new AddPostalCode();

		APIGatewayProxyRequestEvent resuest = new APIGatewayProxyRequestEvent();
		resuest.setBody(new Gson().toJson(new AddPostalCodeDTO("TestCaseCode")));

		APIGatewayProxyResponseEvent res = handler.handleRequest(resuest, null);

		int id= Integer.parseInt(res.getBody());
		
		assertNotEquals(0, id);
	}

}
