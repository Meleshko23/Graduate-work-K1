package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Ads {
    private Integer author;
    private List<String> image;
    @Id
    private Integer pk;
    private Integer price;
    private String title;

    public Ads(Integer author, List<String> image, Integer pk, Integer price, String title) {
        this.author = author;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

    public Ads() {

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ads ads = (Ads) o;
        return author.equals(ads.author) && image.equals(ads.image) && pk.equals(ads.pk) && price.equals(ads.price) && title.equals(ads.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, image, pk, price, title);
    }

    @Override
    public String toString() {
        return "Ads{" +
                "author=" + author +
                ", image=" + image +
                ", pk=" + pk +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }
}
