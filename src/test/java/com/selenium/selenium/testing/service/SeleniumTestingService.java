package com.selenium.selenium.testing.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by
 * Mher Petrosyan
 * Email mher13.02.94@gmail.ru
 */
@Service
public class SeleniumTestingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeleniumTestingService.class);

    @Value("${url}")
    private String url;
    @Value("${login}")
    private String login;
    @Value("${password}")
    private String password;
    private WebDriver driver;


    static {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");

    }

    /**
     * This method for login.
     */
    void login() {

        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);

        driver.get(url);
        driver.manage().window().maximize();
        LOGGER.info("opening browser");

        driver.findElement(By.id("username")).sendKeys(login);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[2]/div[2]/div[2]/div[1]/form/div[3]/input")).click();
        LOGGER.info("successfully login");
    }

    /**
     * This method adds a new product.
     * After that, find this product through with the sku.
     * Then removes this product.
     */
    public boolean addNewProductFindBySkuAndDelete() {

        login();

//     products -> add new product
        driver.findElement(By.xpath("//*[@id=\"mainnav\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"mainnav\"]/ul/li[4]/div/ul/li[7]/a")).click();

//     input values product
        driver.findElement(By.name("new_title")).sendKeys("for test");
        driver.findElement(By.xpath("//*[@id=\"itemOrKit\"]/div/div[1]/div[1]/div/select/option[7]")).click();
        driver.findElement(By.name("new_vendorSku")).sendKeys("for test sku");
        driver.findElement(By.name("new_cost")).sendKeys("10000.1");

//     create button
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div/div/div[3]/form/div[3]/input")).click();
        LOGGER.info("product successfully added");

//     product search
        driver.findElement(By.xpath("//*[@id=\"mainnav\"]/ul/li[4]/div/ul/li[2]/a")).click();

//     sku
        driver.findElement(By.name("sku")).sendKeys("for test sku");

//     search products button
        driver.findElement(By.xpath("//*[@id=\"searchform\"]/div[4]/button")).click();

//     click in product
        driver.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr/td[6]/a")).click();

//     actions -> delete product
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div/div/div[1]/div[1]/div/a[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div/div/div[1]/div[1]/div/ul/li[5]/a")).click();

        //     confirm delete product
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"confirm-delete-product\"]/div/div/div[3]/a[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        LOGGER.info("product successfully removed");

        return true;
    }


    /**
     * This method adds a new product.
     * After that, find this product through with the advanced search.
     * Then removes this product.
     */
    public boolean addNewProductAdvancedSearchAndDelete() {

        login();

//     products -> add new product
        driver.findElement(By.xpath("//*[@id=\"mainnav\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"mainnav\"]/ul/li[4]/div/ul/li[7]/a")).click();

//     input values product
        driver.findElement(By.name("new_title")).sendKeys("for test");
        driver.findElement(By.xpath("//*[@id=\"itemOrKit\"]/div/div[1]/div[1]/div/select/option[7]")).click();

        driver.findElement(By.name("new_vendorSku")).sendKeys("for test sku 2");

        driver.findElement(By.name("new_cost")).sendKeys("100.1");

        driver.findElement(By.xpath("//*[@id=\"addItem\"]/div/div[1]/div/div/div/div[1]/div/div/select/option[2]")).click();

//     create button
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div/div/div[3]/form/div[3]/input")).click();
        LOGGER.info("product successfully added");

//     product search
        driver.findElement(By.xpath("//*[@id=\"mainnav\"]/ul/li[4]/div/ul/li[2]/a")).click();

//     advanced search
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div/div/div[2]/div[1]/div[2]/a")).click();

//     sku
        driver.findElement(By.name("sku")).sendKeys("for test sku 2");
//     cost
        driver.findElement(By.name("purchasePriceFrom")).sendKeys("100.1");
        driver.findElement(By.name("purchasePriceTo")).sendKeys("100.1");
//     created
        driver.findElement(By.name("createdFrom")).sendKeys("06.15.2019");
        driver.findElement(By.name("createdTo")).sendKeys("06.15.2019");
//     attribute
        driver.findElement(By.xpath("//*[@id=\"secondary\"]/div[1]/div[3]/div/select/option[2]")).click();
        driver.findElement(By.name("attributeValue")).sendKeys("red");

//     search products button
        driver.findElement(By.xpath("//*[@id=\"searchform\"]/div[4]/button")).click();

//     click in product
        driver.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr/td[6]/a")).click();
        LOGGER.info("product found");

//     actions -> delete product
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div/div/div[1]/div[1]/div/a[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div/div/div[1]/div[1]/div/ul/li[5]/a")).click();

//     confirm delete product
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement element = driver.findElement(By.xpath("//*[@id=\"confirm-delete-product\"]/div/div/div[3]/a[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        LOGGER.info("product successfully removed");

        return true;
    }

    /**
     * This method  find products through with the vendor Jemson.
     */
    public List<String> findByVendorTest() {

        login();

//     products-> search product
        driver.findElement(By.xpath("//*[@id=\"mainnav\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"mainnav\"]/ul/li[4]/div/ul/li[2]/a")).click();

//     vendor - Jemson
        driver.findElement(By.xpath("//*[@id=\"searchform\"]/div[2]/div[1]/div[2]/div/select/option[7]")).click();
//     search button
        driver.findElement(By.xpath("//*[@id=\"searchform\"]/div[4]/button")).click();

//     all vendors from this page
        driver.findElement(By.xpath("//*[@id=\"maintable\"]/tbody"));
        Document document = Jsoup.parse(driver.getPageSource());
        Element body = document.body();
        int vendorSize = body.getElementsByClass("psearch_vendor").size();
        LOGGER.info("vendors size: [{}]", vendorSize);

        int tr = 1;
        List<String> vendors = new ArrayList<>();
        for (int i = 0; i < vendorSize; i++) {
            vendors.add(driver.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr[" + tr + "]/td[5]")).getText());
        }
        return vendors;
    }

    /**
     * This method  find products through with the price 86.29.
     */
    public List<String> findByPriceTest() {

        login();

//     products-> search product
        driver.findElement(By.xpath("//*[@id=\"mainnav\"]/ul/li[4]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"mainnav\"]/ul/li[4]/div/ul/li[2]/a")).click();

//     price from - to
        driver.findElement(By.name("priceFrom")).sendKeys("86.29");
        driver.findElement(By.name("priceTo")).sendKeys("86.29");

//     search products button
        driver.findElement(By.xpath("//*[@id=\"searchform\"]/div[4]/button")).click();

//     all prices from this page
        driver.findElement(By.xpath("//*[@id=\"maintable\"]/tbody"));
        Document document = Jsoup.parse(driver.getPageSource());
        Element body = document.body();
        int priceSize = body.getElementsByClass("psearch_price").size();
        LOGGER.info("price size: [{}]", priceSize);


        int tr = 1;
        List<String> prices = new ArrayList<>();
        for (int i = 0; i < priceSize; i++) {
            prices.add(driver.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr[" + tr + "]/td[9]")).getText());
        }
        return prices;
    }

    /**
     * This method  closes the driver.
     */
    public void close() {
        this.driver.close();
    }

}
