package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.SearchPage;





public class BaseTest {
    public WebDriver driver;
    protected SearchPage searchPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    protected void goSearchMethod() {
        driver.get("https://www.google.com.ua/");
        searchPage = new SearchPage(driver);
    }
}











/*
    @Test
    public void getCurrentUrlTest() {
        assertEquals(driver.getCurrentUrl(), "https://www.google.com/", "Not https://www.google.com/");
    }

    // works fine. 7. Open Google. Verify that the logo is present.
    @Test
    public void logoVisibilityTest2() {
        boolean logoVisible22 = driver.findElement(By.id("hplogo")).isDisplayed();
        assertTrue(logoVisible22);
    }

    //doesn't work. 1. Doesn't work. Open Google. Search for “selenium”. Verify that “selenium” present in all search results.
    @Test
    public void searchResultsTest() {
        driver.findElement(searchBox).sendKeys("selenium");
        driver.findElement(searchButton).click();
        List<WebElement> searchResultsContains = driver.findElements(By.xpath("//h3"));
        assertTrue(searchResultsContains.contains("Selenium"));
    }

    @Test
    public void checkLink (){
        element.sendKeys("Selenium!");
        element.submit();
        driver.findElement(thirdTitle).click();
        assertEquals(driver.getCurrentUrl(), "https://selenium.dev/", "not https://selenium.dev/" );
    }

    @Test
    public void isSearchBoxDisplayedTest() {
        assertTrue(driver.findElement(By.name("q")).isDisplayed());
    }
}

  //  @Test
   // public void isCharsetDefinedTest() {
       // boolean isTheTextPresent = driver.getPageSource().contains("UTF-8");
       // assertTrue(isTheTextPresent);
    }

   // @Test
   // public void isLandEngUA() {
        assertTrue(driver.getPageSource().contains("en-UA"));
    }



    //Відкрити google; Провести пошук по слову selenium; Провести перевірку наявності нашого слова у всіх темах пошукової видачі.
   /* @Test
    public void searchInputTest (){
driver.findElement(searchField).sendKeys("selenimum");
driver.findElement(searchButton).submit();
boolean isSearchWordsVisible = driver.findElements(allDescriptions).contains("selenium");
assertFalse(isSearchWordsVisible);
    } */

  /*  //  Відкрити google; Провести перевірку наявності логотипу на сторінці.
    @Test
    public void isLogoVisibleTest() {
        boolean logoVisible = driver.findElement(By.id("hplogo")).isDisplayed();
        assertTrue(logoVisible);
    }
}

       // searchBox = driver.findElement(By.name("q"));
        ///filement(By.NAME, 'q');
        //q.send_keys('webdriver');
        //q.submit()  ;

//            boolean isTheTextPresent = driver.getPageSource().contains("your text");
//    assertTrue(isTheTextPresent);



//    @Test(priority = 2, dependsOnMethods = "firstTest1")
//    public void firstTest () {
//        assertEquals(driver.getTitle(),"Google", "Not Google");
//    }
//
//    @Test(priority = 1)
//    public void firstTest1 () {
//        assertEquals(driver.getTitle(),"Google1", "Not Google");
//    }

   */
