/*
Test Steps:
1. Goto http://live.techpanda.org/
2. Click on �MOBILE� menu
3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
4. Click on Sony Xperia mobile
5. Read the Sony Xperia mobile from detail page.
6. Compare Product value in list and details page should be equal ($100).
*/
package test;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;
import driver.driverFactory;

import java.io.File;

@Test
public class testcase2 {
    public static void test02(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1. Goto http://live.techpanda.org/
            driver.get("https://bossgiay.vn/");
            //2. Click on �MOBILE� menu
            WebElement sneaker = driver.findElement(By.xpath("//a[@title='Sneaker']"));
            sneaker.click();
            //3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
            WebElement sonyXperiaPrice = driver.findElement(By.xpath("//a[contains(text(),'[ DV1299-104 ] W Nike Air Jordan 1 Low SE Reverse ')]"));
            String sonyXperiaPriceText = sonyXperiaPrice.getText();
            System.out.println("The cost of the Sony Xperia mobile is: " + sonyXperiaPriceText);
            //4. Click on Sony Xperia mobile
            WebElement Reverse = driver.findElement(By.xpath("//a[contains(text(),'[ DV1299-104 ] W Nike Air Jordan 1 Low SE Reverse ')]"));
            Reverse.click();
            //5. Read the Sony Xperia mobile from detail page.
            WebElement SonyDetail = driver.findElement(By.xpath("//h1[contains(text(),'[ DV1299-104 ] W Nike Air Jordan 1 Low SE Reverse ')]"));
            String SonyDetailText = SonyDetail.getText();
            System.out.println("The name of this sneaker is: "+SonyDetailText);
            //6. Compare Product value in list and details page should be equal ($100).
            WebElement SonyDetailPrice = driver.findElement(By.xpath("//span[@class='pro-price']"));
            String SonyDetailPriceText2 = SonyDetailPrice.getText();
            if(SonyDetailPriceText2.equals(sonyXperiaPriceText)){
                System.out.println("The product value in the list and detail pages is equal.");
            }else {
                System.out.println("The product value in the list and detail pages is not equal.");
            }
            TakesScreenshot screenshot =((TakesScreenshot)driver);
            File srcFile= screenshot.getScreenshotAs(OutputType.FILE);
            FileHandler.copy(srcFile, new File(".\\src\\test\\img\\TestCase02.png"));
            driver.quit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
