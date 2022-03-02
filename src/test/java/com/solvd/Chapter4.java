package com.solvd;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chapter4 {
    WebDriver driver;
    String url1 = "http://automationpractice.com/index.php";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url1);
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testNewWindowTab() {
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
        newWindow.get("http://automationpractice.com/index.php?controller=prices-drop");
        System.out.println("Title: " + driver.getTitle());

    }

    @Test
    public void testWorkingInBothWindowsTab() {

        // TODO: Automatically Open and Switch to the new Windows
        driver.switchTo().newWindow(WindowType.TAB)
                .get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        System.out.println("Title: " + driver.getTitle());

        // TODO: Work in the new window or tab

        driver.findElement(By.id("email_create")).sendKeys("Selenium4@TAU.com");
        driver.findElement(By.id("SubmitCreate")).click();

        // TODO: Get the window Id handels
        Set<String> allWindowTabs = driver.getWindowHandles();
        Iterator<String> iterate = allWindowTabs.iterator();
        String mainFirstWindow = iterate.next();
        System.out.println(mainFirstWindow);

        // TODO: Switch & Workin the main window or tab
        driver.switchTo().window(mainFirstWindow);
        driver.findElement(By.id("search_query_top")).sendKeys("Shirt");
        driver.findElement(By.name("submit_search")).click();
        System.out.println("Title: " + driver.getTitle());
    }

    /*
     * @AfterMethod
     * public void tearDown() {
     * driver.quit();
     * }
     */
}
