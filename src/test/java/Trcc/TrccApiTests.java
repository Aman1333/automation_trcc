package Trcc;

import common.RestUtilities;
import constants.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import selenium.BaseClass;
import selenium.BaseTest;
import selenium.cms.CmsHeaderBarObject;
import selenium.cms.CmsLoginPageObject;
import selenium.entrypoint.EntryPointPageObject;
import selenium.notes.NotesAppPageObject;
import selenium.trcc.TrccPanelPageObject;

import java.util.*;

public class TrccApiTests extends BaseTest {

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    RequestSpecification requestSpecAddQuery;
    Response response;

    BaseClass baseClass;
    CmsLoginPageObject cmsLoginPageObject;
    CmsHeaderBarObject cmsHeaderBarObject;
    TrccPanelPageObject trccPanelPageObject;
    NotesAppPageObject notesAppPageObject;
    EntryPointPageObject entryPointPageObject;
    String sessionKey;
    String accessToken;


    @BeforeClass(alwaysRun = true)
    public void getSessionKey() {
        driver = getDriver();
        cmsLoginPageObject = new CmsLoginPageObject(driver);
        cmsHeaderBarObject = new CmsHeaderBarObject(driver);
        notesAppPageObject = new NotesAppPageObject(driver);
        trccPanelPageObject = new TrccPanelPageObject(driver);
        entryPointPageObject = new EntryPointPageObject(driver);
        baseClass = new BaseClass(driver);

        logWrite.info("Test: Login to CMS with user " + ENTRY_URL);
        try {
            baseClass.openPage(ENTRY_URL);
            logWrite.info("Attempting to login using test user credentials");
            cmsLoginPageObject.fillLogin(ENTRY_USER)
                    .fillPassword(CMS_STAGE_USER_PASS)
                    .submitCredentials();
        } catch (NoSuchElementException e) {        }

        sessionKey = entryPointPageObject.getSessionKey();
        logWrite.info(" Session key==>" + sessionKey );
    }

