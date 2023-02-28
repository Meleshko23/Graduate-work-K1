package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "ads")
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "price")
    private Integer price;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    @JsonIgnore
    private String description;

    @ManyToOne
    @JoinColumn(name = "author")
    private User user;

    @OneToOne(mappedBy = "ads")
    private Image images;

    @OneToMany(mappedBy = "ads")
    private List<Comment> comments;


}
