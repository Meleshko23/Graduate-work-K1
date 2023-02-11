package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.Ads;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {
    List<Ads> findAllAdsByUserId(int id);

    List<Ads> findAdsByAuthorEmail(String username);
}

