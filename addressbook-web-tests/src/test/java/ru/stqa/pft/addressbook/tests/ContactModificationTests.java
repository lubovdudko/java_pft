package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions (){
    app.goTo().HomePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("first").withLastname("last").withTitle("title").withAddress("address").withHomePhone("11144").withMobilePhone("23465").withWorkPhone("0000").withEmail1("abcde@gmail.com").withGroup("test1"), true);

    }
  }

  @Test
  public void testContactModification () {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("new").withLastname("last").withTitle("title").withAddress("address").withHomePhone("123456000").withEmail1("abcde@gmail.com");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after= app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
