package ru.stqa.pft.wombat_admin.appmanager;

import org.openqa.selenium.WebDriver;
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
    private GradeHelper gradeHelper;
    private NavigationHelper navigationHelper;
    private StringBuffer verificationErrors = new StringBuffer();
    private String browser;

    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException, InterruptedException {
//        String target = System.getProperty("local");

        properties.load(new FileReader((new File(String.format("src/test/resources/local.properties")))));
        if (browser.equals(BrowserType.FIREFOX)) {driver = new FirefoxDriver();}
        else if ((browser.equals(BrowserType.CHROME))) {
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
        }
        else if ((browser.equals(BrowserType.SAFARI))) {driver = new SafariDriver();}


        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(properties.getProperty("web.baseUrl"));
        gradeHelper = new GradeHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public GradeHelper grade() { return gradeHelper; }

    public NavigationHelper goTo() { return navigationHelper; }

}
