package selenium;

import common.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BaseTest {
    public static WebDriver driver;

    public static String CMS_URL;
    public static String CMS_USER;
    public static String CMS_PASS;
    public static String ENTRY_URL;
    public static String ENTRY_USER;
    public static String API_URL;

    public Logger logWrite = Logger.getLogger("Logger activated");

    protected String targetBrowser = PropertyLoader.loadProperty("TARGET_BROWSER");

    protected String targetExecution = PropertyLoader.loadProperty("DEFAULT_EXECUTION");

    public static final String ENTRY_URL_STAGE = PropertyLoader.loadProperty("ENTRY_POINT_URL");
    public static final String ENTRY_USER_STAGE = PropertyLoader.loadProperty("ENTRY_USER");
    public static final String PAGE_API_STAGE = PropertyLoader.loadProperty("PAGE_API");
    public static final String CMS_PROD_URL = PropertyLoader.loadProperty("CMS_PROD_URL");
    public static final String CMS_STAGE_URL = PropertyLoader.loadProperty("CMS_BASE_URL");
    public static final String CMS_NOTES_LEAD_PAGE_URL = PropertyLoader.loadProperty("CMS_NOTES_LEAD_PAGE_URL");
    public static final String CMS_REMINDER_LEAD_PAGE_URL = PropertyLoader.loadProperty("CMS_REMINDER_LEAD_PAGE_URL");
    public static final String CMS_ADMIN_PANEL_URL = PropertyLoader.loadProperty("CMS_ADMIN_PANEL_URL");
    public static final String CMS_STAGE_USER = PropertyLoader.loadProperty("CMS_STAGE_USER");
    public static final String CMS_PROD_USER = PropertyLoader.loadProperty("CMS_PROD_USER");
    public static final String REMINDER_PARTICIPANT = PropertyLoader.loadProperty("REMINDER_PARTICIPANT");
    public static final String CMS_STAGE_USER_PASS = PropertyLoader.loadProperty("CMS_PASS");
    public static final String CMS_PROD_USER_PASS = PropertyLoader.loadProperty("CMS_PROD_PASS");
    public static final String LOGIN_USER = PropertyLoader.loadProperty("LOGIN_USER");
    public static final String DISPLAY_NAME = PropertyLoader.loadProperty("DISPLAY_NAME");
   // public static final String DISPLAY_NAME_NOYES = PropertyLoader.loadProperty("DISPLAY_NAME_NOTES");

    private final String env = System.getProperty("env");
            //"prod";






    public BaseTest() {
        // read execution from env
        String evnExecution = System.getenv("EXECUTION");
        if (evnExecution != null) {
            targetExecution = evnExecution;
        }
        getEnvironment();
    }

    public void getEnvironment(){
        if (env == null) {
            CMS_URL = CMS_STAGE_URL;
            CMS_USER = CMS_STAGE_USER;
            CMS_PASS = CMS_STAGE_USER_PASS;
            ENTRY_URL = ENTRY_URL_STAGE;
            ENTRY_USER = ENTRY_USER_STAGE;
            API_URL = PAGE_API_STAGE;
            logWrite.info("stage url is used: " + CMS_URL);

        } else {
            switch (env) {
                case "dev":
                    CMS_URL = CMS_STAGE_URL;
                    ENTRY_URL = ENTRY_URL_STAGE;
                    ENTRY_USER = ENTRY_USER_STAGE;
                    API_URL = PAGE_API_STAGE;
                    logWrite.info("dev url is used: " + CMS_URL);
                    break;
                case "stage":
                    CMS_URL = CMS_STAGE_URL;
                    CMS_USER = CMS_STAGE_USER;
                    CMS_PASS = CMS_STAGE_USER_PASS;
                    ENTRY_URL = ENTRY_URL_STAGE;
                    ENTRY_USER = ENTRY_USER_STAGE;
                    API_URL = PAGE_API_STAGE;
                    logWrite.info("Stage url is used: " + CMS_URL);
                    break;
                case "prod":
                    CMS_URL = CMS_PROD_URL;
                    CMS_USER = CMS_PROD_USER;
                    CMS_PASS = CMS_PROD_USER_PASS;
                    ENTRY_URL = ENTRY_URL_STAGE;
                    ENTRY_USER = ENTRY_USER_STAGE;
                    API_URL = PAGE_API_STAGE;
                    logWrite.info("Prod url is used: " + CMS_URL);
                    break;
            }

        }
    }

    public WebDriver firefoxDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("ssl-client-certificate-file", "src/main/resources/cert/ae129.p12");
        capabilities.setCapability("ssl-client-key-passphrase", "62174ad326d84");
        if (targetExecution.equals("local")) {
            return fireFoxDriver();
        } else if (targetExecution.equals("remote")) {
            return getRemoteWebDriver(capabilities);
        } else {
            throw new IllegalStateException("Execution was not found" + targetExecution);
        }
    }

    public WebDriver chromeDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//        capabilities.setCapability("ssl-client-certificate-file", "src/main/resources/cert/ae129.p12");
//        capabilities.setCapability("ssl-client-key-passphrase", "62174ad326d84");
        capabilities.setBrowserName("chrome");
        if (targetExecution.equals("local")) {
            return localChromeDriver();
        } else if (targetExecution.equals("remote")) {
            return getRemoteWebDriver(capabilities);
        } else {
            throw new IllegalStateException("Execution was not found" + targetExecution);
        }
    }

    public WebDriver fireFoxDriver() {
        System.setProperty("webdriver.gecko.driver", PropertyLoader.loadProperty("DRIVER_PATH"));
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver localChromeDriver() {
        System.setProperty("webdriver.chrome.driver", PropertyLoader.loadProperty("DRIVER_PATH"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver getDriver() {
        try {
            if (targetBrowser.equals("firefox")) {
                return this.firefoxDriver();
            } else if (targetBrowser.equals("chrome")) {
                return this.chromeDriver();
            } else {
                throw new IllegalStateException("Target browser not supported: " + targetBrowser);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private WebDriver getRemoteWebDriver(DesiredCapabilities capabilities) throws MalformedURLException {
        String baseUrl = PropertyLoader.loadProperty("DEFAULT_SE_HUB_BASE_URL");
        String evnBaseUrl = System.getenv("SE_HUB_BASE_URL");
        if (evnBaseUrl != null) {
            baseUrl = evnBaseUrl;
        }
        return new RemoteWebDriver(
                new URL(new URL(baseUrl).toExternalForm() + "/wd/hub"),
                capabilities
        );
    }

    protected void implicitlyWait(long ms) {
        driver.manage().timeouts().implicitlyWait(ms, TimeUnit.MILLISECONDS);
    }
}

