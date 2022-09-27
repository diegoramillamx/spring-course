package com.example.usersapp.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_in_role")
public class UserInRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "role_id")
  private Role role;


  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Role getRole() {
    return this.role;
  }

  public void setRole(Role role) {
    this.role = role;
  }


  @Override
  public boolean equals(Object o) {
    if (o == this)
        return true;
    if (!(o instanceof UserInRole)) {
        return false;
    }
    UserInRole userInRole = (UserInRole) o;
    return Objects.equals(id, userInRole.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

}
