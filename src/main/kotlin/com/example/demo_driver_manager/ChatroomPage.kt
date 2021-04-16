package com.example.demo_driver_manager

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.By
import org.openqa.selenium.support.PageFactory

class ChatroomPage(driver: WebDriver) {
    @FindBy(css = "[data-automation-id='input-chat']")
    lateinit var chatInput: WebElement

    @FindBy(css = "[data-automation-id='text-chars-entered']")
    lateinit var charsEntered: WebElement

    @FindBy(css = "[data-automation-id='btn-back']")
    lateinit var backButton: WebElement

    @FindBy(css = "[data-automation-id='text-confirm-back']")
    lateinit var confirmBackText: WebElement

    init {
        PageFactory.initElements(driver, this)
    }

    fun enterChatText (text: String) {
        this.chatInput.sendKeys(text)
    }

    fun clickBackButton () {
        this.backButton.click()
    }
}
