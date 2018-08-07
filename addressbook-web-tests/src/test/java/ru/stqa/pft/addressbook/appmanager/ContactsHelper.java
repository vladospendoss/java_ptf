package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(WebDriver driver) { super(driver); }

    public void fillContactsForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
    }

     public void deleteContact() { click(By.xpath("/html/body/div[1]/div[4]/form[2]/div[2]/input"));}

    public void selectContact() { click(By.id("4")); }

    public void returnToHomePage() { click(By.linkText("home page")); }

    public void submitFormContact() { click(By.name("submit")); }

    public void goToModificationContact() { click(By.xpath("/html/body/div[1]/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img")); }
}

