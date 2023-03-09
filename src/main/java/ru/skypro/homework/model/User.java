package ru.skypro.homework.model;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String phone;
    @Column(name = "reg_date")
    private LocalDate regDate;
    private String city;
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private Collection<Ads> adsCollection;

    @OneToMany(mappedBy = "user")
    private Collection<Comment> commentsCollection;

}
