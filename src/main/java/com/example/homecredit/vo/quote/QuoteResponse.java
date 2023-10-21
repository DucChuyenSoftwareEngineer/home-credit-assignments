package com.example.homecredit.vo.quote;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuoteResponse {
    private Long id;
    private String text;
    private String author;
}
