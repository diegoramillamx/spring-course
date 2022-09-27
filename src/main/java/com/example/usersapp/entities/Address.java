package com.example.usersapp.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "street")
  private String street;

  @Column(name = "number")
  private String number;

  @Column(name = "city")
  private String city;

  @ManyToOne
  private Profile profile;


  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getStreet() {
    return this.street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return this.number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Profile getProfile() {
    return this.profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
        return true;
    if (!(o instanceof Address)) {
        return false;
    }
    Address address = (Address) o;
    return Objects.equals(id, address.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
