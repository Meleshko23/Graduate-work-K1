package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String createAt; // LocalDataTime???
    private String text;

    @ManyToOne
    @JoinColumn(name = "author")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ads_id")
    private Ads ads;

}

