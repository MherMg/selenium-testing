package com.selenium.selenium.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

/**
 * Created by
 * Mher Petrosyan
 * Email mher13.02.94@gmail.ru
 */

public class ProductPage {

    private WebDriver driver;

    private By action = By.xpath("//*[@id=\"content\"]/div[2]/div/div/div/div[1]/div[1]/div/a[2]");

    private By deleteProduct = By.xpath("//*[@id=\"content\"]/div[2]/div/div/div/div[1]/div[1]/div/ul/li[5]/a");

    private By yesDeleteButton = By.xpath("//*[@id=\"confirm-delete-product\"]/div/div/div[3]/a[2]");

    private By clickInProduct = By.xpath("//*[@id=\"maintable\"]/tbody/tr/td[6]/a");

    private By skuFromProductPage = By.name("sku");

    public ProductPage(WebDriver driver) {

        this.driver = driver;

    }

    public void clickInProduct() {
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajaxElementLocatorFactory, this);
        driver.findElement(clickInProduct).click();
    }


    private void openAction() {

        driver.findElement(action).click();

    }


    private void clickOnDeleteProduct() {

        driver.findElement(deleteProduct).click();
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 20);
        PageFactory.initElements(ajaxElementLocatorFactory, this);

    }

    private void clickOnConfirmDeleteProduct() {

        driver.findElement(yesDeleteButton).click();
        AjaxElementLocatorFactory ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver, 150);
        PageFactory.initElements(ajaxElementLocatorFactory, this);

    }


    public void deleteProduct() {

        this.openAction();

        this.clickOnDeleteProduct();

        this.clickOnConfirmDeleteProduct();

    }

    public String checkProductTroughSku() {

        return driver.findElement(skuFromProductPage).getAttribute("value");


    }

}
