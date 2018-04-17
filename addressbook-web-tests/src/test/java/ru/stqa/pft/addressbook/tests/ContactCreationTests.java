package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.gotoHomePage();
    app.initContactCreation();
    app.fillContactForm(new ContactData("first", "last", "title", "address", "123456", "abcde@gmail.com"));
    app.submitContactCreation();
    app.returnToHomePage();
  }
}
