package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class User_Test {

	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setUpData() {
		
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(3,6));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
	}
	
	@Test(priority=1)
	public void testPostUser() {
		
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(),200);
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		
		Response res =UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
	}
	
	@Test(priority=3)
	public void testUpdateUserByName() {
		
		// Update Data using Payload
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response res= UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		res.then().log().body();
		
	
		// Checking data after Update
		

		Response responseAfterUpdate =UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
	}
	
	@Test(priority=4)
	public void testDeleteUser() {
		
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	

}
