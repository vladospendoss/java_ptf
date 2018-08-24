package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactAddTests extends TestBase {

    @Test
    public void testAddContactTests(){
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId()) ;


        List<ContactData> before = app.getContactsHelper().getContactList();
        before.sort(byId);
        int nextId = before.get(before.size() - 1).getId() + 1;
        app.getContactsHelper().gotoContactPage();
        ContactData contact = new ContactData(nextId, "oleg010", null, "oleg010");
        app.getContactsHelper().fillContactsForm(contact);
        app.getContactsHelper().submitFormContact();
        app.getContactsHelper().returnToHomePage();
        List<ContactData> after = app.getContactsHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);


        before.add(contact);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);
    }

}
