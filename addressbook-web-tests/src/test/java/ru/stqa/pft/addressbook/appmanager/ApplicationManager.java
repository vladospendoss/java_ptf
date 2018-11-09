package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {
    private final Properties properties;
    public WebDriver driver;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactsHelper groupContacts;
    private StringBuffer verificationErrors = new StringBuffer();
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");

        properties.load(new FileReader((new File(String.format("src/test/resources/%s.properties", target)))));
        if (browser.equals(BrowserType.FIREFOX)) {driver = new FirefoxDriver();}
        else if ((browser.equals(BrowserType.CHROME))) {driver = new ChromeDriver();}
        else if ((browser.equals(BrowserType.SAFARI))) {driver = new SafariDriver();}

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(properties.getProperty("web.baseUrl"));
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        groupContacts = new ContactsHelper(driver);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
          fail(verificationErrorString);
        }
    }

    public GroupHelper group() { return groupHelper; }

    public NavigationHelper goTo() { return navigationHelper; }

    public ContactsHelper contact() { return groupContacts; }

}
