package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FabiosaIndexPage;

import java.util.concurrent.TimeUnit;

public class FabiosaIndexPageTests {

    private WebDriver driver;

    private FabiosaIndexPage fiPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5000, TimeUnit.SECONDS);
        fiPage = new FabiosaIndexPage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    /*
    Go to fabiosa.ru,  accept Privacy Policy and Terms of Use and verify that needed cookies we saved
     */
    @Test
    private void cookiesTest(){
        fiPage.openPage().
                acceptCmp().
                isCookiesExist("googlepersonalization","euconsent");
    }




}
