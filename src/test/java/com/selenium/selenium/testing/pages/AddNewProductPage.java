package com.selenium.selenium.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by
 * Mher Petrosyan
 * Email mher13.02.94@gmail.ru
 */

public class AddNewProductPage {

    private WebDriver driver;

    private By addNewProduct = By.xpath("//*[@id=\"mainnav\"]/ul/li[4]/div/ul/li[7]/a");

    private By title = By.name("new_title");

    //    vendor -> test
    private By chooseVendor = By.xpath("//*[@id=\"itemOrKit\"]/div/div[1]/div[1]/div/select/option[15]");

    private By sku = By.name("new_vendorSku");

    private By cost = By.name("new_cost");

    private By createButton = By.xpath("//*[@id=\"content\"]/div[2]/div/div/div/div[3]/form/div[3]/input");

    public AddNewProductPage(WebDriver driver) {

        this.driver = driver;

    }

    private void openAddProductPage() {

        driver.findElement(addNewProduct).click();
    }

    private void setTitle(String strTitle) {

        driver.findElement(title).sendKeys(strTitle);

    }


    private void selectVendor() {

        driver.findElement(chooseVendor).click();

    }

    private void setSku(String strSku) {

        driver.findElement(sku).sendKeys(strSku);

    }

    private void setCost(String strCost) {

        driver.findElement(cost).sendKeys(strCost);

    }
    //Click on login button

    public void clickCreateButton() {

        driver.findElement(createButton).click();

    }


    public void addNewProduct(String strTitle, String strSku, String strCost) {

        this.openAddProductPage();

        this.setTitle(strTitle);

        this.selectVendor();

        this.setSku(strSku);

        this.setCost(strCost);

        this.clickCreateButton();
    }

}
