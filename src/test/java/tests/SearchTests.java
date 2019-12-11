package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import java.io.File;
import static org.testng.Assert.*;

public class SearchTests extends BaseTest {


    @Test
    public void searchResultsTest() {
        searchPage.submitSearch("Selenium")
                  .isAllTitlesContains("Selenium");
    }

    @Test
    public void linkResultsTest() {
        searchPage.submitSearch("Selenium")
                  .doWait()
                  .clickOnSite("https://selenium.dev")
                  .isUrlCorrect("https://selenium.dev/");
    }

    @Test
    public void paginationTests() throws InterruptedException {
        searchPage.submitSearch("Selenium")
                  .getPageNumber (2);
        searchPage.isUrlContainsParams("start=10");
    }


    @Test
    public void checkRelevanceOfSuggestions() throws InterruptedException {
        searchPage.getGoogleResultsPage()
                  .enterText("seleniu")
                  .getAllOptions();
        searchPage.clickSuggestion("webdriver").isAllOptionsContain("selenium");
    }

    @Test
    public void checkLinkFromSuggestions() throws InterruptedException {
        searchPage.getGoogleResultsPage()
                   .enterText("seleniu");
        searchPage.clickSuggestion("webdriver")
                  .isUrlContainsParams("q=selenium+webdriver");
    }



    @Test
    public void onscreenKeyboardTest() throws InterruptedException {
        searchPage.getOnscreenKeyboardButton()
                .doWait()
                .setText("12345")
                .doWait();
        searchPage.getAttributeValue("12345");
    }


    @Test
    public void logoIsPresentTest() {
        assertTrue(searchPage.isLogoVisible(), "Act: logo is not visible on the page");
    }



    @Test
    public void imgSearchTest() throws InterruptedException {
        searchPage.goSearchByImages();
        searchPage.fileUpload(new File("resources/img/homer simpson.jpg")).
                   isAllTitlesContains("Homer");
    }

}
