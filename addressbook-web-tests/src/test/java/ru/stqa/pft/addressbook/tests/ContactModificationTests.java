package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testModificationContact(){
        app.getContactsHelper().createContactWhenNoContacts(new ContactData("oleg", "sad", "oleg2"));
        List<ContactData> before = app.getContactsHelper().getContactList();
        app.getContactsHelper().goToModificationContact();
        ContactData contact = new ContactData( "oleg", null, "oleg2");
        app.getContactsHelper().fillContactsForm(contact);
        app.getContactsHelper().updateFormContact();
        app.getContactsHelper().returnToHomePage();
        List<ContactData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());


        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()) ;
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
