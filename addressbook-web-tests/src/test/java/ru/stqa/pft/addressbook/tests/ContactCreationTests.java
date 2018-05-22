package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().createContact(new ContactData("first", "last", "title", "address", "123456", "abcde@gmail.com", "test1"), true);
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before+1);
  }
}
