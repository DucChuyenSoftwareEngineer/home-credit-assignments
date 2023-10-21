package com.example.homecredit.service;

import com.example.homecredit.enity.QuoteEntity;
import com.example.homecredit.exception.BusinessException;

import java.util.List;

public interface QuoteService {

    public QuoteEntity createQuote(QuoteEntity quoteEntity) throws BusinessException;

    public QuoteEntity updateQuote(Long id, QuoteEntity quoteEntity) throws BusinessException;

    public void deleteQuote(Long id) throws BusinessException;

    public QuoteEntity getRandomQuote();

    public List<QuoteEntity> getQuotesByText(String text);

    public List<QuoteEntity> getAllQuotes();

}

