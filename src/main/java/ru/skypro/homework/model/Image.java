package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue
    private int id;

    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "ads_id")
    private Ads ads;
}
