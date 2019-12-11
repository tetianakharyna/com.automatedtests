package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.naming.directory.SearchResult;


public class FabiosaIndexPage {

    private WebDriver wd;

    private final String PAGE_URL = "https://fabiosa.ru/";
    private final String fb_url = "https://m.facebook.com/marketium.ru/";

    private By facebookIcon = By.xpath("//*[@alt=\"Facebook\"]");
    private By acceptButton = By.xpath(" //button[contains (.,\"I accept\")] ");

    public FabiosaIndexPage(WebDriver wd){
        this.wd=wd;
    }

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

    public FabiosaIndexPage clickOnFaceebookIcon(){
        wd.findElement(facebookIcon).click();
        return this;
    }


    public void assertSocialNetworkUrl() throws InterruptedException {
        Thread.sleep(3000);
        Assert.assertEquals(fb_url, wd.getCurrentUrl(), "Act: facebookIcon URL doesn't equal expected value");
    }

}