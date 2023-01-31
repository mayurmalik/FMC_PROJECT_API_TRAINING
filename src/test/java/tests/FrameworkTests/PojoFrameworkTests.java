package tests.FrameworkTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.ExcelUtility;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PojoFrameworkTests {
   // FriendsHelper friendshelper = new FriendsHelper();
    SoftAssert softAssert = new SoftAssert();

    @Test(dataProvider = "getApiEndPointData2", dataProviderClass = ExcelUtility.class, testName = "addFriends_FriendsTest")
    public void addFriends_FriendsTest(String methodName, String serviceEndpoint, String payload, int statusCode) {
//        //FriendWithLombok payloadFriends = friendshelper.getFriendsPayload(payload);
//        Response response = (Response) friendshelper.addFriends(methodName, serviceEndpoint, payloadFriends);
//        softAssert.assertEquals(response.getStatusCode(), statusCode, "Status code failed");
//        //softAssert.assertEquals(response.as(FriendWithLombok.class).getId(), payloadFriends.getId(), "Incorrect friend Id");
//        softAssert.assertAll();
    }

}
