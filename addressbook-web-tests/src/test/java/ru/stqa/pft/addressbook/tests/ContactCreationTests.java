package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().createContact(new ContactData("first", "last", "title", "address", "123456", "abcde@gmail.com", "test1"), true);
    List<ContactData> after= app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()+1);
  }
}
