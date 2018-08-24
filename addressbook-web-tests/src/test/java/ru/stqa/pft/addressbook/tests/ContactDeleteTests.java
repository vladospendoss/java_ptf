package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeleteTests extends TestBase {
    @Test

    public void testDeleteContactTest(){
        app.getContactsHelper().createContactWhenNoContacts(new ContactData("durnek", "sad", "oleg"));
//        app.getContactsHelper().selectContact();
        app.getContactsHelper().deleteContact();
        app.getContactsHelper().closeDialogWindow();
    }
}
