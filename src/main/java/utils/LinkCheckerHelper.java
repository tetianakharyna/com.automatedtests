package utils;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LinkCheckerHelper {
    private WebDriver driver;

    public LinkCheckerHelper (WebDriver driver){
        this.driver = driver;
    }

    public void getPage (String URL) {
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }
    public int getResponseCode(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection huc = (HttpURLConnection)url.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        return huc.getResponseCode();
    }

}

