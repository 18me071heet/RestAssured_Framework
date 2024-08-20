package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.uttilities.DataProviders;
import io.restassured.response.Response;

public class DDtests {
     
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userid,String userName,String firstname,String lastName,String userEmail,String password,String phoneNo) {
		
		
		User userpayload = new User();
		
		userpayload.setId(Integer.parseInt(userid));
		userpayload.setUsername(userName);
		userpayload.setFirstName(firstname);
		userpayload.setLastName(lastName);
		userpayload.setEmail(userEmail);
		userpayload.setPassword(password);
		userpayload.setPhone(phoneNo);
		
		Response response = UserEndPoints.createUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		

	}
	
	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName) {
		
	
		
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);


	}
}
