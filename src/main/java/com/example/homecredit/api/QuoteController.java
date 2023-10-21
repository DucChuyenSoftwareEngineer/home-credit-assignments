package com.example.homecredit.api;

import com.example.homecredit.common.HomeCreditConstant;
import com.example.homecredit.enity.QuoteEntity;
import com.example.homecredit.exception.BusinessException;
import com.example.homecredit.service.QuoteService;
import com.example.homecredit.vo.base.BaseRestResponse;
import com.example.homecredit.vo.quote.QuoteRequest;
import com.example.homecredit.vo.quote.QuoteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(HomeCreditConstant.API_QUOTE_PATH)
public class QuoteController extends BaseApiController {

   @Autowired
   private QuoteService quoteService;

    // A method to handle POST /api/quote request to create a quote
    @PostMapping
    @RequestMapping(method = RequestMethod.POST, path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseRestResponse> createQuote(@RequestHeader(HomeCreditConstant.X_REQUEST_ID) String xRequestId, @RequestBody QuoteRequest request) throws BusinessException {
        // Creating a new instance of the BaseRestResponse
        final BaseRestResponse response = new BaseRestResponse();

        // Creating a new instance of the QuoteEntity and then Adds all the attributes provided
        final QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setAuthor(request.getAuthor());
        quoteEntity.setText(request.getText());

        // Calls the create method of quote service with the provided attributes
        quoteService.createQuote(quoteEntity);
        // Set value response
        response.setData(QuoteResponse.builder()
                .id(quoteEntity.getId())
                .author(quoteEntity.getAuthor())
                .text(quoteEntity.getText())
                .build());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // A method to handle PUT /api/quote/{id} request to update a quote with a specific ID
    @PutMapping(HomeCreditConstant.API_ID_PATH)
    public ResponseEntity<BaseRestResponse> updateQuote(@PathVariable Long id, @RequestHeader(HomeCreditConstant.X_REQUEST_ID) String xRequestId, @RequestBody QuoteRequest request) throws BusinessException {

        // Creating a new instance of the QuoteEntity and then Adds all the attributes provided
        final QuoteEntity quoteEntity = new QuoteEntity();
        quoteEntity.setAuthor(request.getAuthor());
        quoteEntity.setText(request.getText());

        quoteService.updateQuote(id, quoteEntity);

        // Set value response
        return reply(QuoteResponse.builder()
                .id(quoteEntity.getId())
                .author(quoteEntity.getAuthor())
                .text(quoteEntity.getText())
                .build(),
                HttpStatus.OK);

    }

    // A method to handle DELETE /api/quote/{id} request to delete a quote with a specific ID
    @DeleteMapping(HomeCreditConstant.API_ID_PATH)
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // A method to handle GET /api/quote/random request to get a random quote
    @GetMapping(HomeCreditConstant.API_RANDOM_PATH)
    public ResponseEntity<BaseRestResponse> getRandomQuote() {
        QuoteEntity randomQuote = quoteService.getRandomQuote();
        return reply(randomQuote,HttpStatus.OK);
    }

    // A method to handle GET /api/quote/search?text={text} request to get quotes that contain specific text
    @GetMapping(HomeCreditConstant.API_SEARCH_PATH)
    public ResponseEntity<BaseRestResponse> getQuotesByText(@RequestParam String text) {
        List<QuoteEntity> quotesByText = quoteService.getQuotesByText(text);
        return reply(quotesByText,HttpStatus.OK);

    }

    // A method to handle GET /api/quote request to get all quotes
    @GetMapping
    public ResponseEntity<BaseRestResponse> getAllQuotes() {
        List<QuoteEntity> allQuotes = quoteService.getAllQuotes();
        return reply(allQuotes,HttpStatus.OK);
    }


}