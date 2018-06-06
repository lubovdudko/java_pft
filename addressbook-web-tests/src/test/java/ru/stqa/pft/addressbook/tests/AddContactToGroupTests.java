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
    int allGroups = setOfGroups.size();
      ContactData selectedContact = setOfContacts.iterator().next();
      int contactGroupsTotal = selectedContact.getGroups().size();
      if(contactGroupsTotal<allGroups){
        setOfGroups.removeAll(selectedContact.getGroups());
      } else if (contactGroupsTotal==allGroups) {
        selectedContact= setOfContacts.iterator().next();
        setOfGroups.removeAll(selectedContact.getGroups());
      } else {
        app.contact().create(selectedContact = new ContactData().withFirstname("first").withLastname("last").withTitle("title").withAddress("address").withHomePhone("11144")
                .withMobilePhone("23465").withWorkPhone("0000").withEmail1("abcde@gmail.com").withEmail2("abcde2@gmail.com").withEmail3("abcde3@gmail.com"),true);
      }
    GroupData selectedGroup = setOfGroups.iterator().next();
    app.contact().selectContactById(selectedContact.getId());
    app.contact().selectGroupToAdd(selectedGroup);
    app.contact().submitContactAdditionToGroup();
    System.out.println(selectedContact);
    System.out.println(selectedGroup);
    }

/*
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after= app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    verifyContactListInUI();*/

}
