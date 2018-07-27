package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class AddContactTests extends TestBase {

    @Test

    public void testAddContactTests(){
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactsHelper().submitAddContact();
        app.getContactsHelper().returnToHomePage();
    }

}
