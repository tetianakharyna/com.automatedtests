package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.SearchPage;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertTrue;

public class SearchTests extends BaseTest {


//    @Test
//    public void logoVisibilityTest2() {
//        assertTrue(logoVisible);
//    }

    @Test
    public void searchResultsTest () throws InterruptedException {
        searchPage.enterText("Selenium");
        searchPage.submitSearch();
        for (WebElement title : searchPage.getAllTitles())
        {
            assertTrue(title.getText().contains("Selenium"), "Act: "+title.getText());
        }
    }

    @Test
    public void clickOnResultTest (){
        searchPage.enterText();
    }
}

/*
  public WebDriver driver;
    public By logo = By.id("hplogoooooo");

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "recouses/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @BeforeMethod
    private void bm() {
        driver.get("https://www.google.com/");
    }

    //1 Відкрити google; Провести пошук по слову selenium; Провести перевірку наявності нашого слова у всіх темах пошукової видачі.
    @Test
    public void searchResultTest() {
        SoftAssert sa = new SoftAssert();
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium");
        searchBox.submit();
        List<WebElement> allTitles = driver.findElements(By.xpath("//h3[@class=\"LC20lb\"]"));
        for (WebElement title : allTitles)
        {
            var titleText = title.getText();
            //assertTrue(titleText.contains("Selenium"));
            sa.assertTrue(titleText.contains("Seleniu22222m"), "Exp: contains Seleniu11m; Act: "+titleText);
        }
        sa.assertAll();
    }


    //2 Відкрити google; Провести пошук по слову selenium; Знайти в пошуковій видачі тему з сайту seleniumhq.org (https://selenium.dev) та перейти по ній; Прости перевірку вдалого переходу;
    @Test
    public void clickOnResultTest() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium");
        searchBox.submit();
        WebElement seleniumSite = driver.findElement(By.xpath("//cite[ contains (., \"https://selenium.dev\")]"));
        seleniumSite.click();
        assertEquals(driver.getCurrentUrl(), "https://selenium.dev/", "not https://selenium.dev/");
    }

    // 3. Відкрити google; Провести пошук по слову selenium; Перейти на другу сторінку результатів пошуку; Провести перевірку коректного переходу.
    @Test
    public void paginationSTest() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium");
        searchBox.submit();
        WebElement secondPaginationElement = driver.findElement(By.xpath("//td[3]/a"));
        secondPaginationElement.click();
        assertTrue(driver.getCurrentUrl().contains("start=10"));
    }

    // 4. Відкрити google; Провести перевірку автокомпліту; В пошуковий інпут уводимо seleniu; Проводимо перевірку що у всіх варіантах присутній selenium.
    // не відпрацьовує
    @Test
    public void checkAllSuggestions() throws InterruptedException {
        driver.get("https://www.google.com/search?ei=-x7dXYj2I82asAe2942wAQ&q=selenium&oq=seleniu&gs_l=psy-ab.1.0.0l10.2002.3446..5185...0.0..0.105.784.9j1......0....1..gws-wiz.....0..0i67j0i131.w-_btgVv0Rw");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.clear();
        searchBox.sendKeys("Seleniu");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Thread.sleep(1500);
        //wait.until(ExpectedConditions.visibilityOfAllElements(By.xpath("//*[@class  =\"sbl1\"]")));
        List<WebElement> allOptions = driver.findElements(By.xpath("//li[@jsaction]//div[@class  =\"sbl1\"]"));
        for (WebElement option : allOptions) {
            System.out.println(option.getText());
            assertTrue(option.getText().contains("selenium"), "Act: "+option.getText());
        }
    }

    //5. Відкрити google; Провести перевірку автокомпліту; В пошуковий інпут уводимо seleniu; У списку вибираємо selenium webdriver; Провести перевірку коректного переходу.
    @Test
    public void linkFromSuggestionsTest() {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Seleniu");
        WebDriverWait wait = new WebDriverWait(driver, 2);
       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b [contains (., \"webdriver\")]/ancestor:: span")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//b [contains (., \"webdriver\")]"))).click();
       // driver.findElement(By.xpath("//b [contains (., \"webdriver\")]")).click();
        assertTrue(driver.getCurrentUrl().contains("q=selenium+webdriver"));
    }

    //6. Провести перевірку екранної клавіатури; Відкрити google; Увімкнути клавіатуру; Увести selenium (123); Провести перевірку наявності пошукового слова в інпуті.
    @Test
    public void onscreenKeyboardTest() {
        driver.findElement(By.xpath("//*[@class=\"hOoLGe\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(" //*[@id=\"kbd\"]")));
//        var letters = new char[] {'1', '2', '3'};
        String str = "987438754764873287536";
        setText(str);
        WebElement searchBox = driver.findElement(By.name("q"));
        String inputValue = searchBox.getAttribute("value");
        assertTrue(inputValue.equals(str));
    }


    //7. Відкрити google; Провести перевірку наявності логотипу на сторінці.
    @Test
    public void logoVisibilityTest() {
        boolean logoVisible = driver.findElement(logo).isDisplayed();
               assertTrue(logoVisible);
    }

    private void setText(String str){
        char[] letters = str.toCharArray();
        for (char letter : letters) {
            int asciiCode = (int)letter;
            driver.findElement(By.xpath("//*[@id=\"K" + asciiCode + "\"]")).click();
        }
    }

    private WebElement findButton(char c) {
        int asciiCode = (int)c;
        return driver.findElement(By.xpath("//*[@id=\"K" + asciiCode + "\"]"));
    }
}

 */