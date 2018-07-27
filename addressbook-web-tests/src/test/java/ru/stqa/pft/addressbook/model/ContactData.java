package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String middle;
    private final String lastname;

    public ContactData(String firstname, String middle, String lastname) {
        this.firstname = firstname;
        this.middle = middle;
        this.lastname = lastname;
    }
}
