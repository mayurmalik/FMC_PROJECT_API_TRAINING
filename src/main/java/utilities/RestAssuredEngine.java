package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestAssuredEngine {

    private RequestSpecBuilder builder = new RequestSpecBuilder();

    /**
     * RestAssuredEngine constructor to pass the initial settings for the the following method
     * @param token
     */
    public RestAssuredEngine(String token) {
        builder.setBaseUri(ConfigFileReader.getproperty("BaseUrl"))
				.addHeader("Authorization", "Bearer " + token);
				
    }

    /**
     * Execute API Methods GET/POST/DELETE
     * @return ResponseOptions<Response>
     */
    public Response executeAPIMethods(String methodName,String serviceEndpoint,String payLoad,ContentType type) {
        RequestSpecification requestSpecification = builder.build();
        Response response=null;
        RequestSpecification request = RestAssured.given();
        //Setting up content type
        request.contentType(type);
//        request.contentType(ContentType.ANY);
        request.spec(requestSpecification);
        //request.baseUri("http://zippopotam.us");
        
        if(payLoad!=null && methodName.equalsIgnoreCase(APIConstant.ApiMethods.POST) || methodName.equalsIgnoreCase(APIConstant.ApiMethods.PUT) ) {
        	
        	request.body(payLoad);
        	
        	response =request.body(payLoad).post(serviceEndpoint).then().extract().response();
        	
        	return response;
        	
        }
        else {
        	
        	if(methodName.equalsIgnoreCase(APIConstant.ApiMethods.DELETE)) 
        		return request.delete(serviceEndpoint);
        	
                
//            else if(methodName.equalsIgnoreCase(APIConstant.ApiMethods.GET))
//                return request.get(serviceEndpoint);
        	
            else
                return request.get(serviceEndpoint);
        	
        	
        }
//        if(methodName.equalsIgnoreCase(APIConstant.ApiMethods.POST))
//            return request.post(serviceEndpoint);
//        else if(methodName.equalsIgnoreCase(APIConstant.ApiMethods.DELETE))
//            return request.delete(serviceEndpoint);
//        else if(methodName.equalsIgnoreCase(APIConstant.ApiMethods.GET))
//            return request.get(serviceEndpoint);
//        return null;
    }
    
//    /**
//     * ExecuteWithPathParams
//     * @return
//     */
//    public ResponseOptions<Response> executeMethod(String methodName, String serviceEndpoint, Map<String, String> headerMap,Map<String, String> queryPathMap, Map<String, Object> pathParamMap) {
//        if(!headerMap.isEmpty())
//        	builder.addHeaders(headerMap);
//        if(!queryPathMap.isEmpty())
//        	builder.addQueryParams(queryPathMap);
//        if(!pathParamMap.isEmpty())
//        	builder.addPathParams(pathParamMap);
//        //Set Body for POST Method
//       // if("POST".equals(methodName))
//        	//builder.setBody(object, mapper)
//        return executeAPIMethods(methodName,serviceEndpoint);
//    }
    /**
     * ExecuteWithPathParams
     * @return
     */
//    public ResponseOptions<Response> execute(String methodName, String serviceEndpoint) {
//        return executeAPIMethods(methodName,serviceEndpoint);
//    }
//
//
//    /**
//     * Executing API with query params being passed as the input of it
//     * @param queryPath
//     * @return Reponse
//     */
//    public ResponseOptions<Response> executeWithQueryParams(Map<String, String> queryPath) {
//        builder.addQueryParams(queryPath);
//        return executeAPIMethods();
//    }

    /**
     * ExecuteWithPathParams
     * @param pathParams
     * @return
     */
//    public ResponseOptions<Response> executeWithPathParams(Map<String, String> pathParams) {
//        builder.addPathParams(pathParams);
//        return executeAPIMethods();
//    }
    
   

    /**
     * ExecuteWithPathParamsAndBody
//   * @param pathParams
     * @param body
     * @return
     */
//    public ResponseOptions<Response> executeWithPathParamsAndBody(String methodName, String serviceEndpoint, Object body) {
//        if (body !=null)
//             builder.setBody(body);
////        builder.addPathParams(pathParams);
//        return executeAPIMethods(methodName,serviceEndpoint);
//    }



}
