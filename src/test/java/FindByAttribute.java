package dev.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FindByAttribute {
    WebDriver driver;

    @BeforeMethod
    public void before() {
        driver = new ChromeDriver();

        //driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.get("https://www.saucedemo.com");
    }


    @Test
    public void testLogInPage() {
        String expectedTitle = "Swag Labs";
        String originalTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, originalTitle);
    }

    @Test
    /*Empty Log in*/
    public void emptyLogIn() {
        WebElement logInButton = driver.findElement(By.cssSelector("#login-button"));
        logInButton.click();
        WebElement errorMessage = driver.findElement(By.cssSelector("div.error"));
        if (errorMessage.isDisplayed()) {
            String expectedErrorBGColor = "rgba(226, 35, 26, 1)";
            String errorBGColor = errorMessage.getCssValue("background-color");
            Assert.assertEquals(errorBGColor, expectedErrorBGColor);

            WebElement errorMessage2 = driver.findElement(By.cssSelector("div.error>h3"));

            String errorText = errorMessage2.getText();
            String expectedErrorText = "Epic sadface: Username is required";

            Assert.assertEquals(errorText, expectedErrorText);

            /*Check Red Underline*/
            WebElement userName = driver.findElement(By.cssSelector("#user-name"));
            String errorUnderlineColor = userName.getCssValue("border-bottom-color");
            String expectedErrorUnderlineColor = "rgba(226, 35, 26, 1)";
            Assert.assertEquals(errorUnderlineColor, expectedErrorUnderlineColor);

            /*Red X Icon*/
            WebElement iconRedX = driver.findElement(By.cssSelector(".error_icon"));
            String xIconColor = iconRedX.getCssValue("color");
            String expetedxIconColor = "rgba(226, 35, 26, 1)";
            Assert.assertEquals(xIconColor, expetedxIconColor);

            WebElement buttonX = driver.findElement(By.cssSelector("button.error-button"));
            buttonX.click();
        }
    }

    @Test
    public void emptyUserName() {
        WebElement userName = driver.findElement(By.cssSelector("#user-name"));
        WebElement password = driver.findElement(By.cssSelector("#password"));
        password.sendKeys("secret_sauce");

        /*Click Log In Button*/
        WebElement logInButton = driver.findElement(By.cssSelector("#login-button"));
        logInButton.click();

        WebElement errorMessage2 = driver.findElement(By.cssSelector("div.error>h3"));

        String errorText = errorMessage2.getText();
        String expectedErrorText = "Epic sadface: Username is required";

    }

    @Test
    public void wrongUserName() {
        WebElement userName = driver.findElement(By.cssSelector("#user-name"));
        userName.sendKeys("standard_usera");

        WebElement password = driver.findElement(By.cssSelector("#password"));
        password.sendKeys("secret_sauce");

        /*Click Log In Button*/
        WebElement logInButton = driver.findElement(By.cssSelector("#login-button"));
        logInButton.click();

        WebElement errorMessage2 = driver.findElement(By.cssSelector("div.error>h3"));

        String errorText = errorMessage2.getText();
        String expectedErrorText = "Epic sadface: Username and password do not match any user in this service";
    }

    @Test
    public void wrongPassword() {
        WebElement userName = driver.findElement(By.cssSelector("#user-name"));
        userName.sendKeys("standard_user");

        WebElement password = driver.findElement(By.cssSelector("#password"));
        password.sendKeys("secret_sauce ");

        /*Click Log In Button*/
        WebElement logInButton = driver.findElement(By.cssSelector("#login-button"));
        logInButton.click();

        WebElement errorMessage2 = driver.findElement(By.cssSelector("div.error>h3"));

        String errorText = errorMessage2.getText();
        String expectedErrorText = "Epic sadface: Username and password do not match any user in this service";
    }

    @Test
    public void lockedOutUser() {
        WebElement userName = driver.findElement(By.cssSelector("#user-name"));
        userName.sendKeys("locked_out_user");

        WebElement password = driver.findElement(By.cssSelector("#password"));
        password.sendKeys("secret_sauce");

        /*Click Log In Button*/
        WebElement logInButton = driver.findElement(By.cssSelector("#login-button"));
        logInButton.click();

        WebElement errorMessage2 = driver.findElement(By.cssSelector("div.error>h3"));

        String errorText = errorMessage2.getText();
        String expectedErrorText = "Epic sadface: Sorry, this user has been locked out.";
    }



    /*@AfterMethod
   public void tearDown() {
        driver.quit();
    }*/
}