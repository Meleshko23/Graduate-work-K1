package ru.skypro.homework.model;

import java.util.List;
import java.util.Objects;

public class Ads {
    private Integer author;
    private List<String> image;
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

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
