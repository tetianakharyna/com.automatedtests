package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.asserts.SoftAssert;

import static java.lang.Thread.sleep;

public class FabiosaIndexPage {

    private WebDriver wd;

    private final String PAGE_URL = "https://fabiosa.ru/";

    private By acceptButton = By.xpath(" //button[contains (.,\"I accept\")] ");


    public FabiosaIndexPage openPage(){
        wd.get(PAGE_URL);
        return this;
    }


    public FabiosaIndexPage acceptCmp(){
        wd.findElement(acceptButton).click();
        return this;
    }

    private Cookie getCookieNamed(String name) {
        return wd.manage().getCookieNamed(name);
    }

    public void isCookiesExist(String... names){
        SoftAssert sa = new SoftAssert();
        for (String name : names){
            Assert.assertNotNull(getCookieNamed(name), name);
        }
        sa.assertAll();
    }

    public FabiosaIndexPage(WebDriver wd){
        this.wd=wd;
    }


}