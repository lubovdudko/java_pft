package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions (){
    if (app.db().contacts().size() == 0 ){
    app.goTo().HomePage();
    app.contact().create(new ContactData()
              .withFirstname("first").withLastname("last").withTitle("title").withAddress("address").withHomePhone("11144")
              .withMobilePhone("23465").withWorkPhone("0000").withEmail1("abcde@gmail.com").withEmail2("abc@gmail.com").withEmail3("qwerty@gmail.com").inGroup(new GroupData().withName("test1")), true);

    }
  }

  @Test
  public void testContactEmail(){
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(),equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail1(),contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }
}
