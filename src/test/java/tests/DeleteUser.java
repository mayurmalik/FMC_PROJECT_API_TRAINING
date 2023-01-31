package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.restassured.response.Response;
import modules.DeleteUserHelper;
import utilities.APIConstant;
import utilities.AuthenticationToken;
import utilities.JSONPathExtractor;
import utilities.RestAssuredEngine;

public class DeleteUser extends AuthenticationToken {
	public RestAssuredEngine engine;
	SoftAssert softAssert = new SoftAssert();

	@BeforeClass
	public void preRequisite() {

		engine = new RestAssuredEngine(getAuthenticationToken());

	}

	@Test
	public void deleteUser() throws JsonProcessingException {

		Response userDeleteResponse = DeleteUserHelper.deleteuser(APIConstant.ApiMethods.DELETE, engine);

		// validating the 200 status code after user deletion

		softAssert.assertEquals(userDeleteResponse.getStatusCode(), 200, "user login failed");

		// trying to login with same user again to confirm the deletion

		Response userLoginResponse = DeleteUserHelper.userLoginValidationAfterDelete(APIConstant.ApiMethods.POST,
				engine);

		softAssert.assertEquals(userLoginResponse.getStatusCode(), 401, "user login falied after user deletion");

		String message = JSONPathExtractor.extractor("errors.message", userLoginResponse);

		softAssert.assertEquals("[Authentication Failure]", message);

	}

	// Negative flow for delete user , here we are trying to delete the incorrect
	// user and verifying the response

	@Test
	public void deleteUserNegativeFlow() throws JsonProcessingException {

		Response userDeleteResponse = DeleteUserHelper.deleteUserNegativeFlow(APIConstant.ApiMethods.DELETE, engine);
		softAssert.assertEquals(userDeleteResponse.getStatusCode(), 200, "user deletion succesfull with invalid data");

	}

}
