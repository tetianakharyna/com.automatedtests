package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class SearchPage {

    private WebDriver driver;
    private By logo = By.id("hplogo");
    private By inputField = By.name("q");
    private By searchGoogleButton = By.name("btnK");
    private By paginationElement = By.xpath("//td[3]/a");
    private By allTitles = By.xpath("//h3[@class=\"LC20lb\"]");

    public List<WebElement> getAllTitles() {
        return driver.findElements(allTitles);
    }

    public boolean isLogoVisible(){
        return driver.findElement(logo).isDisplayed();
    }

    public SearchPage (WebDriver driver){
        this.driver = driver;
    }

    public void enterText (String text){
        driver.findElement (inputField).sendKeys(text);
    }

    public void submitSearch (){
        driver.findElements(searchGoogleButton).get(1).click();
    }
}
