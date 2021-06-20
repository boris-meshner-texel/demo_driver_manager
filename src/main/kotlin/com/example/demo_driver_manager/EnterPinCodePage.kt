package com.example.demo_driver_manager

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.By
import org.openqa.selenium.support.PageFactory



class EnterPinCodePage(driver: WebDriver) {
    lateinit var inputFields: List<WebElement>

    init {
        PageFactory.initElements(driver, this)
        this.inputFields = driver.findElements(By.cssSelector("input"))
    }

    fun clickFirstInput () {
        this.inputFields[0].click()
    }

    fun inputCode(numbers: List<String>) {
        this.inputFields
            .forEachIndexed { index, element -> element.sendKeys(numbers[index])}
    }
}
