package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(WebDriver driver) { super(driver); }

    public void fillContactsForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
    }

    public void deleteContact() { click(By.xpath("/html/body/div[1]/div[4]/form[2]/div[2]/input"));}

    public void returnToHomePage() { click(By.linkText("home page")); }

    public void submitFormContact() { click(By.name("submit")); }

    public void goToModificationContact() { click(By.xpath("/html/body/div[1]/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img")); }

    public void updateFormContact() { click(By.name("update")); }

    public void selectContact() { click(By.name("selected[]")); }

    public void closeDialogWindow() { driver.switchTo().alert().accept(); }

    public void gotoContactPage() {
        click(By.linkText("add new"));
    }

    public void createContactWhenNoContacts() {
        int contacts = driver.findElements(By.name("selected[]")).size();
        if (contacts == 0) {
            gotoContactPage();
            fillContactsForm(new ContactData("test1", "test2", "test3"));
            submitFormContact();
            returnToHomePage();
        }
    }

}

