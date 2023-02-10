package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

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

    @ManyToOne
    @JoinColumn(name = "author")
    private User user;

    @OneToMany(mappedBy = "ads")
    private Collection<Image> images;


}
