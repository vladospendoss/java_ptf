package ru.stqa.pft.wombat_admin.appmanager;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class NavigationHelper {

    //переход к экрану со списком грейдов
    public void gradePage() { $(By.id("menu__grades")).click(); }

}
