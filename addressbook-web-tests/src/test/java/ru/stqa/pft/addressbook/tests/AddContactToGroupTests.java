package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.generators.ContactDataGenerator;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.security.acl.Group;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions (){
    if (app.db().contacts().size() == 0 ){
      app.goTo().HomePage();
      app.contact().create(new ContactData()
              .withFirstname("first").withLastname("last").withTitle("title").withAddress("address").withHomePhone("11144")
              .withMobilePhone("23465").withWorkPhone("0000").withEmail1("abcde@gmail.com").withEmail2("abcde2@gmail.com").withEmail3("abcde3@gmail.com"), true);
    }
  }

  @Test
  public void testContactAdditionToGroup () {
    Contacts setOfContacts = app.db().contacts();
    Groups setOfGroups = app.db().groups();
    Set<ContactData> contactsCanBeAddedToGroup = new HashSet<ContactData>();
    int allGroups = setOfGroups.size();
    for (ContactData contact:setOfContacts) {
      int contactGroupsTotal = contact.getGroups().size();
      if(contactGroupsTotal<allGroups){
        contactsCanBeAddedToGroup.add(contact);
      }
    }
    ContactData selectedContact = new ContactData();
    if (contactsCanBeAddedToGroup.size()==0) {
      app.contact().create(new ContactData().withFirstname("first").withLastname("last").withTitle("title").withAddress("address").withHomePhone("11144")
              .withMobilePhone("23465").withWorkPhone("0000").withEmail1("abcde@gmail.com").withEmail2("abcde2@gmail.com").withEmail3("abcde3@gmail.com"), true);
      selectedContact.withId(app.db().contacts().stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId());
    } else {selectedContact = contactsCanBeAddedToGroup.iterator().next();}

    setOfGroups.removeAll(selectedContact.getGroups());
    Groups before = selectedContact.getGroups();
    GroupData selectedGroup = setOfGroups.iterator().next();
    app.contact().selectContactById(selectedContact.getId());
    app.contact().selectGroupToAdd(selectedGroup);
    app.contact().submitContactAdditionToGroup();
    Contacts contactsAfter = app.db().contacts();
    ContactData finalSelectedContact = selectedContact;
    ContactData contactAddedToGroup = contactsAfter.stream().filter((c)->c.equals(finalSelectedContact)).findFirst().get();
    Groups after = contactAddedToGroup.getGroups();
    assertThat(after, equalTo(before.withAdded(selectedGroup)));
    }
}
