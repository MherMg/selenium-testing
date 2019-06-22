package com.selenium.selenium.testing.pages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 * Mher Petrosyan
 * Email mher13.02.94@gmail.ru
 */
public class ProductSearchPage {

    private WebDriver driver;

    private By productSearch = By.xpath("//*[@id=\"mainnav\"]/ul/li[4]/div/ul/li[2]/a");

    private By sku = By.name("sku");

    private By searchProductButton = By.xpath("//*[@id=\"searchform\"]/div[4]/button");

    private By clickInAdvancedSearch = By.xpath("//*[@id=\"content\"]/div[2]/div/div/div/div[2]/div[1]/div[2]/a");

    private By costFrom = By.name("purchasePriceFrom");
    private By costTo = By.name("purchasePriceTo");

    private By cratedFrom = By.name("createdFrom");
    private By createdTo = By.name("createdTo");

    private By priceFrom = By.name("priceFrom");
    private By priceTo = By.name("priceTo");

    private By title = By.name("title");

    private By checkDeletedProduct = By.xpath("//*[@id=\"searchform\"]/div[2]/div[1]");


    private By productsTbody = By.xpath("//*[@id=\"maintable\"]/tbody");
    private int tr = 1;
    private String pricesSt = String.format("//*[@id=\"maintable\"]/tbody/tr[%s]/td[9]", tr);
    private By pricesBy = By.xpath(pricesSt);

    public ProductSearchPage(WebDriver driver) {

        this.driver = driver;

    }

    private void openProductSearchPage() {

        driver.findElement(productSearch).click();
    }

    private void clickInAdvancedSearch() {

        driver.findElement(clickInAdvancedSearch).click();

    }

    private void setSku(String strSku) {

        driver.findElement(sku).sendKeys(strSku);

    }

    private void setCostFrom(String strCostFrom) {

        driver.findElement(costFrom).sendKeys(strCostFrom);

    }

    private void setCostTo(String strCostTo) {

        driver.findElement(costTo).sendKeys(strCostTo);

    }

    private void setPriceFrom(String strPriceFrom) {

        driver.findElement(priceFrom).sendKeys(strPriceFrom);

    }

    private void setPriceTo(String strPriceTo) {

        driver.findElement(priceTo).sendKeys(strPriceTo);

    }

    private void setCreatedFrom(String strCratedFrom) {

        driver.findElement(cratedFrom).sendKeys(strCratedFrom);

    }

    private void setCreatedTo(String strCreatedTo) {

        driver.findElement(createdTo).sendKeys(strCreatedTo);

    }

    private void setTitle(String strTitle) {

        driver.findElement(title).sendKeys(strTitle);

    }


    private void clickSearchButton() {

        driver.findElement(searchProductButton).click();

    }

    public String checkDeletedProduct() {

        return driver.findElement(checkDeletedProduct).getText();

    }

    public void searchProductBySku(String strSku) {

        this.openProductSearchPage();

        this.setSku(strSku);

        this.clickSearchButton();
    }

    public List<String> findByPriceAndCheck(String priceFrom, String priceTo) {
        this.openProductSearchPage();

        this.setPriceFrom(priceFrom);
        this.setPriceTo(priceTo);

        this.clickSearchButton();

        driver.findElement(productsTbody);
        Document document = Jsoup.parse(driver.getPageSource());
        Element body = document.body();
        int priceSize = body.getElementsByClass("psearch_price").size();

        if (priceSize != 0) {

            List<String> prices = new ArrayList<>();
            for (int i = 0; i < priceSize; i++) {
                prices.add(driver.findElement(pricesBy).getText());
                tr = tr + 1;
            }
            return prices;
        }
        return null;
    }

    public void advancedSearch(String sku, String costFrom, String costTo, String createdFrom, String createdTo, String title) {

        this.openProductSearchPage();

        this.clickInAdvancedSearch();

        this.setSku(sku);

        this.setCostFrom(costFrom);

        this.setCostTo(costTo);

        this.setCreatedFrom(createdFrom);

        this.setCreatedTo(createdTo);

        this.setTitle(title);

        this.clickSearchButton();
    }
}
