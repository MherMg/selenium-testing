package com.selenium.selenium.testing;

import com.selenium.selenium.testing.pages.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
public class SeleniumTestingApplicationTests {

    private WebDriver driver;

    private LoginPage loginPage;

    private DashboardPage dashboardPage;

    private AddNewProductPage addNewProductPage;

    private ProductPage productPage;

    private ProductSearchPage productSearchPage;

    static {

    }
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://bobs.myjazva.com/admin/");
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        this.driver.close();
    }


    /**
     * This test case will login in https://bobs.myjazva.com/admin/
     * <p>
     * Login
     * <p>
     * Verify the dashboard page using username
     */

    @Test
    public void loginAndGoDashboard() {
        loginPage = new LoginPage(driver);
        //login
        loginPage.loginToBobsJazva("root", "jz191");

        // go the dashboard page
        dashboardPage = new DashboardPage(driver);

        //Verify dashboard page
        Assert.assertTrue(dashboardPage.getUserNameFromDashboardPage().toLowerCase().contains("jazva admin"));
    }

    /**
     * This test case will login in https://bobs.myjazva.com/admin/
     * Login
     * Verify the dashboard page using username
     * Adds new product and verify this product
     * Deletes product and verify that deleted
     */
    @Test
    public void addNewProductVerifyAndDelete() {
        loginPage = new LoginPage(driver);
        //login
        loginPage.loginToBobsJazva("root", "jz191");

        // go the dashboard page
        dashboardPage = new DashboardPage(driver);

        //Verify dashboard page
        Assert.assertTrue(dashboardPage.getUserNameFromDashboardPage().toLowerCase().contains("jazva admin"));

        dashboardPage.openProductsFromDashboard();

        //Adds new product
        addNewProductPage = new AddNewProductPage(driver);

        addNewProductPage.addNewProduct("for test 0001", "0001 for test", "1");

        productPage = new ProductPage(driver);

        //Verify added product
        Assert.assertTrue(productPage.checkProductTroughSku().toLowerCase().contains("0001 for test"));

        //delete product
        productPage.deleteProduct();

        //Verify that deleted
        productSearchPage = new ProductSearchPage(driver);
        Assert.assertTrue(productSearchPage.checkDeletedProduct().toLowerCase().contains("deleted for test 0001 successfully."));
    }

    /**
     * This test case will login in https://bobs.myjazva.com/admin/
     * Login
     * Verify the dashboard page using username
     * Adds new product and verify added product
     * Search product by sku
     * Delete product and verify deleted
     */
    @Test
    public void addProductFindBySkuAndDelete() {
        loginPage = new LoginPage(driver);
        //login
        loginPage.loginToBobsJazva("root", "jz191");

        // go the dashboard page
        dashboardPage = new DashboardPage(driver);

        //Verify dashboard page
        Assert.assertTrue(dashboardPage.getUserNameFromDashboardPage().toLowerCase().contains("jazva admin"));

        dashboardPage.openProductsFromDashboard();

        //adds new product
        addNewProductPage = new AddNewProductPage(driver);

        addNewProductPage.addNewProduct("for test 0002", "0002 for test", "2");

        productPage = new ProductPage(driver);

        //Verify added product
        Assert.assertTrue(productPage.checkProductTroughSku().toLowerCase().contains("0002 for test"));

        //Search product
        productSearchPage = new ProductSearchPage(driver);

        productSearchPage.searchProductBySku("0002 for test");

        //Click in product
        productPage.clickInProduct();

        //delete product
        productPage.deleteProduct();

        //Verify that deleted
        Assert.assertTrue(this.productSearchPage.checkDeletedProduct().toLowerCase().contains("deleted for test 0002 successfully."));
    }

    /**
     * This test case will login in https://bobs.myjazva.com/admin/
     * Login
     * Verify the dashboard page using username
     * Adds new product and verify added product
     * Advanced search by sku,cost,crated,title
     * Delete product and verify deleted
     */
    @Test
    public void addProductAdvancedSearchAndDelete() {
        loginPage = new LoginPage(driver);
        //login
        loginPage.loginToBobsJazva("root", "jz191");

        // go the dashboard page
        dashboardPage = new DashboardPage(driver);

        //Verify dashboard page
        Assert.assertTrue(dashboardPage.getUserNameFromDashboardPage().toLowerCase().contains("jazva admin"));

        dashboardPage.openProductsFromDashboard();

        //adds new product
        addNewProductPage = new AddNewProductPage(driver);

        addNewProductPage.addNewProduct("for test 0003", "0003 for test", "3");

        productPage = new ProductPage(driver);

        //Verify added product
        Assert.assertTrue(productPage.checkProductTroughSku().toLowerCase().contains("0003 for test"));

        //Search product
        productSearchPage = new ProductSearchPage(driver);

        productSearchPage.advancedSearch("0003 for test", "3", "3", "06.22.2019", "06.22.2019", "for test 0003");

        //Click in product
        productPage.clickInProduct();

        //delete product
        productPage.deleteProduct();

        //Verify that deleted
        Assert.assertTrue(productSearchPage.checkDeletedProduct().toLowerCase().contains("deleted for test 0003 successfully."));
    }

    /**
     * This test case will login in https://bobs.myjazva.com/admin/
     * Login
     * Verify the dashboard page using username
     * search byy price and checks prices
     */
    @Test
    public void findByPriceAndCheckPrices() {
        loginPage = new LoginPage(driver);
        //login
        loginPage.loginToBobsJazva("root", "jz191");

        // go the dashboard page
        dashboardPage = new DashboardPage(driver);

        //Verify dashboard page
        Assert.assertTrue(dashboardPage.getUserNameFromDashboardPage().toLowerCase().contains("jazva admin"));

        dashboardPage.openProductsFromDashboard();

        productPage = new ProductPage(driver);

        //Search product
        productSearchPage = new ProductSearchPage(driver);

        //Check prices
        List<String> prices = productSearchPage.findByPriceAndCheck("86.29", "86.29");

        for (String price : prices) {
            Assert.assertTrue(price.contains("86.29"));
        }
    }

}
