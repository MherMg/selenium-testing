package com.selenium.selenium.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by
 * Mher Petrosyan
 * Email mher13.02.94@gmail.ru
 */

public class LoginPage {

    private WebDriver driver;

    private By usernameJazva = By.name("username");

    private By passwordJazva = By.name("password");

    private By login = By.xpath("//*[@id=\"login\"]/div[2]/div[2]/div[2]/div[1]/form/div[3]/input");

    public LoginPage(WebDriver driver) {

        this.driver = driver;

    }

    //Set userName in userName textBox
    private void setUserName(String userName) {

        driver.findElement(usernameJazva).sendKeys(userName);

    }

    //Set password in password textBox

    private void setPassword(String password) {

        driver.findElement(passwordJazva).sendKeys(password);

    }

    //Click on login button

    public void clickLogin() {

        driver.findElement(login).click();

    }


    public void loginToBobsJazva(String userName, String password) {

        this.setUserName(userName);

        this.setPassword(password);

        this.clickLogin();

    }

}