    //get Access token
    @Test(priority = 1)
    public void createChildSessionTest()
    {
        logWrite.info(" Session key==>" + sessionKey );
        //TODO:Create object
        HashMap<String, String> key = new HashMap<>();
        key.put("sessionKey", sessionKey);
        //TODO: Create request example and set query params
        requestSpec = RestUtilities.getRequestSpecification(API_URL, Path.PAGE_API_PATH);
        //TODO:assert response code
        responseSpec = RestUtilities.getResponseSpecification(201);
        //TODO:Send request
         response = RestUtilities.getResponse(requestSpec,responseSpec,"post","",key);
        //TODO:Asseert response
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"201");
       // Assert.assertEquals(response.path("data.authData.userName"),"Aman Mehra");
        accessToken = response.path("data.authData.accessToken");
    }

    @Test(dependsOnMethods = "createChildSessionTest")
    public void getCommonTypesTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_COM_TYPES, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
         response = RestUtilities.getResponse(requestSpec, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.commTypes.gmail"),"Gmail");
        Assert.assertEquals(response.path("data.commTypes.chat"),"Chat");
        Assert.assertEquals(response.path("data.commTypes.whatsapp"),"Whatsapp");
        Assert.assertEquals(response.path("data.commTypes.facebook"),"Facebook");
        Assert.assertEquals(response.path("data.commTypes.reminder"),"Reminder");
        Assert.assertEquals(response.path("data.commTypes.sms"),"SMS");
    }

    @Test(dependsOnMethods = "createChildSessionTest")
    public void getClientsTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_GET_CLIENTS, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
         response = RestUtilities.getResponse(requestSpec, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
    }
    @Test(dependsOnMethods = "getClientsTest")
    public void searchClientListTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_SEARCH_CLIENTS, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"nameTerm","TES");
         response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
    }
    @Test(dependsOnMethods = "getClientsTest")
    public void getDirectionsTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_GET_DIRECTIONS, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        Response response = RestUtilities.getResponse(requestSpec, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.directions.all"),"All");
        Assert.assertEquals(response.path("data.directions.incoming"),"Incoming");
        Assert.assertEquals(response.path("data.directions.outgoing"),"Outgoing");
        Assert.assertEquals(response.path("data.domains.all"),"All");
        Assert.assertEquals(response.path("data.domains.internal"),"Internal");
        Assert.assertEquals(response.path("data.domains.external"),"External");
    }
    /*@Test(dependsOnMethods = "getClientsTest")
    public void getCallDataList()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_COMM_DATA_LIST, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        //ArrayList<Object> list = new ArrayList<Object>();
        //list.add("call");
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"commType","call");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
           // logWrite.info("Object name==>"+list);
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
       Assert.assertEquals(response.path("data.list[0].type"),"call");
    }*/

    @Test(dependsOnMethods = "getClientsTest")
    public void getGmailDataListTest()
    {
        /*
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_COMM_DATA_LIST, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"commType","gmail");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals((response.path("data.list[1].type")),"gmail");
        Assert.assertEquals(String.valueOf(response.path("data.list[1].commWay")),"incoming");
        Assert.assertEquals(String.valueOf(response.path("data.list[1].isInternal")),"false");
*/


    requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_COMM_DATA_LIST, accessToken);
    responseSpec = RestUtilities.getResponseSpecification(200);
    requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"commType","gmail");
    response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
    Assert.assertEquals(response.path("status"), "success");
    Assert.assertEquals(response.path("statusCode").toString(),"200");

    Optional<String> str = Optional.empty();
        String emailValue = response.path("data.list[0].commWay").toString();

    str = Optional.ofNullable(response.path("data.list[0].type"));
    if(str.isPresent())
      Assert.assertEquals(response.path("data.list[0].type").toString(),"gmail");
    else
        Assert.fail("gmail not present");

    str = Optional.ofNullable(response.path("data.list[0].commWay"));
    if(str.isPresent() && emailValue.equals("incoming"))
        Assert.assertEquals(response.path("data.list[0].commWay").toString(),"incoming");
    else if (str.isPresent() && emailValue.equals("outgoing"))
        Assert.assertEquals(response.path("data.list[0].commWay").toString(),"outgoing");
    else
        Assert.fail("incoming not present");
    }
    @Test(dependsOnMethods = "getClientsTest")
    public void getReminderDataListTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_COMM_DATA_LIST, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"commType","reminder");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.list[1].type").toString(),"reminder");
        Assert.assertEquals(response.path("data.list[1].commWay").toString(),"incoming");
        Assert.assertEquals(response.path("data.list[1].isInternal").toString(),"true");
    }

    @Test(dependsOnMethods = "getClientsTest")
    public void getTeamListAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_TEAM_LIST_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.teams[0].id").toString(),"1");
        Assert.assertEquals(response.path("data.teams[0].teamName").toString(),"Business Support");

    }
    @Test(dependsOnMethods = "getClientsTest")
    public void searchTeamListAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_TEAM_LIST_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"search","Dream Team");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.teams[0].id").toString(),"124");
        Assert.assertEquals(response.path("data.teams[0].teamName").toString(),"Dream Team");
    }
    /*@Test(dependsOnMethods = "getDirectionsTest")
    public void getProjectListAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_PROJECT_LIST_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.projects[0].id").toString(),"1");
        Assert.assertEquals(response.path("data.projects[0].name").toString(),"SCM");
    }*/
   /* @Test(dependsOnMethods = "getProjectListAdminPanelTest")
    public void searchProjectListAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_PROJECT_LIST_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"search","CMS_GMAIL");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.projects[0].id").toString(),"56");
        Assert.assertEquals(response.path("data.projects[0].name").toString(),"CMS_GMAIL");
    }*/
    @Test(dependsOnMethods = "getDirectionsTest")
    public void getAgentListAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_AGENT_LIST_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.agents[0].id").toString(),"1");
        Assert.assertEquals(response.path("data.agents[0].name").toString(),"Alex Weinstein");
    }
    @Test(dependsOnMethods = "getAgentListAdminPanelTest")
    public void searchAgentListAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_AGENT_LIST_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"search","derums");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.agents[0].id").toString(),"134");
        Assert.assertEquals(response.path("data.agents[0].agentName").toString(),"Derums");
    }
    @Test(dependsOnMethods = "getDirectionsTest")
    public void getRedisEventAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_REDIS_LIST_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.redisEvents[0].id").toString(),"1");
        Assert.assertEquals(response.path("data.redisEvents[0].eventName").toString(),"ReminderCreated");
    }
    @Test(dependsOnMethods = "getRedisEventAdminPanelTest")
    public void searchRedisEventAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_REDIS_LIST_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"search","ReminderUpdated");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.redisEvents[0].id").toString(),"2");
        Assert.assertEquals(response.path("data.redisEvents[0].eventName").toString(),"ReminderUpdated");
    }
    @Test(dependsOnMethods = "createChildSessionTest")
    public void getCompanyAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_COMPANY_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.company[0].id").toString(),"1");
        Assert.assertEquals(response.path("data.company[0].name").toString(),"SLT");
    }
    @Test(dependsOnMethods = "createChildSessionTest")
    public void searchCompanyAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_COMPANY_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"search","oojo");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.company[0].id").toString(),"12");
        Assert.assertEquals(response.path("data.company[0].name").toString(),"Oojo");
    }
    @Test(dependsOnMethods = "createChildSessionTest")
    public void getMailboxAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_MAILBOX_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.mailbox[0].id").toString(),"1");
        Assert.assertEquals(response.path("data.mailbox[0].email").toString(),"ale*@itncorp.com");
    }
    @Test(dependsOnMethods = "createChildSessionTest")
    public void searchMailboxAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_MAILBOX_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"search","mar*******@itncorp.com");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.mailbox[0].id").toString(),"6");
        Assert.assertEquals(response.path("data.mailbox[0].uid").toString(),"K3BUS5M91EJ");
    }
    @Test(dependsOnMethods = "createChildSessionTest")
    public void getEmailDomainAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_EMAIL_DOMAIN_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.domain[0].id").toString(),"1");
        Assert.assertEquals(response.path("data.domain[0].name").toString(),"asaptickets.com");
    }
    @Test(dependsOnMethods = "createChildSessionTest")
    public void searchEmailDomainAdminPanelTest()
    {
        requestSpec = RestUtilities.getRequestSpecificationToken(API_URL, Path.PAGE_API_EMAIL_DOMAIN_ADMIN, accessToken);
        responseSpec = RestUtilities.getResponseSpecification(200);
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"page","1");
        requestSpecAddQuery = RestUtilities.createQueryParam(requestSpec,"search","ctncorp.com");
        response = RestUtilities.getResponse(requestSpecAddQuery, responseSpec, "get", "", "");
        Assert.assertEquals(response.path("status"), "success");
        Assert.assertEquals(response.path("statusCode").toString(),"200");
        Assert.assertEquals(response.path("data.domain[0].id").toString(),"5");
        Assert.assertEquals(response.path("data.domain[0].name").toString(),"ctncorp.com");
        Assert.assertEquals(response.path("data.domain[0].domainId").toString(),"10");
    }
    @AfterMethod(alwaysRun = true)
    public void quit() {
        driver.quit();
    }
}
