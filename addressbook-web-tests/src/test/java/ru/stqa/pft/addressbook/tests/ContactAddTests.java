package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactAddTests extends TestBase {

    @Test
    public void testAddContactTests(){
        app.getContactsHelper().gotoContactPage();
        app.getContactsHelper().fillContactsForm(new ContactData("test1", "test2", "test3"));
        app.getContactsHelper().submitFormContact();
        app.getContactsHelper().returnToHomePage();
    }
}
