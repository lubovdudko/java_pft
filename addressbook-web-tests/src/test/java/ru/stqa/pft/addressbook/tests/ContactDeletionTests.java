package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions (){
    if (app.db().contacts().size() == 0 ){
    app.goTo().HomePage();
    app.contact().create(new ContactData()
              .withFirstname("first").withLastname("last").withTitle("title").withAddress("address").withHomePhone("11144").withMobilePhone("23465").withWorkPhone("0000").withEmail1("abcde@gmail.com").withEmail2("abcde2@gmail.com").withEmail3("abcde3@gmail.com").inGroup(new GroupData().withName("test1")), true);
    }
  }

  @Test
  public void testContactDeletion(){
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().HomePage();
    assertThat(app.contact().count(), equalTo(before.size()-1));
    Contacts after= app.db().contacts();

    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
