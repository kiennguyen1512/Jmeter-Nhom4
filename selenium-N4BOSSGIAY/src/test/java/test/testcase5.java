//1. Go to http://live.techpanda.org/
//        2. Click on my account link
//        3. Click Create an Account link and fill New User information except Email ID
//        4. Click Register
//        5. Verify Registration is done. Expected account registration done.
//        6. Go to TV menu
//        7. Add product in your wish list - use product - LG LCD
//        8. Click SHARE WISHLIST
//        9. In next page enter Email and a message and click SHARE WISHLIST
//        10.Check wishlist is shared. Expected wishlist shared successfully.
//        */

package test;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
@Test
public class testcase5 {
    public static void test05() {
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //1. Goto http://live.techpanda.org/
            driver.get("https://bossgiay.vn/");
            //2. Click on my account link
            WebElement Account = driver.findElement(By.xpath("//a[@href='/account/login']//*[name()='svg']"));
            Account.click();


            //3. Click Create an Account link and fill New User information except Email ID

            WebElement label = driver.findElement(By.xpath("//a[@title='Đăng ký']"));
            label.click();
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement usernameElem = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("body > div:nth-child(9) > div:nth-child(1) > div:nth-child(1) > main:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > form:nth-child(1) > div:nth-child(7) > input:nth-child(2)"))));
            Thread.sleep(2000);
            WebElement first = driver.findElement(By.cssSelector("body > div:nth-child(9) > div:nth-child(1) > div:nth-child(1) > main:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > form:nth-child(1) > div:nth-child(3) > input:nth-child(2)"));
            WebElement birth = driver.findElement(By.cssSelector("body > div:nth-child(9) > div:nth-child(1) > div:nth-child(1) > main:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > form:nth-child(1) > div:nth-child(6) > input:nth-child(2)"));
            WebElement last = driver.findElement(By.cssSelector("body > div:nth-child(9) > div:nth-child(1) > div:nth-child(1) > main:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > form:nth-child(1) > div:nth-child(4) > input:nth-child(2)"));
            WebElement password = driver.findElement(By.cssSelector("body > div:nth-child(9) > div:nth-child(1) > div:nth-child(1) > main:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > form:nth-child(1) > div:nth-child(8) > input:nth-child(2)"));




            usernameElem.sendKeys("b01jasonabanil@gmail.com");
            first.sendKeys("khoi");
            birth.sendKeys("6/4/2005");
            last.sendKeys("ha");
            password.sendKeys("123456");

            Thread.sleep(2000);


           // 4. Click Register
            WebElement loginBtnElem = driver.findElement(By.cssSelector("input[value='Đăng ký']"));
            loginBtnElem.click();
//            //5. Verify Registration is done. Expected account registration done.
//            //6. Go to TV menu
             WebElement sandal = driver.findElement(By.xpath("//a[@title='Slide/Sandal']"));
             sandal.click();
//
            // 7. Add product in your cart
//
           WebElement wish = driver.findElement(By.xpath("//a[normalize-space()='Puma Shibui Cat Black [ 385296 - 02 ]']"));
            wish.click();

            WebElement cart = driver.findElement(By.xpath("//button[@id='add-to-cart']"));
            cart.click();

            WebElement gio = driver.findElement(By.xpath("//span[@class='cart-menu']//*[name()='svg']"));
            gio.click();


//            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
