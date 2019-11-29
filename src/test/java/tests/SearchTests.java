package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import static org.testng.Assert.*;

public class SearchTests extends BaseTest {

    SoftAssert sa = new SoftAssert();
    public int statusCode;


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
        assertTrue(searchPage.getAttributeValue().equals("987438754764873287536"), "Act: input field doesn't contain 987438754764873287536  " + "contain " + searchPage.getAttributeValue());
    }

    /*
    Відкрити google; Провести перевірку наявності логотипу на сторінці.
    */

    @Test
    public void logoIsPresentTest() {
        assertTrue(searchPage.isLogoVisible(), "Act: logo is not visible on the page");
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
    Тест з куками
     */
    @Test
    public void cookiesTest() throws InterruptedException {
        searchPage.acceptGdrp();
        Cookie cookieGooglepersonalization = searchPage.getCookieNamed("googlepersonalization");
        assertNotNull(cookieGooglepersonalization, "Act: cookie googlepersonalization aren't found");
    }

    /*
    Тест на поламані лінки
     */

    @Test
    public void brokenLinksTest1() throws MalformedURLException {
        driver.get("https://www.whoishostingthis.com/resources/http-status-codes/");
        searchPage.getAllLinks();
        for (WebElement link : searchPage.getAllLinks()) {
           String urlLink = link.getAttribute("href");
           assertTrue(searchPage.isLinkBroken(new URL(urlLink)), "ggggggg");
        }
    }

    @Test
    public  void brokenKinksTest2 () throws IOException, InterruptedException {
        driver.get("https://www.whoishostingthis.com/resources/http-status-codes/");
        SoftAssert sa = new SoftAssert();
        Thread.sleep(2000);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for(int i = 0; i < links.size(); i++){
            if(!(links.get(i).getAttribute("href") == null) && !(links.get(i).getAttribute("href").equals(""))){
                if(links.get(i).getAttribute("href").contains("http")){
                    statusCode= getResponseCode(links.get(i).getAttribute("href").trim());
                    if(statusCode != 200){
                        System.out.println("Act: broken links  " + i + " " + links.get(i).getAttribute("href"));
                    };
                }
            }
        }
    }
    public int getResponseCode(String urlString) throws MalformedURLException, IOException{
        URL url = new URL(urlString);
        HttpURLConnection huc = (HttpURLConnection)url.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        return huc.getResponseCode();
    }
    }
    /*
    Output:
    Act: broken links  31 https://www.whoishostingthis.com/hosting-reviews/ukit/
    Act: broken links  229 http://iana.org/
     */

       /*
       запитати Вадима чому некоретно відпрацьовують асерти в цьому варіанті варіанті
   @Test
    public void cookiesTest2() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        searchPage.acceptGdrp();
        Cookie cookieGooglepersonalization = searchPage.getCookieNamed("googleperso11nalization");
        Cookie cookieEuconsent = searchPage.getCookieNamed("euconsent");
        softAssert.assertTrue(cookieGooglepersonalization != null, "Act: cookie googlepersonalization not found");
        softAssert.assertTrue(cookieEuconsent != null, "Act: cookie euconsent not found");
        softAssert.assertAll();
    }

       */


