package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().HomePage();
    Set<ContactData> before = app.contact().all();
    app.contact().initContactCreation();
    ContactData contact = new ContactData()
            .withFirstname("abs").withLastname("new").withTitle("title").withAddress("address").withPhone("123456").withEmail("abcde@gmail.com").withGroup("test1");
    app.contact().create(contact, true);
    Set<ContactData> after= app.contact().all();
    Assert.assertEquals(after.size(), before.size()+1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    contact.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);
  }
}
