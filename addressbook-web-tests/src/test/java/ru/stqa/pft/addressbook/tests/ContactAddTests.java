package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddTests extends TestBase {

//    @DataProvider
//    public Iterator<Object[]> validContactsFromXml() throws IOException {
//        try (BufferedReader reader = new BufferedReader(new FileReader(
//                new File("src/test/resources/contacts.xml")))) {
//            String xml = "";
//            String line = reader.readLine();
//            while (line != null) {
//                xml += line;
//                line = reader.readLine();
//            }
//            XStream xStream = new XStream();
//            xStream.processAnnotations(ContactData.class);
//            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
//            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
//        }
//
//    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(
                new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

   /* получаем список с контактами
    создаем переменную с параметрами
    создаем контакт
    получаем список контактов
    сраниваем с*/

   @Test(dataProvider = "validContactsFromJson")
    public void testAddContactTests(ContactData contact)
    {
        app.contact().goToHomePage();
        Contacts before = app.contact().all();
        app.contact().create(contact);
        Contacts after = app.contact().all();

        int maxIndex = after.stream()
                .mapToInt((c) -> c.getId())
                .max()
                .getAsInt();
        ContactData contactWithId = contact.withId(maxIndex);
        before = before.withAdded(contactWithId);
        assertThat(after, equalTo(before));
    }

}