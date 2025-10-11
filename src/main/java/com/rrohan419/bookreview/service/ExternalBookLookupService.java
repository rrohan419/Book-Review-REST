package com.rrohan419.bookreview.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalBookLookupService {
    private final RestTemplate rest = new RestTemplate();

    public Map<String, String> lookupByIsbn(String isbn) {
        String url = "https://openlibrary.org/isbn/" + isbn + ".json";
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> resp = rest.getForObject(url, Map.class);
            String title = resp != null && resp.get("title") != null ? resp.get("title").toString() : "Unknown";
            String author = "Unknown";
            return Map.of("title", title, "author", author);
        } catch (Exception ex) {
            return Map.of("title", "Unknown", "author", "Unknown");
        }
    }
}
