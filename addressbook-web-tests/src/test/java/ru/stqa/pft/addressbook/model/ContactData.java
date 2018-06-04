package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name="addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name="firstname")
  private String firstname;
  @Expose
  @Column(name="lastname")
  private String lastname;
  @Expose
  @Column(name="title")
  private String title;
  @Expose
  @Column(name="address")
  @Type(type = "text")
  private String address;
  @Expose
  @Column(name="home")
  @Type(type = "text")
  private String homephone;
  @Expose
  @Column(name="mobile")
  @Type(type = "text")
  private String mobilephone;
  @Expose
  @Column(name="work")
  @Type(type = "text")
  private String workphone;
  @Expose
  @Transient
  private String allPhones;
  @Expose
  @Column(name="email")
  @Type(type = "text")
  private String email1;
  @Expose
  @Column(name="email2")
  @Type(type = "text")
  private String email2;
  @Expose
  @Column(name="email3")
  @Type(type = "text")
  private String email3;
  @Expose
  @Transient
  private String allEmails;
  @Expose
  @Column(name="photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany (fetch = FetchType.EAGER)
  @JoinTable(name="address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name="group_id"))
  public Set<GroupData> groups = new HashSet<GroupData>();

  public ContactData() { }

  public int getId() { return id; }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getTitle() {
    return title;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homephone;
  }

  public String getMobilePhone() {
    return mobilephone;
  }

  public String getWorkPhone() {
    return workphone;
  }

  public String getAllPhones(){return allPhones;}

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public File getPhoto() {
    if (photo == null) {
      return null;
    } else {
      return new File(photo);
    }
    }

  public Groups getGroups() { return new Groups(groups); }

  public void setId(int id) { this.id = id;}

  public void setGroups(Set<GroupData> groups) { this.groups = groups;}

  public ContactData withId(int id) { this.id = id; return this;}

  public ContactData withFirstname(String firstname) { this.firstname = firstname; return this; }

  public ContactData withLastname(String lastname) { this.lastname = lastname; return this;}

  public ContactData withTitle(String title) { this.title = title; return this;}

  public ContactData withAddress(String address) { this.address = address; return this;}

  public ContactData withHomePhone(String homephone) { this.homephone = homephone; return this;}

  public ContactData withMobilePhone(String mobilephone) { this.mobilephone = mobilephone; return this;}

  public ContactData withWorkPhone(String workphone) { this.workphone = workphone; return this;}

  public ContactData withEmail1(String email1) { this.email1 = email1; return this;}

  public ContactData withEmail2(String email2) { this.email2 = email2; return this;}

  public ContactData withEmail3(String email3) { this.email3 = email3; return this;}

  public ContactData withAllEmails(String allEmails) { this.allEmails = allEmails; return this;}

  public ContactData withAllPhones(String allPhones) { this.allPhones = allPhones; return this;}

  public ContactData withPhoto(File photo) { this.photo = photo.getPath(); return this; }

  public ContactData withGroup(String group) { this.groups = groups; return this; }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(title, that.title) &&
            Objects.equals(address, that.address) &&
            Objects.equals(homephone, that.homephone) &&
            Objects.equals(mobilephone, that.mobilephone) &&
            Objects.equals(workphone, that.workphone) &&
            Objects.equals(email1, that.email1) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, firstname, lastname, title, address, homephone, mobilephone, workphone, email1, email2, email3);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", title='" + title + '\'' +
            ", address='" + address + '\'' +
            ", homephone='" + homephone + '\'' +
            ", mobilephone='" + mobilephone + '\'' +
            ", workphone='" + workphone + '\'' +
            ", email1='" + email1 + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            '}';
  }

  public ContactData inGroup(GroupData group) {
  groups.add(group);
  return this;
  }
}
