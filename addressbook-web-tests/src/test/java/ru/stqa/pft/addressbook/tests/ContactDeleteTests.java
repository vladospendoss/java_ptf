package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTests extends TestBase {
    @Test(enabled = false)

    public void testDeleteContactTest(){
        app.getContactsHelper().createContactWhenNoContacts(new ContactData("oleg", null, "oleg2"));
        List<ContactData> before = app.getContactsHelper().getContactList();
        app.getContactsHelper().selectContact(before.size() - 1);
        app.getContactsHelper().deleteContact();
        app.getContactsHelper().closeDialogWindow();
        app.getContactsHelper().returnToHomePage();
        List<ContactData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
