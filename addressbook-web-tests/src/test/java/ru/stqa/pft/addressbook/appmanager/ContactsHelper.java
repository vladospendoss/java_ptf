package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(WebDriver driver) { super(driver); }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitFormContact() { click(By.name("submit")); }

    public void fillContactsForm(ContactData contactData) {
        type(By.name("contact_firstname"), contactData.getFirstname());
        type(By.name("contact_skovoroda"), contactData.getSkovoroda());
        type(By.name("contact_lastname"), contactData.getLastname());
    }
}