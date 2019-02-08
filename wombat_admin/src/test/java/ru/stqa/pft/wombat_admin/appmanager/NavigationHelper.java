package ru.stqa.pft.wombat_admin.appmanager;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class NavigationHelper {

    //переход к экрану со списком грейдов
    public void gradePage() {
        if($(By.id("menu__grades")).waitUntil(Condition.enabled, 5000).isEnabled())
        {
            $(By.id("menu__grades")).click();
        }
        else {
            open("http://0.0.0.0:3000/grades/list");
        }
    }


    //переход к созданию грейда
    public void createGrade() { $(By.cssSelector("div[class*='AddGradeButton']")).click(); }



}
