package com.example.demo_driver_manager

import java.net.URL
import io.github.bonigarcia.wdm.WebDriverManager
import org.testng.annotations.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import java.util.concurrent.TimeUnit

import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.remote.IOSMobileCapabilityType
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import org.openqa.selenium.remote.DesiredCapabilities
import com.fasterxml.jackson.databind.ObjectMapper
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

import com.google.gson.JsonParser;

import org.openqa.selenium.support.ui.ExpectedConditions

import org.openqa.selenium.support.ui.WebDriverWait




val BASE_URL = "http://192.168.1.138:3000";
val API_URL = "https://coviewdev.texel.live/v1";


class MainPageTest {
    private lateinit var hostDriver: AppiumDriver<MobileElement>
    private lateinit var guestDriver: AppiumDriver<MobileElement>


    private lateinit var chromeDriver: WebDriver

    ////    private lateinit var firefoxDriver: WebDriver

    private lateinit var hostLandingPage: LandingPage
    private lateinit var hostLegalNoticePage: LegalNoticePage
    private lateinit var hostEnterPinCodePage: EnterPinCodePage
    private lateinit var hostPairingInfoPage: PairingInfoPage
    private lateinit var hostCreateUserPage: CreateUserPage
    private lateinit var hostChatroomPage: ChatroomPage


     fun chrome(): WebDriver {
        WebDriverManager.chromedriver().setup()
         //ChromeOptions chromeOptions...
        return ChromeDriver() //@todo add options
    }


    fun firefox () : WebDriver {
        WebDriverManager.firefoxdriver().setup()
        return FirefoxDriver() //@todo add options
    }

