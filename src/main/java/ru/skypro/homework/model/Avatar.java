package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "avatars")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
