package com.iopscience.qa.pages;

import com.iopscience.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class BooksPage extends TestBase {

    //Webpage constants
    public static final WebElement LATEST_FILTER = driver.findElement(By.name("latest"));

    public static final WebElement OLDEST_FILTER = driver.findElement(By.name("oldest"));

    public static final WebElement ALPHABETIC_FILTER = driver.findElement(By.name("az"));

    public static final WebElement GO_BUTTON = driver.findElement(By.name("submit"));

    public static final WebElement YEAR_FILTER = driver.findElement(By.name("year"));

    //Page Factory approach sample
    @FindBy(name = "book-subject")
    WebElement subjectFilter;

    @FindBy(name = "collection")
    WebElement collectionFilter;

    @FindBy(xpath = "//a[@class=\"reveal-all-trigger mr-2 fl-l small mb-1\"]")
    WebElement allDescriptionsOption;

    @FindBy(xpath = "//a[@class=\"mt-025 reveal-trigger mr-2\"]")
    WebElement singleDescriptionOption;

    @FindBy(xpath = "//p[@class=\"small art-list-item-meta\" and contains(text(),'Published')]")
    List<WebElement> booksYearPublishingDate;

    @FindBy(xpath = "//h2[@itemprop=\"name\"]")
    List<WebElement> booksTitles;

    @FindBy(xpath = "//option[@selected]")
    WebElement currentYearDropDown;




    public BooksPage(){

        //Initializing all provided elements on the books page
        PageFactory.initElements(driver,this);
    }



    private List<Integer> extractYear() {

        //Get all published text string records with year values
        List<String> booksYearTextCollection = new ArrayList<String>();
        for (WebElement bookYearPublishingDate : booksYearPublishingDate){
            String yearText = bookYearPublishingDate.getText();
            booksYearTextCollection.add(yearText);
        }

        //Get all year values string records and convert them into Integer format
        List<Integer> booksYearDigitCollection = new ArrayList<Integer>();
        for (String bookYearPublishingDateDigit : booksYearTextCollection){
            Integer yearDigit = Integer.valueOf(bookYearPublishingDateDigit.replaceAll("[^0-9]",""));
            booksYearDigitCollection.add(yearDigit);
        }
        return booksYearDigitCollection;
    }



    public void yearDecreaseCompare(){

        //Compare prevoius book's year item with the next one
        extractYear();
        int i;
        for (i = 0; i < extractYear().size(); i++){
            for (int j = i+1; j < extractYear().size(); j++) {
                if(extractYear().get(i) >= extractYear().get(j)) {
                    continue;
                } else {
                    System.out.println("Decreasing order is wrong");
                    break;
                }
            }
        }
    }



    public void yearIncreaseCompare(){

        //Compare prevoius book's year item with the next one
        extractYear();
        int i;
        for (i = 0; i < extractYear().size(); i++){
            for (int j = i+1; j < extractYear().size(); j++) {
                if(extractYear().get(i) <= extractYear().get(j)) {
                    continue;
                } else {
                    System.out.println("Increasing order is wrong");
                    break;
                }
            }
        }
    }



    public void alphabeticSortVerification(){

        //Book names sorting
        List<String> actual = new ArrayList<String>();
        List<String> expected = new ArrayList<String>();
        for (WebElement webElement : booksTitles) {
            actual.add(webElement.getText());
            expected.add(webElement.getText());
        }

        //Sorting expected values by actual names and verify actual and expected results
        Collections.sort(expected);
        for (int i = 0; i < actual.size(); i++) {
            Assert.assertEquals(actual.get(i), expected.get(i), "Books name mismatch for " + i + " element");
        }
    }

    private Set<Integer> extractCurrentYear() {

        //Get all published text string records with year values
        HashSet<String> booksCurrentYearTextCollection = new HashSet<String>();
        for (WebElement bookYearPublishingDate : booksYearPublishingDate){
            String yearText = bookYearPublishingDate.getText();
            booksCurrentYearTextCollection.add(yearText);
        }

        //Get all year values string records and convert them into Integer format
        HashSet<Integer> booksCurrentYearDigitCollection = new HashSet<Integer>();
        for (String bookYearPublishingDateDigit : booksCurrentYearTextCollection){
            Integer yearDigit = Integer.valueOf(bookYearPublishingDateDigit.replaceAll("[^0-9]",""));
            booksCurrentYearDigitCollection.add(yearDigit);
        }
        return booksCurrentYearDigitCollection;
    }

    public boolean yearCurrentCompare(){

        //Compare expected year result in drop down list with actual in set
        extractCurrentYear();

        String yearCurrentForDropDownStr = currentYearDropDown.getAttribute("value");
        Integer yearCurrentForDropDownInt = Integer.valueOf(yearCurrentForDropDownStr);

        if (extractCurrentYear().equals(yearCurrentForDropDownInt)){
            return true;
        } else {
            return false;
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

}
