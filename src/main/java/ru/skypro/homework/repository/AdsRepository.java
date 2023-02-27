package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.User;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {

    List<Ads> findAllAdsByUserId(int id);

    List<Ads> findAdsByUser(User user);

    @Query(value = "select * from advert where lower(title) like lower(concat('%', ?1,'%'))", nativeQuery = true)
    List<Ads> findAds(String search);

    List<Ads> findByTitleContainsOrderByTitle(String title);
}

