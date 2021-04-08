package com.example.demo_driver_manager

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory


class LandingPage(driver: WebDriver) {
    @FindBy(css = "[data-automation-id='btn-join-arena']")
    lateinit var joinArenaButton: WebElement

    @FindBy(css = "[data-automation-id='btn-help']")
    lateinit var helpButton: WebElement

    init {
        PageFactory.initElements(driver, this)
    }

    fun clickJoinArena () {
        joinArenaButton.click();
    }

    fun clickHelp () {
        helpButton.click();
    }
}
