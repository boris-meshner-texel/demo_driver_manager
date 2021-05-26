package com.example.demo_driver_manager

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.By
import org.openqa.selenium.support.PageFactory

class CreateUserPage(driver: WebDriver) {
    @FindBy(css = "[data-automation-id='create-user-input']")
    lateinit var createUserInput: WebElement

    @FindBy(css = "[data-automation-id='create-user-join-btn']")
    lateinit var createUserJoinBtn: WebElement

    @FindBy(css = "[data-automation-id='create-user-header']")
    lateinit var header: WebElement

    @FindBy(css = "[data-automation-id='create-user-text']")
    lateinit var mainText: WebElement

    init {
        PageFactory.initElements(driver, this)
    }

    fun enterUsername (text: String) {
        this.createUserInput.sendKeys(text)
    }

    fun clickJoinButton () {
        this.createUserJoinBtn.click()
    }
}
