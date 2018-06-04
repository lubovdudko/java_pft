package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions (){
    if (app.db().contacts().size() == 0 ){
    app.goTo().HomePage();
    app.contact().create(new ContactData()
              .withFirstname("first").withLastname("last").withTitle("title").withAddress("address").withHomePhone("11144").withMobilePhone("23465").withWorkPhone("0000").withEmail1("abcde@gmail.com").withEmail2("abcde22@gmail.com").withEmail3("abcde33@gmail.com").inGroup(new GroupData().withName("test1")), true);

    }
  }

  @Test
  public void testContactModification () {
    Contacts before =app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("new").withLastname("last").withTitle("title").withAddress("address").withHomePhone("123456000").withWorkPhone("").withMobilePhone("").withEmail1("abcde@gmail.com").withEmail2("abcde2@gmail.com").withEmail3("abcde3@gmail.com");
    app.goTo().HomePage();
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after= app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}
