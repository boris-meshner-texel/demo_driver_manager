package com.example.demo_driver_manager

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.By
import org.openqa.selenium.support.PageFactory

class ChatroomPage(driver: WebDriver) {
    @FindBy(css = "[data-automation-id='input-chat']")
    lateinit var chatInput: WebElement

    @FindBy(css = "[data-automation-id='btn-send-chat']")
    lateinit var submitChatBtn: WebElement

    @FindBy(css = "[data-automation-id='text-chars-entered']")
    lateinit var charsEntered: WebElement

    @FindBy(css = "[data-automation-id='created-the-chat-text']")
    lateinit var createdTheChatText: WebElement

    @FindBy(css = "[data-automation-id='btn-back']")
    lateinit var backButton: WebElement

    @FindBy(css = "[data-automation-id='text-confirm-back']")
    lateinit var confirmBackText: WebElement

    @FindBy(css = "[data-automation-id='share-btn']")
    lateinit var shareButton: WebElement

    @FindBy(css = "[data-automation-id='pair-tv-btn']")
    lateinit var pairTvButton: WebElement

    @FindBy(css = "[data-automation-id='participants-btn']")
    lateinit var participantsButton: WebElement

    @FindBy(css = "[data-automation-id='hide-tray-btn']")
    lateinit var hideTrayButton: WebElement

    @FindBy(css = "[data-automation-id='share-url-field']")
    lateinit var shareUrlField: WebElement

    init {
        PageFactory.initElements(driver, this)
    }

    fun enterChatText (inputChars: List<String>) {
            inputChars.forEachIndexed { index, item -> chatInput.sendKeys(item)}
    }

    fun sendChat () {
        this.submitChatBtn.click()
    }

    fun clickBackButton () {
        this.backButton.click()
    }

    fun clickShareButton () {
        this.shareButton.click()
    }

    fun clickParticipantsButton () {
        this.participantsButton.click()
    }

    fun clickHideTrayButton () {
        this.hideTrayButton.click()
    }

    fun clickPairTvButton () {
        this.pairTvButton.click()
    }
}
