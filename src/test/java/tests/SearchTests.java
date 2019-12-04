package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class SearchTests extends BaseTest {


    public int statusCode;

    /*
    Open Google. Search for “selenium”. Verify that “selenium” present in all search results.
    */
    @Test
    public void searchResultsTest() {
        searchPage.submitSearch("Selenium")
                  .isAllTitlesContains("Selenium");
    }
    /*
    Open Google. Search for “selenium”. Find seleniumhq.org and click on it. Verify that the correct page was opened.
     */
    @Test
    public void linkResultsTest() {
        searchPage.submitSearch("Selenium")
                  .clickOnSite("https://selenium.dev")
                  .isUrlCorrect("https://selenium.dev/");
    }
    /*
    Open Google. Search for “selenium”. Click on the second page of the search results and verify that the correct page opened.
     */
    @Test
    public void paginationTests() throws InterruptedException {
        searchPage.submitSearch("Selenium")
                  .getPageNumber (2);
        searchPage.isUrlContainsParams("start=10");
    }

    /*
    Open Google. Check the autocomplete (type in “seleniu” and verify that all suggestions contain “selenium”)
    */
    @Test
    public void checkRelevanceOfSuggestions() throws InterruptedException {
        searchPage.getGoogleResultsPage()
                  .enterText("seleniu")
                  .getAllOptions();
        searchPage.clickSuggestion("webdriver").isAllOptionsContain("selenium");
       // driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //searchPage.isAllOptionsContain("selenium");
    }

    /*
    Open Google. Check the autocomplete (type in “seleniu”, click on suggested “selenium web driver” and verify that the correct page was open)
    */

    @Test
    public void checkLinkFromSuggestions() throws InterruptedException {
        searchPage.getGoogleResultsPage()
                   .enterText("seleniu");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        searchPage.clickSuggestion("webdriver")
                  .isUrlContainsParams("q=selenium+webdriver");
    }

     /*
     Open Google. Check the on-screen keyboard. Type in “12345” and verify that “12345” present in the input field
     */

    @Test
    public void onscreenKeyboardTest() throws InterruptedException {
        searchPage.getOnscreenKeyboardButton()
                .doThetWait()
                .setText("12345")
                .doThetWait();
        searchPage.getAttributeValue("12345");
    }

    /*
    Open Google. Verify that the logo is present.
    */

    @Test
    public void logoIsPresentTest() {
        assertTrue(searchPage.isLogoVisible(), "Act: logo is not visible on the page");
    }

    /*
    Search by images test (upload Homer Simpson's picture and verify that all search results contains the word "Homer"
     */

    @Test
    public void imgSearchTest() throws InterruptedException {
        searchPage.goSearchByImages();
        searchPage.fileUpload(new File("resources/img/homer simpson.jpg")).
                   isAllTitlesContains("Homer");
    }

}
