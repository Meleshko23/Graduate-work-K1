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
//    private Integer author;
//    private Collection<Image> image;
//    private Integer pk;
//    private Integer price;
//    private String title;

    @Column(name = "id")
    private int id;

    @Column(name = "price")
    private int price;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "ads")
    private Collection<Image> images;

    @OneToMany(mappedBy = "ads")
    private Collection<Comment> comments;


//    public Ads(Integer author, Collection<Image> image, Integer pk, Integer price, String title) {
//        this.author = author;
//        this.image = image;
//        this.pk = pk;
//        this.price = price;
//        this.title = title;
//    }
//
//    public Ads() {
//
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Ads ads = (Ads) o;
//        return author.equals(ads.author) && image.equals(ads.image) && pk.equals(ads.pk) && price.equals(ads.price) && title.equals(ads.title);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(author, image, pk, price, title);
//    }
//
//    @Override
//    public String toString() {
//        return "Ads{" +
//                "author=" + author +
//                ", image=" + image +
//                ", pk=" + pk +
//                ", price=" + price +
//                ", title='" + title + '\'' +
//                '}';
//    }
}
