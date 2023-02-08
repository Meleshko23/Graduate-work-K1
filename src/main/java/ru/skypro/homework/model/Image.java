package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue
    private Integer id;
    private String filePath;
    private Long fileSize;
    private String mediaType;
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "ads_id")
    @JsonIgnore
    private Ads ads;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
