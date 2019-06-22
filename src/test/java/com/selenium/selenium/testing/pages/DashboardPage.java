package com.selenium.selenium.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by
 * Mher Petrosyan
 * Email mher13.02.94@gmail.ru
 */

public class DashboardPage {
    private WebDriver driver;

    private By dropDown = By.id("user-nav");
    private By dashboardPageUserName = By.xpath("//*[@id=\"imontheright\"]/ul/li[2]/div/div");
    private By products = By.xpath("//*[@id=\"mainnav\"]/ul/li[4]/a");


    public DashboardPage(WebDriver driver) {

        this.driver = driver;

    }

    //open drop down
    private void dropDownClick() {
        driver.findElement(dropDown).click();

    }

    //Get the User name from dashboard
    private String dropDownItemUserName() {
        return driver.findElement(dashboardPageUserName).getText();
    }

    public String getUserNameFromDashboardPage() {
        this.dropDownClick();
        return this.dropDownItemUserName();

    }

    public void openProductsFromDashboard() {
        driver.findElement(products).click();
    }
}
