package ru.stqa.pft.wombat_admin.appmanager;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class NavigationHelper {

    //переход к экрану со списком грейдов
    public void gradePage() {

        if($("div[tabindex*='-1']").isDisplayed() || $(By.id("menu__grades")).is(Condition.not(Condition.enabled)))
        {
            open("http://0.0.0.0:3000/grades/list");
        }
        else {
            $(By.id("menu__grades")).click();
        }
    }

    //переход к созданию грейда
    public void createGrade() { $(By.cssSelector("div[class*='AddGradeButton']")).click(); }



}
