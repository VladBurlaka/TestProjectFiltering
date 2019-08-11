package com.iopscience.qa.base;

import com.iopscience.qa.util.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;

    public TestBase(){
        try{
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("C:\\Users\\Vlad\\IdeaProjects\\" +
                    "FiltrationFunctionalityProject\\src\\main\\sources\\application.properties"));
            prop.load(ip);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void init(){
        String browserName = prop.getProperty("browser");

        if(browserName.equals("firefox")){
            System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\geckodriver.exe");
            WebDriver driver  = new FirefoxDriver();
        } else {
            System.out.println("Wrong driver name");
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.get(prop.getProperty("url"));
    }

    public static void tearDown(){
        driver.quit();
    }

}
