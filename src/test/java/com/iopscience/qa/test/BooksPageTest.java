package com.iopscience.qa.test;

import com.iopscience.qa.base.TestBase;
import com.iopscience.qa.pages.BooksPage;
import com.iopscience.qa.pages.MainPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.iopscience.qa.pages.BooksPage.*;

public class BooksPageTest extends TestBase {

    MainPage mainPage;
    BooksPage booksPage;

    public BooksPageTest() {
        super();
    }

    //Prepare browser for test
    @BeforeMethod
    public void setUp() {
        init();
        mainPage = new MainPage();
    }

    //Latest filter option validation
    @Test(priority = 1)
    public void filterLatestYearTest() {
        mainPage.checkElem(LATEST_FILTER);
        mainPage.click(LATEST_FILTER);
        booksPage.yearDecreaseCompare();
    }

    //Oldest filter option validation
    @Test(priority = 2)
    public void filterOldestYearTest() {
        mainPage.checkElem(OLDEST_FILTER);
        mainPage.click(OLDEST_FILTER);
        booksPage.yearIncreaseCompare();
    }

    //Alphabetic filter option validation
    @Test(priority = 3)
    public void filterAlphabeticTest() {
        mainPage.checkElem(ALPHABETIC_FILTER);
        mainPage.click(ALPHABETIC_FILTER);
        booksPage.alphabeticSortVerification();
    }

    //Year drop down option validation
    @Test(priority = 4)
    public void filterYearTest() {
        mainPage.checkElem(YEAR_FILTER);
        mainPage.selectFromDropDown(YEAR_FILTER,"2013");
        mainPage.click(GO_BUTTON);
        booksPage.yearCurrentCompare();
    }

    //Close browser with all tabs if any
    @AfterMethod
    public void close() {
        tearDown();
    }
}
