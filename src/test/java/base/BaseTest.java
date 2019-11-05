package base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://news.amomama.com");
        driver.manage().window().maximize();
        System.out.println("Current URL is " + driver.getCurrentUrl());
    }

   @AfterClass
    public void tearDown(){
       driver.quit();
    }

    public static void main (String [] args ) {
        BaseTest baseTest = new BaseTest();
        baseTest.setUp();
    }
}
