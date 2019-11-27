package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.naming.directory.SearchResult;
import java.util.List;


public class SearchPage {

    private WebDriver driver;

    private By logo = By.id("hplogo");
    private By inputField = By.name("q");
    private By searchGoogleButton = By.xpath("//div[@class=\"FPdoLc tfB0Bf\"]/input");
    private By seleniumWebsite = By.xpath("//cite[ contains (., \"https://selenium.dev\")]");
    private By webdriverSuggestion = By.xpath("//b [contains (., \"webdriver\")]");
    private By onscreenKeyboadButton = By.xpath("//* [ @class=\"hOoLGe\"]");
    private By paginationElement = By.xpath("//a[@aria-label=\"Page 2\"]");
    private By allTitles = By.xpath("//h3[@class=\"LC20lb\"]");
    private By allOptions = By.xpath("//li[@jsaction]//div[@class=\"sbl1\"]");


    //private By imageSearchButton = By.xpath("//span[@class=\"BwoPOe\"]");
    private By imageSearchButton = By.xpath("//*[@aria-label=\"Пошук за зображенням\"]");
    private By imageUploadButton = By.xpath(" //*[@class=\"qbwr\"]/a");
    private By chooseFileButton = By.xpath("//input[@id=\"qbfile\"]");

    private By allLinks =By.partialLinkText("HTTP");

    public List<WebElement> getAllLinks (){
        return driver.findElements(allLinks);
    }
    public List<WebElement> getAllOptions() {
        return driver.findElements(allOptions);
    }

    public WebElement getPaginationElement() {
        return driver.findElement(paginationElement);
    }

    public List<WebElement> getAllTitles() {
        return driver.findElements(allTitles);
    }

    public WebElement getSeleniumWebsite() {
        return driver.findElement(seleniumWebsite);
    }

    public WebElement getWebdriverSuggestion() {
        return driver.findElement(webdriverSuggestion);
    }

    public WebElement getOnscreenKeyboordButton() {
        return driver.findElement(onscreenKeyboadButton);
    }

    public boolean isLogoVisible() {
        return driver.findElement(logo).isDisplayed();
    }

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterText(String text) {
        driver.findElement(inputField).sendKeys(text);
    }

    public void submitSearch(String text) {
        driver.findElement(inputField).sendKeys(text);
        driver.findElement(inputField).submit();
    }

    public void clearInputField() {
        driver.findElement(inputField).clear();
    }

    public void setText(String str) {
        char[] letters = str.toCharArray();
        for (char letter : letters) {
            int asciiCode = (int) letter;
            driver.findElement(By.xpath("//*[@id=\"K" + asciiCode + "\"]")).click();
        }
    }

    private WebElement findButton(char c) {
        int asciiCode = (int) c;
        return driver.findElement(By.xpath("//*[@id=\"K" + asciiCode + "\"]"));
    }

    public void fileUpload() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(imageSearchButton).click();
        Thread.sleep(1500);
        driver.findElement(imageUploadButton).click();
        driver.findElement(chooseFileButton).sendKeys("/home/tetiana_kharyna/workspace/automation/automatedtests/resources/img/homer simpson.jpg");
    }
}
