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

val BASE_URL = "http://localhost:3000";

class MainPageTest {
    private lateinit var chromeDriver: WebDriver
//    private lateinit var firefoxDriver: WebDriver

    private lateinit var landingPage: LandingPage
    private lateinit var legalNoticePage: LegalNoticePage
    private lateinit var enterPinCodePage: EnterPinCodePage


     fun chrome(): WebDriver {
        WebDriverManager.chromedriver().setup()
         //ChromeOptions chromeOptions...
        return ChromeDriver() //@todo add options
    }
//
//    fun firefox () : WebDriver {
//        WebDriverManager.firefoxdriver().setup()
//        return FirefoxDriver() //@todo add options
//    }



    @BeforeMethod
    fun setUp() {

        chromeDriver = chrome()
        chromeDriver.manage().window().maximize()
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
        chromeDriver.get(BASE_URL);

//        firefoxDriver = firefox()
//        firefoxDriver.manage().window().maximize()
//        firefoxDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
//        firefoxDriver.get("http://localhost:3000/")
//

    }

    @AfterMethod
    fun tearDown() {
        chromeDriver.quit()
//        firefoxDriver.quit()
    }

    @Test
    fun landingPageTest() {
        landingPage = LandingPage(chromeDriver)
        landingPage.clickJoinArena()
        legalNoticePage = LegalNoticePage(chromeDriver)
        legalNoticePage.clickContinue()
        enterPinCodePage = EnterPinCodePage(chromeDriver)
        enterPinCodePage.inputCode(listOf("1", "2", "3", "4"));

        chromeDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)

        //timer needed?

        val pinErrorLabel = "Please check the Code and try again."
        val isError = chromeDriver.findElement(By.cssSelector("[data-automation-id='check-pin-error-msg']")).text.equals(pinErrorLabel)

        assert(isError);
    }

//    @Test
//    fun toolsMenu() {
//        Actions(chromeDriver)
//            .moveToElement(mainPage.toolsMenu)
//            .perform()
//
//        val menuPopup = chromeDriver.findElement(By.className("menu-main__popup-wrapper"))
//        assertTrue(menuPopup.isDisplayed)
//    }
//
//    @Test
//    fun navigationToAllTools() {
//        mainPage.seeAllToolsButton.click()
//
//        val productsList = chromeDriver.findElement(By.className("products-list"))
//        assertTrue(productsList.isDisplayed)
//        assertEquals(chromeDriver.title, "All Developer Tools and Products by JetBrains")
//    }
}
