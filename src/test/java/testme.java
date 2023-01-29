import static org.junit.Assert.*;

import java.util.List;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.bailwal.crudwithlambda.dtos.AddPostalCodeDTO;
import com.bailwal.crudwithlambda.dtos.PostalCodeDetailDTO;
import com.bailwal.crudwithlambda.entities.PostalCodeEntity;
import com.bailwal.crudwithlambda.repositories.PostalCodeRepo;
import com.bailwal.crudwithlambda.services.PostalCodeService2;
import com.bailwal.crudwithlambda.requsetHandlers.postalCode.*;

import org.junit.Test;

public class testme {

	@Test
	public void test() {
		PostalCodeService2 service = new PostalCodeService2();
		
	//	PostalCodeRepo service = new PostalCodeRepo();
		List<PostalCodeDetailDTO> codes = null;
		try {
			codes =	service.GetAllPostalCodes();
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

}
