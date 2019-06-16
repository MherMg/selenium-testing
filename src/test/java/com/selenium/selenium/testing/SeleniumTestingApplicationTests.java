package com.selenium.selenium.testing;

import com.selenium.selenium.testing.service.SeleniumTestingService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SeleniumTestingService.class)
public class SeleniumTestingApplicationTests {

    @Autowired
    SeleniumTestingService seleniumTestingService;

    @After
    public void teardown() {
        seleniumTestingService.close();
    }

    @Test
    public void addProductFindBySkuAndDelete() {
        assertTrue(seleniumTestingService.addNewProductFindBySkuAndDelete());
    }

    @Test
    public void addProductAdvancedSearchAndDelete() {
        assertTrue(seleniumTestingService.addNewProductAdvancedSearchAndDelete());
    }

    @Test
    public void findByVendor() {
        List<String> vendors = seleniumTestingService.findByVendorTest();
        for (String vendor : vendors) {
            assertThat(vendor, is("Jemson"));

        }
    }

    @Test
    public void findByPrice() {
        List<String> prices = seleniumTestingService.findByPriceTest();
        for (String price : prices) {
            assertThat(price, is("86.29"));
        }
    }

}
