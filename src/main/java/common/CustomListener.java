package common;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import selenium.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class CustomListener extends BaseTest implements ITestListener {

    public void onTestFailure(ITestResult result) {

        Date currentDate = new Date();
        String screenshotFileName = result.getName()+currentDate.toString().replace(" ","-").replace(":","-");
        try {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshotFile, new File("target/screenshots/" + screenshotFileName + ".png"));
            System.out.println("Test failed. Screenshot saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