    fun initIosRealHostDevice() {
        val desiredCapabilities = DesiredCapabilities()
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOs")
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.4.2") //CHANGE THESE TO YOUR DEVICE DETAILS
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "John Dodds’s iPhone (14.4.2)") //CHANGE THESE TO YOUR DEVICE DETAILS

        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest")

        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "00008030-00063C3A1A98402E") //CHANGE THESE TO YOUR DEVICE DETAILS
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari")
        desiredCapabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, "HN384CSA45")
        desiredCapabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, "iPhone Developer")

        desiredCapabilities.setCapability("updatedWDABundleId", "io.texel.texel1")

        val url = URL("http://192.168.1.138:4723/wd/hub")
        hostDriver = AppiumDriver<MobileElement>(url, desiredCapabilities)
    }

    fun initIosRealGuestDevice() {
        val desiredCapabilities = DesiredCapabilities()
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOs")
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.4.2") //CHANGE THESE TO YOUR DEVICE DETAILS
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "John Dodds’s iPhone (14.4.2)") //CHANGE THESE TO YOUR DEVICE DETAILS

        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest")

        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "00008030-00063C3A1A98402E") //CHANGE THESE TO YOUR DEVICE DETAILS
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari")
        desiredCapabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, "HN384CSA45")
        desiredCapabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, "iPhone Developer")

        desiredCapabilities.setCapability("updatedWDABundleId", "io.texel.texel1")

        val url = URL("http://192.168.1.138:4723/wd/hub")
        guestDriver = AppiumDriver<MobileElement>(url, desiredCapabilities)
    }

    fun initIosHostSimulator() {
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
        hostDriver = AppiumDriver<MobileElement>(url, desiredCapabilities)
    }

    fun initIosGuestSimulator() {
        val desiredCapabilities = DesiredCapabilities()
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOs")
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.4.2")
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11 (14.4)")
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest")

        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "ED27BF8D-D6E9-4FB3-AE0E-9076C69E93A1")
        desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari")
        desiredCapabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, "HN384CSA45")
        desiredCapabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, "iPhone Developer")

        val url = URL("http://192.168.1.138:4723/wd/hub")
        guestDriver = AppiumDriver<MobileElement>(url, desiredCapabilities)
    }


    fun initAndroidHostSimulator() {
        val capabilities = DesiredCapabilities()
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0")
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_3a_API_30_x86")
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2")
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome")

        val url = URL("http://192.168.1.138:4723/wd/hub")
        hostDriver = AppiumDriver<MobileElement>(url, capabilities)
    }

    fun initAndroidGuestSimulator() {
        val capabilities = DesiredCapabilities()
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android")
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0")
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_3a_API_30_x86")
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2")
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome")

        val url = URL("http://192.168.1.138:4723/wd/hub")
        guestDriver = AppiumDriver<MobileElement>(url, capabilities)
    }


    @BeforeMethod
    fun setUp() {

        //OUTDATED DESKTOP STYLE TESTS
//        chromeDriver = chrome()
//        chromeDriver.manage().window().maximize()
//        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
//        chromeDriver.get(BASE_URL);

//        firefoxDriver = firefox()
//        firefoxDriver.manage().window().maximize()
//        firefoxDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
//        firefoxDriver.get("http://localhost:3000/")
//

        initIosHostSimulator()
        initAndroidGuestSimulator()
    }

    @AfterMethod
    fun tearDown() {
//        chromeDriver.quit()
//        firefoxDriver.quit()
        hostDriver.quit()
    }

    //TODO move api to other class

    fun ApiGenerateEngagementToken(userId: String, deviceId: String): String {
        val values = mapOf(
            "applicationKey" to "some-uuid",
            "userId" to userId,
            "deviceId" to deviceId)

        val objectMapper = ObjectMapper()
        val requestBody: String = objectMapper
            .writeValueAsString(values)

        println(requestBody);

        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL+"/auth/generateEngagementToken"))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .header("Content-Type", "application/json")
            .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val jsonString = response.body()

        println(jsonString)

        val responseObject = JsonParser.parseString(jsonString).asJsonObject
        val accessToken = responseObject.get("auth").asJsonObject.get("accessToken").asString

        println("Access Token: "+accessToken);

        return accessToken
    }

    fun ApiUserConnect(authToken: String, deviceId: String): String {
        val values = mapOf("device" to mapOf(
            "id" to deviceId,
            "name" to "sdk_gphone_x86_arm",
            "platform" to "ANDROID",
            "capabilities" to mapOf(
                "MEDIA_SYNC" to "READ_WRITE",
                "MESSENGER" to "READ_WRITE"
            ))
            ,
            "clientProtocols" to listOf("FIRESTORE")
        );

        val objectMapper = ObjectMapper()
        val requestBody: String = objectMapper
            .writeValueAsString(values)

        println(requestBody);

        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL+"/users/connect"))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .header("Content-Type", "application/json")
            .header("Authorization", authToken)
            .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        val jsonString = response.body()

        println(jsonString)

        val responseObject = JsonParser.parseString(jsonString).asJsonObject
        val token = responseObject.get("asyncProtocol").asJsonObject.get("config").asJsonObject.get("token").asString

        return token
    }

    fun ApiAddDeviceGroup(authToken: String, userId: String, deviceId: String): String {
        val values = mapOf(
            "deviceId" to deviceId,
        )

        val objectMapper = ObjectMapper()
        val requestBody: String = objectMapper
            .writeValueAsString(values)

        println(requestBody);

        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL+"/users/"+userId+"/deviceGroups"))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .header("Content-Type", "application/json")
            .header("Authorization", authToken)
            .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        val jsonString = response.body()

        println(jsonString)

        val responseObject = JsonParser.parseString(jsonString).asJsonObject
        val deviceGroupId = responseObject.get("deviceGroupId").asString

        return deviceGroupId
    }

    fun ApiGeneratePin(authToken: String, deviceGroupId: String, userId: String): String {
        val requestBody = "{\n" +
                "  \"payload\": \"{" +
                "\\\"userId\\\":\\\""+userId+"\\\"," +
                "\\\"deviceGroupId\\\":\\\""+deviceGroupId+"\\\"," +
                "\\\"content\\\":{" +
                "\\\"id\\\":\\\"1111\\\"," +
                "\\\"playbackUrls\\\":[\\\"https://svc43.main.sl.t-online.de/dlt3/out/u/hssfcbayern01_fm.mpd\\\"]}," +
                "\\\"roomInformation\\\":{" +
                "\\\"homeTeam\\\":\\\"FC Würzburger Kickers\\\"," +
                "\\\"homeTeamLogoUrl\\\":\\\"https://stg-zeus-telekomsport-de.laola1.at/images/editorial/Mataracan/Logos_neu/FWK200x200.png?time=1544545891822&h=150\\\"," +
                "\\\"awayTeam\\\":\\\"FC Bayern München II\\\"," +
                "\\\"awayTeamLogoUrl\\\":\\\"https://stg-zeus-telekomsport-de.laola1.at/images/editorial/Logos/Fussball/Bundesliga/01_fc_bayern_200x200.png?time=1562156010808&h=150\\\"," +
                "\\\"eventName\\\":\\\"FSV Zwickau - FC Ingolstadt\\\"," +
                "\\\"competitionName\\\":\\\"3. Liga Spieltag 15\\\"," +
                "\\\"scheduledStartTime\\\":\\\"1563623100000\\\"}}\",\n" +
                "  \"expiration\": 0\n" +
                "}"

        println(requestBody);

        val client = HttpClient.newBuilder().build();
        val request = HttpRequest.newBuilder()
            .uri(URI.create(API_URL+"/pin"))
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .header("Content-Type", "application/json")
            .header("Authorization", authToken)
            .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        val jsonString = response.body()

        println(jsonString)

        val responseObject = JsonParser.parseString(jsonString).asJsonObject
        val pin = responseObject.get("pin").asString
        println("PIN: "+pin)
        return pin
    }

    @Test
    fun happyPathChatTest() {
        hostDriver.get(BASE_URL)
        hostLandingPage = LandingPage(hostDriver)

        hostDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        hostDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //TODO add tests here

        hostLandingPage.clickJoinArena()
        hostLegalNoticePage = LegalNoticePage(hostDriver)

        //TODO add tests here

        hostLegalNoticePage.clickContinue()
        hostEnterPinCodePage = EnterPinCodePage(hostDriver)

        //TODO add more tests here (text content etc)

        val hostUserId = "myUser1";
        val hostDeviceId = "myDevice1";
        val hostAuthToken = ApiGenerateEngagementToken(hostUserId, hostDeviceId);
        ApiUserConnect(hostAuthToken, hostDeviceId)
        val hostDeviceGroupId = ApiAddDeviceGroup(hostAuthToken, hostUserId, hostDeviceId)
        val hostPin = ApiGeneratePin(hostAuthToken, hostDeviceGroupId, hostUserId)


        hostEnterPinCodePage.inputCode(listOf("1", "2", "3", "4"));

        hostDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)

        val pinErrorLabel = "Please check the Code and try again."
        val isError = hostDriver.findElement(By.cssSelector("[data-automation-id='check-pin-error-msg']")).text.equals(pinErrorLabel)

        assert(isError);

        hostEnterPinCodePage.inputCode(listOf(hostPin[0].toString(), hostPin[1].toString(), hostPin[2].toString(), hostPin[3].toString()));
        hostDriver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS)

        hostCreateUserPage = CreateUserPage(hostDriver)

        //TODO add secondary tests here
        //TODO check header text value
        //TODO check main text value
        //TODO check help button works

        val hostUsername = "host"+Math.round(Math.random()*9999).toString();
        hostCreateUserPage.enterUsername(hostUsername)
        hostCreateUserPage.clickJoinButton()

        hostChatroomPage = ChatroomPage(hostDriver)

        //check name of chat host is right
        assert(hostChatroomPage.createdTheChatText.text.equals(hostUsername+" has created the Arena"))

        val chatInput = "This is a test"
        hostDriver.executeScript("window.enterText('"+chatInput+"');")

        //check chat length is right
        assert(hostChatroomPage.charsEntered.text.equals(chatInput.length.toString()+"/140"))
        hostChatroomPage.sendChat()

        WebDriverWait(
            hostDriver,
            10
        ).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("[data-automation-id='message-content']"), 0))

        // check message has been sent
        val chatMessageText =  hostDriver.findElements(By.cssSelector("[data-automation-id='message-content']"))[0].text;
        assert(chatMessageText.equals(chatInput));

        assert(hostChatroomPage.createdTheChatText.text.equals(hostUsername+" has created the Arena"))

        //TODO check share button

        //PARTICIPANT FLOW
        val guestUserId = "guser"+Math.round(Math.random()*9999).toString();
        val guestDeviceId = "gdevice"+Math.round(Math.random()*9999).toString();
        val guestAuthToken = ApiGenerateEngagementToken(guestUserId, guestDeviceId);
        ApiUserConnect(guestAuthToken, guestDeviceId)
        val guestDeviceGroupId = ApiAddDeviceGroup(guestAuthToken, guestUserId, guestDeviceId)
        val guestPin = ApiGeneratePin(guestAuthToken, guestDeviceGroupId, guestUserId)

        //TODO check that this actually copies to clipboard here -- hidden field hack works for now
