package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddTests extends TestBase {



   /* получаем список с контактами
    создаем переменную с параметрами
    создаем контакт
    получаем список контактов
    сраниваем с*/

    @Test
    public void testAddContactTests() {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("Shakal").withLastname("Shakalovich");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded
                (contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
