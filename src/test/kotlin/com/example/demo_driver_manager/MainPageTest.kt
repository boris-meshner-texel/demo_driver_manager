package com.example.demo_driver_manager

import java.net.URL
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

import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.remote.IOSMobileCapabilityType
import io.appium.java_client.AppiumDriver
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.pagefactory.*
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.WebElement


val BASE_URL = "http://192.168.1.138:3000";

class MainPageTest {
    private lateinit var driver: AppiumDriver<MobileElement>


    private lateinit var chromeDriver: WebDriver

    ////    private lateinit var firefoxDriver: WebDriver

    private lateinit var landingPage: LandingPage
    private lateinit var legalNoticePage: LegalNoticePage
    private lateinit var enterPinCodePage: EnterPinCodePage
    private lateinit var pairingInfoPage: PairingInfoPage
    private lateinit var chatroomPage: ChatroomPage


     fun chrome(): WebDriver {
        WebDriverManager.chromedriver().setup()
         //ChromeOptions chromeOptions...
        return ChromeDriver() //@todo add options
    }


//    fun firefox () : WebDriver {
//        WebDriverManager.firefoxdriver().setup()
//        return FirefoxDriver() //@todo add options
//    }

    fun initRealDevice() {
        val desiredCapabilities = DesiredCapabilities()
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOs")
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.4.2")
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "John Dodds’s iPhone (14.4.2)")

        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest")

        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "00008030-00063C3A1A98402E")
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari")
        desiredCapabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, "HN384CSA45")
        desiredCapabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, "iPhone Developer")

        desiredCapabilities.setCapability("updatedWDABundleId", "io.texel.texel1")

        val url = URL("http://192.168.1.138:4723/wd/hub")
        driver = AppiumDriver<MobileElement>(url, desiredCapabilities)
    }

    fun initSimulator() {
        val desiredCapabilities = DesiredCapabilities()
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOs")
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.4.2")
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 12 (14.4)")
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest")

        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "E6FF6BF5-1D9C-438F-BF08-074558BF435C")
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari")
        desiredCapabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, "HN384CSA45")
        desiredCapabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, "iPhone Developer")

        val url = URL("http://192.168.1.138:4723/wd/hub")
        driver = AppiumDriver<MobileElement>(url, desiredCapabilities)
    }

    @BeforeMethod
    fun setUp() {

//        chromeDriver = chrome()
//        chromeDriver.manage().window().maximize()
//        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
//        chromeDriver.get(BASE_URL);

//        firefoxDriver = firefox()
//        firefoxDriver.manage().window().maximize()
//        firefoxDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
//        firefoxDriver.get("http://localhost:3000/")
//

        initSimulator()
    }

    @AfterMethod
    fun tearDown() {
//        chromeDriver.quit()
//        firefoxDriver.quit()
        driver.quit()
    }

    @Test
    fun landingPageTest() {
        driver.get(BASE_URL)
        landingPage = LandingPage(driver)
        landingPage.clickJoinArena()
        legalNoticePage = LegalNoticePage(driver)
        legalNoticePage.clickContinue()
        enterPinCodePage = EnterPinCodePage(driver)
        enterPinCodePage.inputCode(listOf("1", "2", "3", "4"));

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)

        val pinErrorLabel = "Please check the Code and try again."
        val isError = driver.findElement(By.cssSelector("[data-automation-id='check-pin-error-msg']")).text.equals(pinErrorLabel)

        assert(isError);

        driver.get(BASE_URL+"/pairing-info");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)

        pairingInfoPage = PairingInfoPage(driver)

        val step1HeaderText = "Start the Magenta Sport App"
        val actualStep1HeaderText =  driver.findElements(By.cssSelector("[data-automation-id='h4-header']"))[0].text;
        assert(actualStep1HeaderText.equals(step1HeaderText));

        //TODO add image check
        //TODO add main text check

        pairingInfoPage.clickContinueToStep2Button()
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)

        val step2HeaderText = "Join the Virtual Arena"
        val actualStep2HeaderText =  driver.findElements(By.cssSelector("[data-automation-id='h4-header']"))[1].text;
        assert(actualStep2HeaderText.equals(step2HeaderText));

        //TODO add image check
        //TODO add main text check

        pairingInfoPage.clickContinueToStep3Button()
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)
        val step3HeaderText = "Enter the PIN code"
        val actualStep3HeaderText =  driver.findElements(By.cssSelector("[data-automation-id='h4-header']"))[2].text;
        assert(actualStep2HeaderText.equals(step2HeaderText));

        //TODO add image check
        //TODO add main text check

        //TODO add close button check

        pairingInfoPage.clickContinueToEnterPinButton()

        driver.get(BASE_URL+"/chatroom");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)

        chatroomPage = ChatroomPage(driver)
        chatroomPage.enterChatText("test123456");
        assert(chatroomPage.charsEntered.text.equals("10/140"));

        chatroomPage.clickBackButton();

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