//        chatroomPage.clickShareButton()
        val sharedUrl = hostChatroomPage.shareUrlField.getAttribute("value")

        println("share url: "+sharedUrl)

        guestDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        guestDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        guestDriver.get(sharedUrl.replace("https://", "http://", true))


        val guestLandingPage = LandingPage(guestDriver)
        guestLandingPage.clickJoinArena()

        val guestLegalNoticePage = LegalNoticePage(guestDriver)
        guestLegalNoticePage.clickContinue()

        val guestUsername = "guest"+Math.round(Math.random()*9999).toString()
        val guestCreateUserPage = CreateUserPage(guestDriver)
        guestCreateUserPage.enterUsername(guestUsername)
        guestCreateUserPage.clickJoinButton()

        val guestChatroomPage = ChatroomPage(guestDriver)
        //TODO add tests for popup
        //TODO check participants list

        guestChatroomPage.clickPairTvButton();

        val guestPairingInfoPage = PairingInfoPage(guestDriver)

        val step1HeaderText = "Start the Magenta Sport App"
        val actualStep1HeaderText =  guestDriver.findElements(By.cssSelector("[data-automation-id='card-h4-header']"))[0].text;
        assert(actualStep1HeaderText.equals(step1HeaderText));

        val step1SubText = "In order for you to watch the same game with your friends, it is necessary that you join the Virtual Arena on your TV."
        val actualStep1SubText =  guestDriver.findElements(By.cssSelector("[data-automation-id='card-subtext']"))[0].text;
        assert(actualStep1SubText.equals(step1SubText));

        //TODO add image check

        guestPairingInfoPage.clickContinueToStep2Button()


        //TODO the swiper here behaves unexpectedly sometimes leading to the indexes changing
        //TODO these text tests should be rewritten in a more consistent way

        val step2HeaderText = "Join the Virtual Arena"

        val actualStep2HeaderText =  guestDriver.findElements(By.cssSelector("[data-automation-id='card-h4-header']"))[1].text;
