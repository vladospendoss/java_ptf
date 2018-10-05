package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;

public class ContactsHelper extends HelperBase {

    public ContactsHelper(WebDriver driver) { super(driver); }

    public void fillContactsForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"),contactData.getHomePhone());
        type(By.name("mobile"),contactData.getMobilePhone());
        type(By.name("work"),contactData.getWorkPhone());
    }

    public void create(ContactData contact) {
            goToAdd();
            fillContactsForm(contact);
            submitFormContact();
            contactCache = null;
            goToHomePage();
        }

    public void modify(ContactData contact){
        goToModificationContact();
        fillContactsForm(contact);
        updateFormContact();
        contactCache = null;
        goToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        closeDialogWindow();
        contactCache = null;
        goToHomePage();
    }

    public void goToAdd() { click(By.linkText("add new")); }

    public void deleteContact() { click(By.xpath("/html/body/div[1]/div[4]/form[2]/div[2]/input"));}

    public void goToHomePage() { click(By.linkText("home")); }

    public void submitFormContact() { click(By.name("submit")); }

    public void goToModificationContact() { click(By.xpath("/html/body/div[1]/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img")); }

    public void updateFormContact() { click(By.name("update")); }

    public void selectContactById (int id) { driver.findElement(By.cssSelector("input[value='" + id + "']")).click(); }

    public void closeDialogWindow() { driver.switchTo().alert().accept(); }

    public int count() {
        return driver.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = driver.findElements(By.cssSelector("#maintable [name=entry]"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.cssSelector("input")).getAttribute("value"));
            List<WebElement> tds = element.findElements(By.cssSelector("td"));
            String lastname = tds.get(1).getText();
            String firstname = tds.get(2).getText();
            String[] phones =  tds.get(5).getText().split("\n");
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).
                    withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
        }
        return contactCache;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        driver.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    private void initContactModificationById(int id) {
       WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value'%s']", id)));
       WebElement row = checkbox.findElement(By.xpath("./../.."));
       List<WebElement> cells = row.findElements(By.tagName("td"));
       cells.get(7).findElement(By.tagName("a")).click();
//       driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s'], id"))).click();
    }
}



