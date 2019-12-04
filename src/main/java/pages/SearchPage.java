package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class SearchPage {

    private WebDriver driver;

    private By logo = By.id("hplogo");
    private By inputField = By.name("q");
    private By onscreenKeyboadButton = By.xpath("//* [ @class=\"hOoLGe\"]");
    private By allTitles = By.xpath("//h3[@class=\"LC20lb\"]");
    private By allOptions = By.xpath("//li[@jsaction]//div[@class=\"sbl1\"]");
    private By imageSearchButton = By.xpath("//*[@aria-label=\"Пошук за зображенням\"]");
    private By imageUploadButton = By.xpath(" //*[@class=\"qbwr\"]/a");
    private By chooseFileButton = By.xpath("//input[@id=\"qbfile\"]");


    private By allLinks = By.tagName("a");


    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getAllOptions() {
        return driver.findElements(allOptions);
    }


    public List<WebElement> getAllTitles() {
        return driver.findElements(allTitles);
    }

    public SearchPage clickOnSite(String site){
        driver.findElement(By.xpath("//cite[ contains (., '"+site+"')]")).click();
        return this;
    }

    public void getPageNumber (Integer label){
        driver.findElement(By.xpath("//*[@aria-label=\"Page "+label+"\"]")).click();

    }

    public SearchPage clickSuggestion (String word) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//b [contains (., \"" + word + "\")]")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return this;
    }


    public SearchPage getOnscreenKeyboardButton() {
         driver.findElement(onscreenKeyboadButton).click();
         return this;
    }

    public boolean isLogoVisible () {
        try {
         driver.findElement(logo).isDisplayed();
         return true;
     } catch (org.openqa.selenium.NoSuchElementException exception) {
         System.out.println("Element wasn't found");
         return false;
     }
    }

    public SearchPage enterText(String text) {
        driver.findElement(inputField).sendKeys(text);
        return this;
    }

    public SearchPage submitSearch(String text) {
        driver.findElement(inputField).sendKeys(text);
        driver.findElement(inputField).submit();
        return this;
    }

    public void clearInputField() {
        driver.findElement(inputField).clear();
    }

    public SearchPage getGoogleResultsPage (){
        submitSearch("123");
        clearInputField();
        return this;
    }

    public SearchPage setText(String str) {
        char[] letters = str.toCharArray();
        for (char letter : letters) {
            driver.findElement(By.xpath("//*[@id=\"K" + (int) letter + "\"]")).click();
        }
        return this;
    }

    public SearchPage fileUpload(File file) throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(imageSearchButton).click();
        Thread.sleep(3000);
        driver.findElement(imageUploadButton).click();
        driver.findElement(chooseFileButton).sendKeys(file.getAbsolutePath());
        return this;
    }

    public void isAllTitlesContains(String text){
        SoftAssert sa = new SoftAssert();
        for (WebElement title : getAllTitles()) {
            sa.assertTrue(title.getText().contains(text), "Act: " + title.getText());
        }
        sa.assertAll();
    }

    public void isAllOptionsContain (String text){
        SoftAssert sa = new SoftAssert();
        for (WebElement option: getAllOptions()){
            sa.assertTrue(option.getText().contains(text), "Act: " + text + " isn't found" + option.getText());
        }
        sa.assertAll();
    }
    public void isUrlCorrect (String cite){
        assertEquals(driver.getCurrentUrl(), cite, "Act: URL " + cite + " not found " + driver.getCurrentUrl());
    }


    public void isUrlContainsParams (String attribute){
        assertTrue(driver.getCurrentUrl().contains(attribute), "Act: URL doesn't have " + attribute);
    }

    public SearchPage doThetWait (){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return this;
    }


    public void getAttributeValue (String text){
        assertEquals(text, driver.findElement(inputField).getAttribute("value"), "Act: element't doesn't contain");
    }

    public List<WebElement> getAllLinks (){
        return driver.findElements(allLinks);
    }


    public void goSearchByImages (){
        driver.get("https://www.google.com/search?biw=1680&bih=971&tbm=isch&sa=1&ei=Ni3cXc-hIa_prgSYsJ2oAg&q=img&oq=img&gs_l=img.3..0l10.2433.3519..3839...0.0..0.85.242.3..");
    }
}

