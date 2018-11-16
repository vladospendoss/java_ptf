package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
//import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class HelperBase {

    protected WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        if (text != null) {
            driver.findElement(locator).sendKeys(text);
        }

    }

    protected void atach (By locator, File file){
    if (file != null) {
        driver.findElement(locator).sendKeys(file.getAbsolutePath());
    }
    }
//    private boolean isAlertPresent() {
//        try {
//            driver.switchTo().alert();
//            return true;
//        } catch (NoAlertPresentException e) {
//            return false;
//        }
//    }
}
