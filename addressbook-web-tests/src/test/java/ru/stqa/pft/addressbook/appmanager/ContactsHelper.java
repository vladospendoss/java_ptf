package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(WebDriver driver) { super(driver); }

    public void fillContactsForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("name_middlename"), contactData.getMiddlename());
        type(By.name("name_lastname"), contactData.getLastname());
    }

     public void deleteContact() { click(By.xpath("[normalize-space() and normalize-space(.)='Select all'])[1]/following::input[2]"));}

    public void selectContact() { click(By.id("4")); }

    public void returnToHomePage() { click(By.linkText("home page")); }

    public void submitFormContact() { click(By.name("submit")); }
}

