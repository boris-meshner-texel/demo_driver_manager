package com.example.demo_driver_manager

import io.github.bonigarcia.wdm.WebDriverManager
import org.testng.annotations.*
import org.testng.Assert.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.interactions.Actions
import java.util.concurrent.TimeUnit

class MainPageTest {
    private lateinit var chromeDriver: WebDriver
    private lateinit var firefoxDriver: WebDriver

    private lateinit var mainPage: MainPage


     fun chrome(): WebDriver {
        WebDriverManager.chromedriver().setup()
         //ChromeOptions chromeOptions...
        return ChromeDriver() //@todo add options


    }

    fun firefox () : WebDriver {
        WebDriverManager.firefoxdriver().setup()
        return FirefoxDriver() //@todo add options
    }



    @BeforeMethod
    fun setUp() {

        chromeDriver = chrome()
        chromeDriver.manage().window().maximize()
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        chromeDriver.get("http://localhost:3000/")

        firefoxDriver = firefox()
        firefoxDriver.manage().window().maximize()
        firefoxDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        firefoxDriver.get("http://localhost:3000/")




        mainPage = MainPage(chromeDriver)


    }

    @AfterMethod
    fun tearDown() {
        chromeDriver.quit()
        firefoxDriver.quit()
    }

    @Test
    fun search() {
        mainPage.searchButton.click()

        val searchField = chromeDriver.findElement(By.id("header-search"))
        searchField.sendKeys("Selenium")
        val submitButton = chromeDriver.findElement(By.xpath("//button[@type='submit' and text()='Search']"))
        submitButton.click()

        val searchPageField = chromeDriver.findElement(By.className("js-search-input"))
        assertEquals(searchPageField.getAttribute("value"), "Selenium")
    }

    @Test
    fun toolsMenu() {
        Actions(chromeDriver)
            .moveToElement(mainPage.toolsMenu)
            .perform()

        val menuPopup = chromeDriver.findElement(By.className("menu-main__popup-wrapper"))
        assertTrue(menuPopup.isDisplayed)
    }

    @Test
    fun navigationToAllTools() {
        mainPage.seeAllToolsButton.click()

        val productsList = chromeDriver.findElement(By.className("products-list"))
        assertTrue(productsList.isDisplayed)
        assertEquals(chromeDriver.title, "All Developer Tools and Products by JetBrains")
    }
}
