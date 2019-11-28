package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class SearchTests extends BaseTest {

    SoftAssert sa = new SoftAssert();

    @Test
    public void searchResultsTest() {
        searchPage.submitSearch("Selenium");
        for (WebElement title : searchPage.getAllTitles()) {
            sa.assertTrue(title.getText().contains("Selenium"), "Act: " + title.getText());
        }
        sa.assertAll();
    }

    @Test
    public void linkResultsTest() {
        searchPage.submitSearch("Selenium");
        searchPage.getSeleniumWebsite().click();
        assertEquals(driver.getCurrentUrl(), "https://selenium.dev/", "Act: not selenium.dev site " + driver.getCurrentUrl());
    }

    @Test
    public void paginationTests() {
        searchPage.submitSearch("Selenium");
        searchPage.getPaginationElement().click();
        assertTrue(driver.getCurrentUrl().contains("start=10"));
    }
    /*
    Відкрити google; Провести перевірку автокомпліту; В пошуковий інпут уводимо seleniu; Проводимо перевірку що у всіх варіантах присутній selenium.
    */

    @Test
    public void checkRelevanceOfSuggestions2() throws InterruptedException {
        searchPage.getGoogleResultsPage();
        searchPage.enterText("seleniu");
        Thread.sleep(3000);
        searchPage.getAllOptions();
        for (WebElement option : searchPage.getAllOptions()) {
            sa.assertTrue(option.getText().contains("selenium"), "Act: selenium isn't found" + option.getText());   //не забудь запитати як зробити красіво
        }
        sa.assertAll();
    }

     /*
     Відкрити google; Провести перевірку автокомпліту; В пошуковий інпут уводимо seleniu; У списку вибираємо selenium webdriver; Провести перевірку коректного переходу.
     */
    @Test
    public void checkLinkFromSuggestions() throws InterruptedException {
        searchPage.getGoogleResultsPage();
        searchPage.enterText("seleniu");
        Thread.sleep(1500);
        searchPage.getWebdriverSuggestion().click();
        assertTrue(driver.getCurrentUrl().contains("q=selenium+webdriver"), "Act: " + driver.getCurrentUrl());
    }

     /*
     Провести перевірку екранної клавіатури; Відкрити google; Увімкнути клавіатуру; Увести selenium (123); Провести перевірку наявності пошукового слова в інпуті.
     */

    @Test
    public void onscreenKeyboardTest() throws InterruptedException {
        searchPage.getOnscreenKeyboordButton().click();
        Thread.sleep(1500);
        searchPage.setText("987438754764873287536");
        searchPage.getAttributeValue();
        assertTrue(searchPage.getAttributeValue().equals("987438754764873287536"), "Act: input field doesn't contain 987438754764873287536  "  + "contain " + searchPage.getAttributeValue());
    }

    /*
    Відкрити google; Провести перевірку наявності логотипу на сторінці.
    */

    @Test
    public void logoIsPresentTest() {
             assertTrue(searchPage.isLogoVisible2(), "Exp: logo is not visible");
    }

    @Test
    public void logoIsPresentTest2() { assertTrue(searchPage.isLogoVisible(), "Exp: logo is not visible");
    }

    /*
    Тест з картинкою
     */

    @Test
    public void imgSearchTest() throws InterruptedException {
        driver.get("https://www.google.com/search?biw=1680&bih=971&tbm=isch&sa=1&ei=Ni3cXc-hIa_prgSYsJ2oAg&q=img&oq=img&gs_l=img.3..0l10.2433.3519..3839...0.0..0.85.242.3..");
        searchPage.fileUpload();
        for (WebElement title : searchPage.getAllTitles()) {
            sa.assertTrue(title.getText().contains("Homer"), "Act: " + title.getText());
        }
        sa.assertAll();
    }

    /*
    Find all broken links (code!=200)
    Example: https://www.toolsqa.com/selenium-webdriver/finding-broken-links-selenium-automation/
     */

    @Test
    public void brokenLinksTest() throws InterruptedException {
        driver.get("https://www.whoishostingthis.com/resources/http-status-codes/");
        searchPage.getAllLinks();
        // System.out.println(searchPage.getAllLinks().size());
        //java.util.List<WebElement> links = driver.findElements(By.partialLinkText("HTTP"));
        for (WebElement link : searchPage.getAllLinks()) {
        }
    }
    /*
    Тест з куками
     */

        @Test
        public void cookiesTest () throws InterruptedException {
            searchPage.acceptGdrp();
            Cookie cookieGooglepersonalization = searchPage.getCookieNamed("googlepersonalization");
            assertNotNull(cookieGooglepersonalization, "Act: cookie googlepersonalization aren't found");
        }
    }


       /*
       ASK VADYM

       Cookie cookieEuconsent = searchPage.getCookieNamed("euconsent");
       sa.assertTrue(cookieGooglepersonalization != null, "Act: cookie googlepersonalization not found");
       sa.assertTrue(cookieEuconsent != null, "Act: cookie googlepersonalization not found");
       assertTrue(searchPage.isCookiePresent(cookieGooglepersonalization), "Act: cookies \"googlepersonalization\" aren't present"); //ask Kerem why I have NullPointerException here
       */


