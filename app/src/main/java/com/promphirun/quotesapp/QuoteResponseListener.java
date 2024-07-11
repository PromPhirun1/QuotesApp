package com.promphirun.quotesapp;

import java.util.List;

public interface QuoteResponseListener {
    void didFetch(List<QuotesResponse> responses, String message);
    void didError(String message);
  }
