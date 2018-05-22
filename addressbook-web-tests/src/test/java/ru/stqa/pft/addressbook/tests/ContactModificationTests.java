package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification () {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("first", "last", "title", "address", "123456", "abcde@gmail.com", "test1"), true);
    }

    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size()-1);
    app.getContactHelper().fillContactForm(new ContactData("first", "last", "title", "address", "123456", "abcde@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after= app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}
