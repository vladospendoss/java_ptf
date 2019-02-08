package ru.stqa.pft.wombat_admin.appmanager;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ApplicationManager {

    private GradeHelper gradeHelper;
    private NavigationHelper navigationHelper;
    private ErrorsHelper errorsHelper;

    public ApplicationManager() {
        this.navigationHelper = new NavigationHelper();
        this.gradeHelper = new GradeHelper();
        this.errorsHelper = new ErrorsHelper();
    }

    public void init() {
        String login = "grades_admin@smedialink.com";
        String password = "iac5jiB8Y";

        Configuration.browser = "chrome";
        open("http://0.0.0.0:3000/login");
        $(By.cssSelector("img[alt*='Enter.']")).click();
        switchTo().window(1);
        sleep(4000);
        $(By.name("login")).setValue(login);
        if ($(By.name("passwd")).isDisplayed()) {
            $(By.name("passwd")).setValue(password).pressEnter();
        } else{
            $(By.id("passp-field-login")).pressEnter();
            $(By.id("passp-field-passwd")).setValue(password).pressEnter();
        }
        $(By.id("nb-2")).click();
        sleep(3000);
        switchTo().window(0);
    }

    public GradeHelper grade() { return gradeHelper; }

    public NavigationHelper goTo() { return navigationHelper; }

    public ErrorsHelper check() { return errorsHelper; }
}
