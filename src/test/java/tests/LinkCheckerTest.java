package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.FabiosaIndexPage;
import pages.LinkCheckerHelper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LinkCheckerTest {
    private WebDriver driver;
    public int statusCode;
    private LinkCheckerHelper linkCheckerHelper;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(5000, TimeUnit.SECONDS);
        linkCheckerHelper = new LinkCheckerHelper(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    /*
    Find all broken links on the page
    */

    @Test
    public void brokenKinksTest2() throws IOException, InterruptedException {
        linkCheckerHelper.getPage("https://www.whoishostingthis.com/resources/http-status-codes/");
        SoftAssert sa = new SoftAssert();
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (int i = 0; i < links.size(); i++) {
            if (!(links.get(i).getAttribute("href") == null) && !(links.get(i).getAttribute("href").equals(""))) {
                if (links.get(i).getAttribute("href").contains("http")) {
                    statusCode = linkCheckerHelper.getResponseCode(links.get(i).getAttribute("href").trim());
                    sa.assertEquals(statusCode, 200, String.format("Code: %s Linck: %s", statusCode, links.get(i).getAttribute("href")));
                    if (statusCode != 200) {
                        System.out.println("Act: broken links  " + i + " " + links.get(i).getAttribute("href"));
                    }
                    ;
                }
            }
        }
        sa.assertAll();
    }
}
