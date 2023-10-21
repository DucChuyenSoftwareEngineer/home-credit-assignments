package com.example.homecredit.service.impl;

import com.example.homecredit.common.enumcode.HomeCreditErrorCode;
import com.example.homecredit.dao.QuoteRepository;
import com.example.homecredit.enity.QuoteEntity;
import com.example.homecredit.exception.BusinessException;
import com.example.homecredit.service.QuoteService;
import com.example.homecredit.util.CheckUtil;
import com.example.homecredit.vo.base.BaseErrorResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;

    // A method to create a quote and save it in the database
    @Transactional
    public QuoteEntity createQuote(QuoteEntity quoteEntity) throws BusinessException {
        this.validate(quoteEntity);
        return quoteRepository.save(quoteEntity);
    }

    // A method to update a quote with a given ID and save it in the database
    @Transactional
    public QuoteEntity updateQuote(Long id, QuoteEntity quoteEntity) throws BusinessException {
        this.validate(quoteEntity);
        QuoteEntity existingQuote = getQuoteById(id);
        existingQuote.setText(quoteEntity.getText());
        existingQuote.setAuthor(quoteEntity.getAuthor());
        return quoteRepository.save(existingQuote);
    }

    // A method to delete a quote with a given ID from the database
    @Transactional
    public void deleteQuote(Long id) throws BusinessException {
        QuoteEntity existingQuote = getQuoteById(id);
        quoteRepository.delete(existingQuote);
    }

    // A method to get a quote with a given ID from the database
    @Transactional
    public QuoteEntity getQuoteById(Long id) {
        return quoteRepository
                .findById(id)
                .orElseThrow(() -> new BusinessException(new BaseErrorResponse(HomeCreditErrorCode.DOES_NOT_EXIST)));
    }

    private void validate(QuoteEntity quoteEntity) throws BusinessException {
        if (CheckUtil.isNullOrEmpty(quoteEntity.getAuthor()) || CheckUtil.isNullOrEmpty(quoteEntity.getText())) {
            throw new BusinessException(new BaseErrorResponse(HomeCreditErrorCode.MANDATORY_FIELD_MISSING));
        }
    }

    // A method to get a random quote from the database
    public QuoteEntity getRandomQuote() {
        return quoteRepository.findRandom();
    }

    // A method to get all quotes from the database
    public List<QuoteEntity> getAllQuotes() {
        return quoteRepository.findAll();
    }

    // A method to get quotes that contain specific text in their text or author fields from the database
    public List<QuoteEntity> getQuotesByText(String text) {
        return quoteRepository.findByText(text);
    }
}
