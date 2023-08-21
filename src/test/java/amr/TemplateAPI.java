package amr;

import common.RestUtilities;
import constants.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.BaseTest;

import java.util.HashMap;

public class TemplateAPI extends BaseTest{

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;


    @BeforeClass(alwaysRun = true)
    public void setup() {
        driver = getDriver();
        getEnvironment();
    }


    //TODO: Api test example
    @Test(groups = {"Regression"})
    public void Template (){
        //TODO:Create object
        HashMap<String,Object> sapUser = new HashMap<>();
        sapUser.put(Constants.GLOBAL_PERSON_UID,"20000966");
        sapUser.put(Constants.USER_ID, "30000401");
        sapUser.put(Constants.PERSON_STATE, Constants.EMPLOYEE);
        sapUser.put(Constants.AUTH_GROUP, Constants.EMC);
        sapUser.put(Constants.EVENT_DATE, "2022-04-20T00:00:00.000");
        sapUser.put(Constants.LOCATION, Constants.LOCATION_RIX);
        sapUser.put(Constants.POSITION_CODE, "10876");
        sapUser.put(Constants.POSITION_TITLE, "");
        sapUser.put(Constants.PREV_POSITION_CODE, null);
        sapUser.put(Constants.NICKNAME, "Rimpy");
        sapUser.put(Constants.TEAM, Constants.TEAM_BLAZERS);
        sapUser.put(Constants.BUSINESS_EMAIL, "Rimpy.T@asaptickets.com");
        sapUser.put(Constants.POSITION_ENTRY_DATE, "2022-04-20T00:00:00.000");

        //TODO: Create request example and set query params
        requestSpec = RestUtilities.getRequestSpecification(CMS_URL,Path.SERVICE_PATH);
        requestSpec = RestUtilities.createQueryParam(requestSpec, Constants.CREDENTIALS, Auth.TECHNICAL_USER_CREDENTIALS);
        requestSpec = RestUtilities.createQueryParam(requestSpec, Constants.SERVICE_NAME, ServiceName.SERVICE_NAME_SAP);
        requestSpec = RestUtilities.createQueryParam(requestSpec, Constants.FUNCTION_NAME, FunctionName.CHANGE_POSITION);
        requestSpec = RestUtilities.createQueryParam(requestSpec, Constants.PARAMS, sapUser);

        //TODO:assert response code
        responseSpec = RestUtilities.getResponseSpecification(200);
        //TODO:Send request
        Response response = RestUtilities.getResponse(requestSpec, responseSpec, "post", "","");

        //TODO:Asseert response
        Assert.assertEquals(response.path("status"),"OK");
        Assert.assertNotNull(response.path("result.amrId.amrUuid"));
        Assert.assertNotNull(response.path("result.amrId.amrId"));

        //TODO: Selenium test, go to AMR, check request

    }

    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }

}