//        assert(actualStep2HeaderText.equals(step2HeaderText));

        val step2SubText = "In order for you to watch the same game with your friends, it is necessary that you join the Virtual Arena on your TV."
        val actualStep2SubText =  guestDriver.findElements(By.cssSelector("[data-automation-id='card-subtext']"))[1].text;
//        assert(actualStep1SubText.equals(step1SubText));

        //TODO add image check

        guestPairingInfoPage.clickContinueToStep3Button()

        val step3HeaderText = "Enter the PIN code"
        val actualStep3HeaderText =  guestDriver.findElements(By.cssSelector("[data-automation-id='card-h4-header']"))[2].text;
//        assert(actualStep2HeaderText.equals(step2HeaderText));

        val step3SubText = "Enter the Pin that is displayed on your TV to connect your devices for the Virtual Arena."
        val actualStep3SubText =  guestDriver.findElements(By.cssSelector("[data-automation-id='card-subtext']"))[2].text;
//        assert(actualStep1SubText.equals(step1SubText));

        guestPairingInfoPage.clickContinueToEnterPinButton()

        val guestEnterPinCodePage = EnterPinCodePage(guestDriver)
        guestEnterPinCodePage.inputCode(listOf(guestPin[0].toString(), guestPin[1].toString(), guestPin[2].toString(), guestPin[3].toString()));

        //CHECK for first message
        WebDriverWait(
            guestDriver,
            10
        ).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("[data-automation-id='message-content']"), 0))

        val message1 = guestDriver.findElements(By.cssSelector("[data-automation-id='message-content']"))[0].text;
        assert(message1.equals(chatInput))

        guestChatroomPage.clickParticipantsButton();
        hostChatroomPage.clickParticipantsButton();


        val guestParticipant1 = guestDriver.findElements(By.cssSelector("[data-automation-id='participant-name']"))[0].text;
        val guestParticipant2 = guestDriver.findElements(By.cssSelector("[data-automation-id='participant-name']"))[1].text;
        val hostParticipant1 = hostDriver.findElements(By.cssSelector("[data-automation-id='participant-name']"))[0].text;
        val hostParticipant2 = hostDriver.findElements(By.cssSelector("[data-automation-id='participant-name']"))[1].text;

        //check participants
        assert(guestParticipant1.equals(hostParticipant1) && hostParticipant1.equals(hostUsername));
        assert(guestParticipant2.equals(hostParticipant2) && hostParticipant2.equals(guestUsername));

        guestChatroomPage.clickHideTrayButton()
        hostChatroomPage.clickHideTrayButton()

        val guestChatInput = "testing 123"
        guestDriver.executeScript("window.enterText('"+guestChatInput+"');")
        guestChatroomPage.sendChat()

        WebDriverWait(
            hostDriver,
            10
        ).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("[data-automation-id='message-content']"), 1))

        //TODO check the chat was received on host
        val message2 = hostDriver.findElements(By.cssSelector("[data-automation-id='message-content']"))[1].text;
        assert(message2.equals(guestChatInput))

        val hostChatInput = "123 testing received"
        hostDriver.executeScript("window.enterText('"+hostChatInput+"');")
        hostChatroomPage.sendChat()

        WebDriverWait(
            guestDriver,
            10
        ).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("[data-automation-id='message-content']"), 2))

        //TODO check the chat was received on guest
        val message3 = hostDriver.findElements(By.cssSelector("[data-automation-id='message-content']"))[2].text;
        assert(message2.equals(guestChatInput))


        //TODO
    }

