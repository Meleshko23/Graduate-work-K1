package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long fileSize;
    private String mediaType;
    @Lob
    @Type(type = "binary")
    private byte[] data;

    @OneToOne
    @JoinColumn(name = "ads_id")
    @JsonIgnore
    private Ads ads;

    //    аватар
    @OneToOne
    @JoinColumn(name = "author")
    @JsonIgnore
    private User user;

    public String toString() {
        return "AdsEntity(id=" + this.getId() + ", image=" + java.util.Arrays.toString(this.getData()) + ")";
    }
}
