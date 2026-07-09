package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

@Service
public class ShoppingService {

    // 🔥 YAHAN APNI API KEY DAALNI HAI
        @Value("${serpapi.api.key}")
        private String API_KEY;
    public List<Map<String, Object>> searchProduct(String query) {

        System.out.println("\n========================");
        System.out.println("SEARCHING : " + query);
        System.out.println("========================");

        RestTemplate restTemplate = new RestTemplate();

        String url =
                "https://serpapi.com/search.json?q="
                        + query.replace(" ", "+")
                        + "&tbm=shop"
                        + "&api_key="
                        + API_KEY;

        Map response =
                restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> results =
                (List<Map<String, Object>>)
                        response.get("shopping_results");

        System.out.println("Products Found : "
                + (results == null ? 0 : results.size()));

        return results;
    }
}