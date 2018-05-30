package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")

public class ContactData {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  @Expose
  private String firstname;
  @Expose
  private String lastname;
  @Expose
  private String title;
  @Expose
  private String address;
  @Expose
  private String homephone;
  @Expose
  private String mobilephone;
  @Expose
  private String workphone;
  @Expose
  private String allPhones;
  @Expose
  private String email;
  @Expose
  private String group;
  @Expose
  private File photo;

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

  public String getEmail() {
    return email;
  }

  public String getGroup() { return group; }

  public File getPhoto() { return photo; }

  public void setId(int id) { this.id = id;}

  public ContactData withId(int id) { this.id = id; return this;}

  public ContactData withFirstname(String firstname) { this.firstname = firstname; return this; }

  public ContactData withLastname(String lastname) { this.lastname = lastname; return this;}

  public ContactData withTitle(String title) { this.title = title; return this;}

  public ContactData withAddress(String address) { this.address = address; return this;}

  public ContactData withHomePhone(String homephone) { this.homephone = homephone; return this;}

  public ContactData withMobilePhone(String mobilephone) { this.mobilephone = mobilephone; return this;}

  public ContactData withWorkPhone(String workphone) { this.workphone = workphone; return this;}

  public ContactData withEmail(String email) { this.email = email; return this;}

  public ContactData withGroup(String group) { this.group = group; return this; }

  public ContactData withAllPhones(String allPhones) { this.allPhones = allPhones; return this;}

  public ContactData withPhoto(File photo) { this.photo = photo; return this; }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, firstname, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

}
