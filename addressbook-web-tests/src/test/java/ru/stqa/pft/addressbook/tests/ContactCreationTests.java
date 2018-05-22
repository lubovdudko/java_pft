package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactCreation();
    ContactData contact = new ContactData ("abs", "new", "title", "address", "", "abcde@gmail.com", "group");
    app.getContactHelper().createContact(contact, true);
    List<ContactData> after= app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);

    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
