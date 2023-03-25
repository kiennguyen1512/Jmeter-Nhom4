/*
Test Steps
Step 1. Go to https://bossgiay.vn/
Step 2. Verify Title of the page
Step 3. Click on -> Sneaker
Step 4. In the list of all mobile , select SORT BY -> dropdown as name
Step 5. Verify all products are sorted by name
*/
package test;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.io.File;


@Test
public class testcase1 {
    public static void test01(){
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //Step 1. Go to https://bossgiay.vn/
            driver.get("https://bossgiay.vn/");
            //Step 2. Verify Title of the page
            String pageTitle = driver.getTitle();
            System.out.println(pageTitle);
            //Step 3. Click on -> Sneaker
            WebElement sneaker = driver.findElement(By.xpath("//a[@title='Sneaker']"));
            sneaker.click();
            //Step 4. In the list of all mobile , select SORT BY -> dropdown as name
            WebElement ByDropdown = driver.findElement(By.xpath("//select[@class='sort-by custom-dropdown__select']"));
            ByDropdown.click();
            Select selectSortBy = new Select(ByDropdown);
            selectSortBy.selectByVisibleText("Tên: A-Z");
            //Step 5. Verify all products are sorted by name
            TakesScreenshot screenshot =((TakesScreenshot)driver);
            File srcFile= screenshot.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(srcFile, new File(".\\src\\test\\img\\TestCase01.png"));
            driver.quit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
