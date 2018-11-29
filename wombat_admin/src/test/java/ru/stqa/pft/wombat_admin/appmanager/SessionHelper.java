package ru.stqa.pft.wombat_admin.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver driver) { super(driver); }

    public void login(String username, String password) throws InterruptedException {
        click(By.cssSelector("img[alt*='Enter.']"));
        Thread.sleep(1000);
        WebDriver yandexScreen = driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        Thread.sleep(5000);
        WebElement cookie = null;
        try {
            cookie = driver.findElement(By.cssSelector("div[class$='title']"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cookie != null) {
            yandexScreen.findElement(By.cssSelector("div[class$='_action']")).click();

        }
        type(yandexScreen, By.name("login"), username);
        type(yandexScreen, By.name("passwd"), password);
        click(By.cssSelector("span[class*='passport-Button-Text']"));
        Thread.sleep(3000);
        click(By.id("nb-2"));
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
    }
}

