package com.example.demo_driver_manager

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory



class EnterPinCodePage(driver: WebDriver) {
    @FindBy(css = "input")
    lateinit var inputFields: List<WebElement>

    init {
        PageFactory.initElements(driver, this)
    }

    fun clickFirstInput () {
        inputFields[0].click()
    }
    
    fun inputCode(numbers: List<String>) {
        inputFields
            .forEachIndexed { index, element -> element.sendKeys(numbers[index])}
    }
}
