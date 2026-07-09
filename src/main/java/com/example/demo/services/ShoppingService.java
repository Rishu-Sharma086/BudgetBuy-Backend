package com.example.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ShoppingService {

    // 🔥 YAHAN APNI API KEY DAALNI HAI
    private final String API_KEY = "0ba64068604a983f8a472afef64ad0af8facd7d85222c574b23cf77e87cb26d2";

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