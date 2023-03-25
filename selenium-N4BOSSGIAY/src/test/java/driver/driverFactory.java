package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class driverFactory {
    public static WebDriver getChromeDriver() {
        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverRelativePath = "/src/test/resources/drivers/chromedriver.exe";
        String chromeDriverLocation = currentProjectLocation.concat(chromeDriverRelativePath);
        System.setProperty("webdriver.firefox.driver", chromeDriverLocation);
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        //return new ChromeDriver();
        return driver;
    }
}
