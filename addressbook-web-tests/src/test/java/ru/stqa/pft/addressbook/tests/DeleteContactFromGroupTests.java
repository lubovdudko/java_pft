package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions (){
    if (app.db().contacts().size() == 0 ){
      Groups setOfGroups = app.db().groups();
      app.goTo().HomePage();
      app.contact().create(new ContactData()
              .withFirstname("first").withLastname("last").withTitle("title").withAddress("address").withHomePhone("11144")
              .withMobilePhone("23465").withWorkPhone("0000").withEmail1("abcde@gmail.com").withEmail2("abcde2@gmail.com").withEmail3("abcde3@gmail.com").inGroup(setOfGroups.iterator().next()), true);
    }
  }

  @Test
  public void testContactDeletionFromGroup () {
    Contacts setOfContacts = app.db().contacts();
    Groups setOfGroups = app.db().groups();
    Set<ContactData> contactsCanBeDeletedFromGroup = new HashSet<ContactData>();
    int allGroups = setOfGroups.size();
    for (ContactData contact:setOfContacts) {
      int contactGroupsTotal = contact.getGroups().size();
      if(contactGroupsTotal>0){
        contactsCanBeDeletedFromGroup.add(contact);
      }
    }
    ContactData selectedContact = new ContactData();
    if (contactsCanBeDeletedFromGroup.size()==0) {
      app.contact().create(selectedContact=new ContactData().withFirstname("first").withLastname("last").withTitle("title").withAddress("address").withHomePhone("11144")
              .withMobilePhone("23465").withWorkPhone("0000").withEmail1("abcde@gmail.com").withEmail2("abcde2@gmail.com").withEmail3("abcde3@gmail.com").inGroup(setOfGroups.iterator().next()), true);
      selectedContact.withId(app.db().contacts().stream().max((c1, c2) -> Integer.compare(c1.getId(), c2.getId())).get().getId());
    } else {selectedContact = contactsCanBeDeletedFromGroup.iterator().next();}

    Groups before = selectedContact.getGroups();
    GroupData selectedGroup = before.iterator().next();
    app.contact().selectGroupToDelete(selectedGroup);
    app.contact().selectContactById(selectedContact.getId());
    app.contact().submitContactDeletionFromGroup();
    Contacts contactsAfter = app.db().contacts();
    ContactData finalSelectedContact = selectedContact;
    ContactData contactDeletedFromGroup = contactsAfter.stream().filter((c)->c.equals(finalSelectedContact)).findFirst().get();
    Groups after = contactDeletedFromGroup.getGroups();
    assertThat(after, equalTo(before.without(selectedGroup)));
  }
}
