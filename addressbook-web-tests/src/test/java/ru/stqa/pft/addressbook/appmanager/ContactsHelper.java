package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(WebDriver driver) { super(driver); }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitAddContact() { click(By.name("submit")); }

    public void fillAddForms() {

    }
}
