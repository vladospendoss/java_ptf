package ru.stqa.pft.wombat_admin.appmanager;

import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver driver) { super(driver); }

    public void gradePage() { driver.get("http://0.0.0.0:3000/grades-new/list"); }

}