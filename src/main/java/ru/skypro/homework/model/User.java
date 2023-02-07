package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.List;

@Entity
@Data
public class User {
    private String email;
    private String firstName;
    @Id
    private Integer id;
    private String lastName;
    private String phone;
    private String regDate;
    private String city;
    private List<String> image;

    public User(String email, String firstName, Integer id, String lastName, String phone, String regDate, String city, List<String> image) {
        this.email = email;
        this.firstName = firstName;
        this.id = id;
        this.lastName = lastName;
        this.phone = phone;
        this.regDate = regDate;
        this.city = city;
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
                ", image=" + image +
                '}';
    }
}