//    @Test
//    fun pairingInfoPageTest() {
//        hostDriver.get(BASE_URL+"/pairing-info");
//        hostDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)
//
//        pairingInfoPage = PairingInfoPage(hostDriver)
//
//        val step1HeaderText = "Start the Magenta Sport App"
//        val actualStep1HeaderText =  hostDriver.findElements(By.cssSelector("[data-automation-id='card-h4-header']"))[0].text;
//        assert(actualStep1HeaderText.equals(step1HeaderText));
//
//        val step1SubText = "In order for you to watch the same game with your friends, it is necessary that you join the Virtual Arena on your TV."
//        val actualStep1SubText =  hostDriver.findElements(By.cssSelector("[data-automation-id='card-subtext']"))[0].text;
//        assert(actualStep1SubText.equals(step1SubText));
//
//        //TODO add image check
//
//        pairingInfoPage.clickContinueToStep2Button()
//        hostDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)
//
//        val step2HeaderText = "Join the Virtual Arena"
//        val actualStep2HeaderText =  hostDriver.findElements(By.cssSelector("[data-automation-id='card-h4-header']"))[1].text;
//        assert(actualStep2HeaderText.equals(step2HeaderText));
//
//        val step2SubText = "In order for you to watch the same game with your friends, it is necessary that you join the Virtual Arena on your TV."
//        val actualStep2SubText =  hostDriver.findElements(By.cssSelector("[data-automation-id='card-subtext']"))[1].text;
//        assert(actualStep1SubText.equals(step1SubText));
//
//        //TODO add image check
//
//        pairingInfoPage.clickContinueToStep3Button()
//        hostDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS)
//        val step3HeaderText = "Enter the PIN code"
//        val actualStep3HeaderText =  hostDriver.findElements(By.cssSelector("[data-automation-id='card-h4-header']"))[2].text;
//        assert(actualStep2HeaderText.equals(step2HeaderText));
//
//        val step3SubText = "Enter the Pin that is displayed on your TV to connect your devices for the Virtual Arena."
//        val actualStep3SubText =  hostDriver.findElements(By.cssSelector("[data-automation-id='card-subtext']"))[2].text;
//        assert(actualStep1SubText.equals(step1SubText));
//    }

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
