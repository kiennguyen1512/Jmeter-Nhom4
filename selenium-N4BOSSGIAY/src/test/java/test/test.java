package test;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;

@Test
public class test {
    public static void testHandleDropdown() {

        //1. Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //2. Open target page - Login Form
            driver.get("http://live.techpanda.org/index.php/mobile.html");
            //3. Locate dropdown element
            WebElement dropdownElement = driver.findElement(By.xpath("//body/div[@class='wrapper']/div[@class='page']/div[@class='main-container col3-layout']/div[@class='main']/div[@class='col-wrapper']/div[@class='col-main']/div[@class='category-products']/div[@class='toolbar']/div[@class='sorter']/div[@class='sort-by']/select[1]"));
            //4. Init a Select Option instance
            Select selectOption = new Select(dropdownElement);

            selectOption.selectByVisibleText("Position");
            selectOption.selectByVisibleText("Name");
            selectOption.selectByVisibleText("Price");

            //Or select option in dropdown list by value
            //debug purpose only
            Thread.sleep(10000);
            selectOption.selectByValue("http://live.techpanda.org/index.php/mobile.html?dir=asc&mode=grid&order=position");
            selectOption.selectByValue("http://live.techpanda.org/index.php/mobile.html?dir=asc&mode=grid&order=name");
            selectOption.selectByValue("http://live.techpanda.org/index.php/mobile.html?dir=asc&mode=grid&order=price");

            //debug purpose only
            Thread.sleep(10000);

            //debug purpose only
            Thread.sleep(10000);

        }catch (Exception e){
            e.printStackTrace();
        }
        //7. Quit browser session

    }
}
