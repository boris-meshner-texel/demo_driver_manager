package com.example.demo_driver_manager

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.By
import org.openqa.selenium.support.PageFactory

class PairingInfoPage(driver: WebDriver) {
    @FindBy(css = "[data-automation-id='btn-continue-to-step-2']")
    lateinit var continueToStep2Button: WebElement

    @FindBy(css = "[data-automation-id='btn-continue-to-step-3']")
    lateinit var continueToStep3Button: WebElement

    @FindBy(css = "[data-automation-id='btn-go-to-enter-pin']")
    lateinit var continueToEnterPinButton: WebElement

    @FindBy(css = "[data-automation-id='btn-close-window']")
    lateinit var closeButton: WebElement


    init {
        PageFactory.initElements(driver, this)
    }

    fun clickContinueToStep2Button () {
        this.continueToStep2Button.click()
    }

    fun clickContinueToStep3Button () {
        this.continueToStep3Button.click()
    }

    fun clickContinueToEnterPinButton () {
        this.continueToEnterPinButton.click()
    }

    fun clickCloseButton () {
        this.closeButton.click()
    }
}
