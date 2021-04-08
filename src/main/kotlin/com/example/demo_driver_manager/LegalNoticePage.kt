package com.example.demo_driver_manager

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory


class LegalNoticePage(driver: WebDriver) {
    @FindBy(css = "[data-automation-id='btn-continue']")
    lateinit var continueBotton: WebElement

    init {
        PageFactory.initElements(driver, this)
    }

    fun clickContinue () {
        continueBotton.click();
    }
}
