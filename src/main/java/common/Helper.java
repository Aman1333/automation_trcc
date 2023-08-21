package common;

import constants.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import selenium.BaseTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Helper extends BaseTest {

    public static String generateClientEmail(String name, String surname) {
        return name + surname + "@dynatech.lv";
    }

    public static String getRandomPhoneNumber(){
        int n = 8;
        String AlphaNumericString = "0123456789";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return "+4423" + sb.toString();
    }

    public static String getRandomInt(int n){
        //int n = 6;
        String AlphaNumericString = "01234567";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    public static String getRandomName(){
        String[] name = TestData.name;
        int getLength = name.length;
        Random rn = new Random();
        int max = rn.nextInt(getLength);
        return name[max];
    }

    public static String getRandomNickName(){
        String[] name = TestData.surname;
        int getLength = name.length;
        Random rn = new Random();
        int max = rn.nextInt(getLength);
        return name[max] + "123";
    }

    public static String getRandomLastName(){
        String[] name = TestData.surname;
        int getLength = name.length;
        Random rn = new Random();
        int max = rn.nextInt(getLength);
        return name[max];
    }

    public Boolean datePicker(String object, String data) {
        try {
            WebElement dateWidget = driver.findElement(By.xpath(object));

            List<WebElement> rows = dateWidget.findElements(By.tagName("tr"));
            List<WebElement> columns = dateWidget.findElements(By.tagName("td"));

            for (WebElement cell : columns) {
                if (cell.getText().equals(data)) {
                    cell.findElement(By.linkText(data)).click();
                    break;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String getDateWithSpecificMonths(int months, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,months);
        String newDate = sdf.format(cal.getTime());
        return newDate;
    }

    public static String getDateWithSpecificDaysInFuture(int days, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,days);
        String newDate = sdf.format(cal.getTime());
        return newDate;
    }


}
