package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase {
    @Test

    public void testDeleteContactTest(){
        app.getContactsHelper().selectContact();
        app.getContactsHelper().deleteContact();
        app.getContactsHelper().closeDialogWindow();
    }
}
