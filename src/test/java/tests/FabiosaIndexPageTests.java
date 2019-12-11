package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FabiosaIndexPage;

import java.util.HashMap;
import java.util.Map;

public class FabiosaIndexPageTests {

   private WebDriver driver;

    private FabiosaIndexPage fiPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone 8");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        driver = new ChromeDriver(chromeOptions);
        fiPage = new FabiosaIndexPage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    private void cookiesTest(){
        fiPage.openPage()
                .acceptCmp()
                .isCookiesExist("googlepersonalization","euconsent");
    }


    @Test
    public void facebookUrlTest () throws InterruptedException {
        fiPage.openPage()
              .acceptCmp()
              .clickOnFaceebookIcon()
              .assertSocialNetworkUrl();
    }

}
