package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String skovoroda;
    private final String lastname;

    public ContactData(String firstname, String skovoroda, String lastname) {
        this.firstname = firstname;
        this.skovoroda = skovoroda;
        this.lastname = lastname;
    }

    public String getFirstname() { return firstname; }

    public String getSkovoroda() { return skovoroda; }

    public String getLastname() { return lastname; }
}