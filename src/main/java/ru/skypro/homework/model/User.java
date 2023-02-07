package ru.skypro.homework.model;

import java.util.Objects;

public class User {
    private String email;
    private String firstName;
    private Integer id;
    private String lastName;
    private String phone;
    private String regDate;
    private String city;
    private String image;

    public User(String email, String firstName, Integer id, String lastName, String phone, String regDate, String city, String image) {
        this.email = email;
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.phone = phone;
        this.regDate = regDate;
        this.city = city;
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email) && firstName.equals(user.firstName) && id.equals(user.id) && lastName.equals(user.lastName) && phone.equals(user.phone) && regDate.equals(user.regDate) && city.equals(user.city) && image.equals(user.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, id, lastName, phone, regDate, city, image);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", id=" + id +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", regDate='" + regDate + '\'' +
                ", city='" + city + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
