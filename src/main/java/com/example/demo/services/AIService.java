package com.example.demo.services;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AIService {

    public String generateSuggestion(List<Product> products,
                                     int budget,
                                     int totalCost) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:11434/api/generate";

        StringBuilder prompt = new StringBuilder();

        prompt.append(
                "You are an AI shopping assistant.\n");

        prompt.append(
                "You are an expert shopping advisor. "
                        + "Give a short recommendation in 3-4 sentences. "
                        + "Explain quality, value for money and budget utilization.\n");

        prompt.append(
                "Budget: ").append(budget).append("\n");

        prompt.append(
                "Total Cost: ").append(totalCost).append("\n");

        prompt.append("Products:\n");

        for (Product p : products) {

            prompt.append("- ")
                    .append(p.getName())
                    .append(" | Price: ")
                    .append(p.getPrice())
                    .append(" | Rating: ")
                    .append(p.getRating())
                    .append("\n");
        }

        Map<String, Object> body = new HashMap<>();

        body.put("model", "tinyllama");

        body.put("prompt", prompt.toString());

        body.put("stream", false);

        Map response =
                restTemplate.postForObject(
                        url,
                        body,
                        Map.class
                );

        return response.get("response").toString();
    }
}