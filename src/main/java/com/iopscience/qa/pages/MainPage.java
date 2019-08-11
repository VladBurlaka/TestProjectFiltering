package com.iopscience.qa.pages;

import com.iopscience.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainPage extends TestBase {

    //Page Factory approach
    @FindBy(xpath = "//a[@class=\"header-logo wd-header-graphic\"]")
    WebElement mainButton;

    @FindBy(xpath = "//div[@class=\"nav-books nav-item wd-nav-books\"]/parent::nav[@id=\"sidr\"]")
    WebElement booksButton;



    public MainPage(){

        //Initializing all necessary provided elements on the main page
        PageFactory.initElements(driver,this);
    }



    public void checkElemList(List<WebElement> elems){

        //Set implicit wait to 5 sec to page to be loaded
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Validate if list of web elements are presented on the web page
        List<WebElement> elemList = elems;
        for (int i = 0; i < elemList.size(); i++) {
            if (elemList.get(i) != null) {
                //If list size is non-zero, element is present
                System.out.println("Element is present on the web page");
            } else {
                //Else if size is 0, then element is not present
                System.out.println("Element is not present on the web page");
            }
        }
    }



    public void checkElem(WebElement elem){

        //Set implicit wait to 5 sec to page to be loaded
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Validate if web element is presented on the webpage
        if(elem.getSize() != null){
            //If list size is non-zero, element is present
            System.out.println("Element is present on the web page");
        } else{
            //Else if size is 0, then element is not present
            System.out.println("Element is not present on the web page");
        }
    }



    public void click(WebElement elem){

        //Submit element method
        elem.click();
    }



    public void selectFromDropDown(WebElement elemPath, String value){

        //Select from drop down lost method
        Select dropDownList = new Select(elemPath);
        dropDownList.selectByValue(value);
    }

}
