package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(WebDriver driver) { super(driver); }

    public void fillContactsForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
    }

    public void createContactWhenNoContacts(ContactData contact) {
        int contacts = driver.findElements(By.name("selected[]")).size();
        if (contacts == 0) {
            gotoContactPage();
            fillContactsForm(contact);
            submitFormContact();
            returnToHomePage();
        }
    }

    public void deleteContact() { click(By.xpath("/html/body/div[1]/div[4]/form[2]/div[2]/input"));}

    public void returnToHomePage() { click(By.linkText("home page")); }

    public void submitFormContact() { click(By.name("submit")); }

    public void goToModificationContact() { click(By.xpath("/html/body/div[1]/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img")); }

    public void updateFormContact() { click(By.name("update")); }

//    public void selectContact(int index) { driver.findElements(By.name("selected[]")).get(index).click(); }

    public void closeDialogWindow() { driver.switchTo().alert().accept(); }

    public void gotoContactPage() { click(By.linkText("add new")); }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> rows = driver.findElements(By.cssSelector("#maintable [name=entry]"));
        for (WebElement row : rows) {
            int id = Integer.parseInt(row.findElement(By.cssSelector("input")).getAttribute("value"));
            List<WebElement> tds = row.findElements(By.cssSelector("td"));
            String firstname = tds.get(1).getText();
            String lastname = tds.get(2).getText();
            ContactData contact = new ContactData(id, firstname, null,lastname);
            contacts.add(contact);
        }
        return contacts;
    }

}

