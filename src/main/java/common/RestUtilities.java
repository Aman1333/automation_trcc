package common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestUtilities {

    private static RequestSpecBuilder REQUEST_BUILDER;
    private static RequestSpecification REQUEST_SPECIFICATION;

    private static ResponseSpecBuilder RESPONSE_BUILDER;
    private static ResponseSpecification RESPONSE_SPECIFICATION;

    public static RequestSpecification getRequestSpecification(String baseUrl, String basePath) {
        REQUEST_BUILDER = new RequestSpecBuilder();
        REQUEST_BUILDER.setBaseUri(baseUrl);
        REQUEST_BUILDER.setBasePath(basePath);
        REQUEST_BUILDER.setRelaxedHTTPSValidation();
        REQUEST_BUILDER.addHeader("Accept", "*/*");
        REQUEST_BUILDER.setContentType(ContentType.JSON);
        REQUEST_BUILDER.log(LogDetail.ALL);
        REQUEST_SPECIFICATION = REQUEST_BUILDER.build();
        return REQUEST_SPECIFICATION;
    }

    public static RequestSpecification getRequestSpecificationToken(String baseUrl, String basePath, String token) {
        REQUEST_BUILDER = new RequestSpecBuilder();
        REQUEST_BUILDER.setBaseUri(baseUrl);
        REQUEST_BUILDER.setBasePath(basePath);
        REQUEST_BUILDER.addHeader("Authorization", "Bearer " + token);
        REQUEST_BUILDER.setRelaxedHTTPSValidation();
        REQUEST_BUILDER.addHeader("Accept", "*/*");
        REQUEST_BUILDER.setContentType(ContentType.JSON);
        REQUEST_BUILDER.log(LogDetail.ALL);
        REQUEST_SPECIFICATION = REQUEST_BUILDER.build();
        return REQUEST_SPECIFICATION;
    }

    public static ResponseSpecification getResponseSpecification(int status) {
        RESPONSE_BUILDER = new ResponseSpecBuilder();
        RESPONSE_BUILDER.expectStatusCode(status);
        RESPONSE_BUILDER.log(LogDetail.ALL);
        RESPONSE_SPECIFICATION = RESPONSE_BUILDER.build();
        return RESPONSE_SPECIFICATION;
    }

    public static Response

    getResponse(RequestSpecification reqSpec,
                                       ResponseSpecification respSpec,
                                       String type,
                                       String endpoint,
                                       Object post) {
        Response response = null;
        if (type.equalsIgnoreCase("get")) {
            response = given().spec(reqSpec).get(endpoint);
        } else if (type.equalsIgnoreCase("post")) {
            response = given().spec(reqSpec).body(post).post();
        } else if (type.equalsIgnoreCase("put")) {
            response = given().spec(reqSpec).put(endpoint);
        } else if (type.equalsIgnoreCase("delete")) {
            response = given().spec(reqSpec).delete(endpoint);
        } else if (type.equalsIgnoreCase("options")) {
            response = given().spec(reqSpec).options(endpoint);
        } else {
            System.out.println("Type is not supported");
        }
        assert response != null;
        response.then().spec(respSpec).extract().response();
        return response;
    }

    public static RequestSpecification createQueryParam(RequestSpecification rspec,
                                                        String param, String value) {
        return rspec.queryParam(param, value);
    }

    public static RequestSpecification createQueryParam(RequestSpecification rspec,
                                                        String param, Object json) {
        return rspec.queryParam(param, json);
    }

    public static RequestSpecification createQueryParams(RequestSpecification rspec,
                                                         String params, Map<String, Object> queryMap) {
        return rspec.queryParams(queryMap);
    }

}
