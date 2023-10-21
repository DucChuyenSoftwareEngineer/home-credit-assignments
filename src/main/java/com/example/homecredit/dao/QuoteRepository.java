package com.example.homecredit.dao;

import com.example.homecredit.enity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {

    // A custom query to get a random quote from the database
    @Query(value = "SELECT * FROM quote ORDER BY RAND() LIMIT 1", nativeQuery = true)
    QuoteEntity findRandom();

    // A custom query to get quotes that contain specific text in their text or author fields
    @Query(value = "SELECT * FROM quote WHERE text LIKE %?1% OR author LIKE %?1%", nativeQuery = true)
    List<QuoteEntity> findByText(String text);
}