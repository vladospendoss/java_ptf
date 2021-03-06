package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
//import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testModificationContact(){
        app.getNavigationHelper().gotoContactPage();
        app.getContactsHelper().fillContactsForm(new ContactData("oleg1", "oleg2","oleg3"));
        app.getContactsHelper().submitFormContact();
        app.getContactsHelper().returnToHomePage();
    }
}
