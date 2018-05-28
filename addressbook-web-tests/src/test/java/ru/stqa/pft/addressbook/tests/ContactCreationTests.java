package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().HomePage();
   Contacts before = app.contact().all();
    app.contact().initContactCreation();
    File photo = new File("src/test/resources/Contacts.png");
    ContactData contact = new ContactData()
            .withFirstname("abs").withLastname("new1").withTitle("title").withAddress("address").withHomePhone("123456").withMobilePhone("45675").withWorkPhone("099988").withEmail("abcde@gmail.com").withGroup("test1").withPhoto(photo);
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size()+1));
    Contacts after= app.contact().all();


    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }
}
