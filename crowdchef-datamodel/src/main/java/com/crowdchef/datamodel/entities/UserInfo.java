package com.crowdchef.datamodel.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    private String email;

    private String address;

    private String city;

    private String country;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public UserInfo() {

    }

    public UserInfo(User user, String email) {
        this.user = user;
        this.email = email;
    }

    public UserInfo(User user, String email, String address, String city, String country) {
        this.user = user;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserInfo{" + getId() + "}: " + getEmail() + " " + getAddress() + " " + getCity() + " " + getCountry();
    }
}
