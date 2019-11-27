package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchTests extends BaseTest {

    SoftAssert sa = new SoftAssert();

    @Test
    public void searchResultsTest() throws InterruptedException {
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
        assertEquals(driver.getCurrentUrl(), "https://selenium.dev/", "Act: not https://selenium.dev/" + driver.getCurrentUrl());
    }

    @Test
    public void paginationTests() {
        searchPage.submitSearch("Selenium");
        searchPage.getPaginationElement().click();
        assertTrue(driver.getCurrentUrl().contains("start=10"));
    }

    @Test
    public void checkRelevanceOfSuggestions() throws InterruptedException {
        searchPage.submitSearch("1");
        searchPage.clearInputField();
        searchPage.enterText("Seleniu");
        Thread.sleep(1500);
        searchPage.getAllOptions();
        for (WebElement option : searchPage.getAllOptions()) {
            sa.assertTrue(option.getText().contains("selenium"), "Act: " + option.getText()); //запитати Вадимa як зробити красіво
        }
        sa.assertAll();
    }

    //5. Відкрити google; Провести перевірку автокомпліту; В пошуковий інпут уводимо seleniu; У списку вибираємо selenium webdriver; Провести перевірку коректного переходу.
    @Test
    public void checkLinkFromSuggestions() throws InterruptedException {
        searchPage.submitSearch("1");
        searchPage.clearInputField();
        searchPage.enterText("seleniu");
        Thread.sleep(1500);
        searchPage.getWebdriverSuggestion().click();
        assertTrue(driver.getCurrentUrl().contains("q=selenium+webdriver"), "Act: " + driver.getCurrentUrl());
    }

    //6. Провести перевірку екранної клавіатури; Відкрити google; Увімкнути клавіатуру; Увести selenium (123); Провести перевірку наявності пошукового слова в інпуті.
    @Test
    public void onscreenKeyboardTest() throws InterruptedException {
        searchPage.getOnscreenKeyboordButton().click();
        Thread.sleep(1500);
        String str = "987438754764873287536";
        searchPage.setText(str);
        WebElement searchBox = driver.findElement(By.name("q"));
        String inputValue = searchBox.getAttribute("value");
        assertTrue(inputValue.equals(str), "Act: input field doesn't contain  " + str + "contain " + searchBox.getAttribute("value"));
    }

    //7. Відкрити google; Провести перевірку наявності логотипу на сторінці.
    @Test
    public void logoIsPresentTest() {
        assertTrue(searchPage.isLogoVisible(), "Exp: logo is not visible");
    }

    //9. Тест з картинкою
    @Test
    public void imgSearchTest() throws InterruptedException {
        driver.get("https://www.google.com/search?biw=1680&bih=971&tbm=isch&sa=1&ei=Ni3cXc-hIa_prgSYsJ2oAg&q=img&oq=img&gs_l=img.3..0l10.2433.3519..3839...0.0..0.85.242.3..");
        searchPage.fileUpload();
        for (WebElement title : searchPage.getAllTitles()) {
            sa.assertTrue(title.getText().contains("Homer"), "Act: " + title.getText());
        }
        sa.assertAll();
    }
}
/*
    //10. Tecm з побитими лінками
    @Test
    public void brokenLinksTest (){
        driver.get("https://www.whoishostingthis.com/resources/http-status-codes/");
        searchPage.getAllLinks()
                .clear();
        for (WebElement link : searchPage.getAllLinks()) {
        String value = link.getAttribute("href");
        URL myurl = new URL (value)
        }

        for (WebElement link : links) {

       String value = link.getAttribute("href");
       // For each link check response code is 200.
       URL myurl = new URL(value);
       HttpURLConnection connection = (HttpURLConnection) myurl.openConnection();
       connection.setRequestMethod("HEAD");
       int code = connection.getResponseCode();
       Assert.assertEquals(code, 200);

}
         */








/*
8. Відкрити пошук картинок; Можна по прямій лінці https://www.google.com/search?biw=1680&bih=971&tbm=isch&sa=1&ei=Ni3cXc-hIa_prgSYsJ2oAg&q=img&oq=img&gs_l=img.3..0l10.2433.3519..3839...0.0..0.85.242.3......0....1..gws-wiz-img.......0i67.ghJSNzJKdG0&ved=0ahUKEwjPlJaHjobmAhWvtIsKHRhYByUQ4dUDCAc&uact=5
Провести пошук по картинці через завантаження файлу

Провести перевірку наявності у всіх темах Homer/Гомер; Враховуючи uppercase/lowercase
Теорія
https://kreisfahrer.gitbooks.io/selenium-webdriver/content/otpravlenie_faila_upload.html
9.
Написвти тест який перейде по https://www.whoishostingthis.com/resources/http-status-codes/
Виявить всі поломані лінки на сторінці (code!=200)
Один з підходів https://www.toolsqa.com/selenium-webdriver/finding-broken-links-selenium-automation/

 */