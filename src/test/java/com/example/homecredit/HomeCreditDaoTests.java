package com.example.homecredit;

import com.example.homecredit.dao.QuoteRepository;
import com.example.homecredit.enity.QuoteEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HomeCreditApplication.class)
@DataJpaTest
class HomeCreditDaoTests {

    @Autowired
    private QuoteRepository quoteRepository;

    // A test method to check if the save method works correctly for creating a quote
    @Test
    void testSave() {
        QuoteEntity quote = new QuoteEntity("Thánh gióng.", "dân gian");
        QuoteEntity savedQuoteEntity = quoteRepository.save(quote);
        assertNotNull(savedQuoteEntity.getId());
        assertEquals(quote.getText(), savedQuoteEntity.getText());
        assertEquals(quote.getAuthor(), savedQuoteEntity.getAuthor());
    }

    // A test method to check if the save method works correctly for updating a quote
    @Test
    void testUpdate() {
        QuoteEntity quote = new QuoteEntity("Chí phèo", "Không Biết");
        QuoteEntity savedQuoteEntity = quoteRepository.save(quote);
        savedQuoteEntity.setText("Chí phèo");
        savedQuoteEntity.setAuthor("Nam Cao");
        QuoteEntity updatedQuoteEntity = quoteRepository.save(savedQuoteEntity);
        assertEquals(savedQuoteEntity.getId(), updatedQuoteEntity.getId());
        assertEquals(savedQuoteEntity.getText(), updatedQuoteEntity.getText());
        assertEquals(savedQuoteEntity.getAuthor(), updatedQuoteEntity.getAuthor());
    }

    // A test method to check if the findById method works correctly for retrieving a quote with a specific ID
    @Test
    void testFindById() {
        QuoteEntity quote = new QuoteEntity("Lão Hạc", "Nam cao");
        QuoteEntity savedQuoteEntity = quoteRepository.save(quote);
        Long id = savedQuoteEntity.getId();
        QuoteEntity foundQuoteEntity = quoteRepository.findById(id).orElse(null);
        assertNotNull(foundQuoteEntity);
        assertEquals(id, foundQuoteEntity.getId());
        assertEquals(quote.getText(), foundQuoteEntity.getText());
        assertEquals(quote.getAuthor(), foundQuoteEntity.getAuthor());
    }

    // A test method to check if the findRandom method works correctly for retrieving a random quote
    @Test
    void testFindRandom() {
        QuoteEntity quote1 = new QuoteEntity("Cô bé bán diêm", "Hans Christian Andersen");
        QuoteEntity quote2 = new QuoteEntity("Cô bé quàng khăn đỏ", "Truyện cổ tích");
        QuoteEntity quote3 = new QuoteEntity("Sọ Dừa", "Dân Gian");
        quoteRepository.save(quote1);
        quoteRepository.save(quote2);
        quoteRepository.save(quote3);
        List<QuoteEntity> allQuoteEntitys = quoteRepository.findAll();
        assertTrue(allQuoteEntitys.contains(quoteRepository.findRandom()));
    }

    // A test method to check if the findByText method works correctly for retrieving quotes that contain specific text
    @Test
    void testFindByText() {
        QuoteEntity quote1 = new QuoteEntity("Cô bé bán diêm", "Hans Christian Andersen");
        QuoteEntity quote2 = new QuoteEntity("Cô bé quàng khăn đỏ", "Truyện cổ tích");
        QuoteEntity quote3 = new QuoteEntity("Sọ Dừa", "Dân Gian");
        quoteRepository.save(quote1);
        quoteRepository.save(quote2);
        quoteRepository.save(quote3);
        List<QuoteEntity> quotesBySimple = quoteRepository.findByText("Cô bé");
        assertTrue(quotesBySimple.contains(quote1));
        assertTrue(quotesBySimple.contains(quote2));
        assertTrue(quotesBySimple.contains(quote3));
    }

    @org.springframework.context.annotation.Configuration
    public static class ContextConfiguration {
    }
}
