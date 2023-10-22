package com.example.homecredit;

import com.example.homecredit.common.enumcode.HomeCreditErrorCode;
import com.example.homecredit.dao.QuoteRepository;
import com.example.homecredit.enity.QuoteEntity;
import com.example.homecredit.exception.BusinessException;
import com.example.homecredit.service.QuoteService;
import com.example.homecredit.vo.base.BaseErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class HomeCreditServiceTests {

    @Autowired
    private QuoteService quoteService;

    // A test method to check if the save method works correctly for creating a quote
    @Test
    void testSaveSuccess() {
        QuoteEntity quote = new QuoteEntity("Thánh gióng.", "dân gian");
        QuoteEntity savedQuoteEntity = quoteService.createQuote(quote);
        assertNotNull(savedQuoteEntity.getId());
        assertEquals(quote.getText(), savedQuoteEntity.getText());
        assertEquals(quote.getAuthor(), savedQuoteEntity.getAuthor());
    }

    // A test method to check all case for missing some fields
    @Test
    void testSaveBusinessException() throws Exception {
        QuoteEntity case1 = new QuoteEntity("", "dân gian");
        Mockito.when(quoteService.createQuote(case1))
                .thenThrow(new BusinessException(new BaseErrorResponse(HomeCreditErrorCode.MANDATORY_FIELD_MISSING)));

        QuoteEntity case2 = new QuoteEntity("Thánh gióng.", "dân gian");
        Mockito.when(quoteService.createQuote(case2))
                .thenThrow(new BusinessException(new BaseErrorResponse(HomeCreditErrorCode.MANDATORY_FIELD_MISSING)));

        QuoteEntity case3 = new QuoteEntity("", "");
        Mockito.when(quoteService.createQuote(case3))
                .thenThrow(new BusinessException(new BaseErrorResponse(HomeCreditErrorCode.MANDATORY_FIELD_MISSING)));
    }


    // A test method to check if the save method works correctly for updating a quote
    @Test
    void testUpdateSuccess() {
        QuoteEntity quote = new QuoteEntity("Chí phèo", "Không Biết");
        QuoteEntity savedQuoteEntity = quoteService.createQuote(quote);
        savedQuoteEntity.setText("Chí Phèo");
        savedQuoteEntity.setAuthor("Nam Cao");
        QuoteEntity updatedQuoteEntity = quoteService.createQuote(savedQuoteEntity);

        assertEquals(savedQuoteEntity.getId(), updatedQuoteEntity.getId());
        assertEquals(savedQuoteEntity.getText(), updatedQuoteEntity.getText());
        assertEquals(savedQuoteEntity.getAuthor(), updatedQuoteEntity.getAuthor());
    }


    @Test
    void testUpdateBusinessException() {
        QuoteEntity quote = new QuoteEntity("Chí phèo", "Không Biết");
        QuoteEntity savedQuoteEntity = quoteService.createQuote(quote);
        savedQuoteEntity.setText("");
        savedQuoteEntity.setAuthor("Nam Cao");

        Mockito.when(quoteService.updateQuote(savedQuoteEntity.getId(),savedQuoteEntity))
                .thenThrow(new BusinessException(new BaseErrorResponse(HomeCreditErrorCode.MANDATORY_FIELD_MISSING)));
    }


    // A test method to check if the findRandom method works correctly for retrieving a random quote
    @Test
    void testFindRandom() {
        QuoteEntity quote1 = new QuoteEntity("Cô bé bán diêm", "Hans Christian Andersen");
        QuoteEntity quote2 = new QuoteEntity("Cô bé quàng khăn đỏ", "Truyện cổ tích");
        QuoteEntity quote3 = new QuoteEntity("Sọ Dừa", "Dân Gian");
        quoteService.createQuote(quote1);
        quoteService.createQuote(quote2);
        quoteService.createQuote(quote3);
        List<QuoteEntity> allQuoteEntitys = quoteService.getAllQuotes();
        assertTrue(allQuoteEntitys.contains(quoteService.getRandomQuote()));
    }

    // A test method to check if the findByText method works correctly for retrieving quotes that contain specific text
    @Test
    void testFindByText() {
        QuoteEntity quote1 = new QuoteEntity("Cô bé bán diêm", "Hans Christian Andersen");
        QuoteEntity quote2 = new QuoteEntity("Cô bé quàng khăn đỏ", "Truyện cổ tích");
        QuoteEntity quote3 = new QuoteEntity("Sọ Dừa", "Dân Gian");
        quoteService.createQuote(quote1);
        quoteService.createQuote(quote2);
        quoteService.createQuote(quote3);
        List<QuoteEntity> quotesBySimple = quoteService.getQuotesByText("Cô bé");
        assertTrue(quotesBySimple.contains(quote1));
        assertTrue(quotesBySimple.contains(quote2));
        assertTrue(quotesBySimple.contains(quote3));
    }

    @TestConfiguration
    public static class ContextConfiguration {
    }
}
