package com.example.demo.services;

import com.example.demo.model.*;
import org.springframework.stereotype.Service;
import com.example.demo.model.AnalyticsResponse;

import java.util.*;

@Service
public class Optimizeservice {

    // 🔥 Services

    private static class SelectedOption {

        String requestedName;

        Product cheapestProduct;

        int cheapestCost;

        List<Product> allOptions;

        int quantity;

        SelectedOption(
                String requestedName,
                List<Product> allOptions,
                int quantity
        ) {

            this.requestedName = requestedName;
            this.allOptions = allOptions;
            this.quantity = quantity;

            cheapestCost = Integer.MAX_VALUE;

            for(Product p : allOptions){

                int cost =
                        p.getPrice() * quantity;

                if(cost < cheapestCost){

                    cheapestCost = cost;
                    cheapestProduct = p;
                }
            }
        }
    }
    private final ShoppingService shoppingService;

    private final AIService aiService;

    // 🔥 Constructor
    public Optimizeservice(ShoppingService shoppingService,
                           AIService aiService) {

        this.shoppingService = shoppingService;

        this.aiService = aiService;
    }



    public Response optimize(Request req) {

        List<SelectedOption> allSelections =
                new ArrayList<>();

        int budget = req.getBudget();

        int totalCost = 0;

        List<Product> selectedItems = new ArrayList<>();

        // 🔥 LOOP WITH QUANTITY
        for (ItemRequest itemReq : req.getItems()) {

            String item = itemReq.getName();

            int quantity = itemReq.getQuantity();

            // 🔥 REAL INTERNET PRODUCTS
            List<Map<String, Object>> results =
                    shoppingService.searchProduct(item);

            if (results == null) {
                results = new ArrayList<>();
            }

            List<Product> options = new ArrayList<>();

            // 🔥 Convert API response to Product objects
            for (Map<String, Object> r : results) {

                try {

                    String title = (String) r.get("title");

                    // 🔥 skip if no price
                    if (r.get("price") == null) continue;

                    String priceText =
                            r.get("price").toString()
                                    .replaceAll("[^0-9]", "");

                    if (priceText.isEmpty()) continue;

                    int price = Integer.parseInt(priceText);

                    // ⭐ default rating
                    double rating = 4.0;

                    if (r.get("rating") != null) {

                        rating =
                                ((Number) r.get("rating"))
                                        .doubleValue();
                    }

                    // 🔗 product link
                    String link = "";

                    if (r.get("product_link") != null) {

                        link = (String) r.get("product_link");
                    }

                    // 🖼️ product image
                    String image = "";

                    if (r.get("thumbnail") != null) {

                        image = r.get("thumbnail").toString();
                    }

                    // 🔥 remove bad ratings
                    if (rating < 3.5) continue;

                    options.add(new Product(
                            title,
                            price,
                            8,
                            "Online",
                            link,
                            rating,
                            image
                    ));

                } catch (Exception e) {

                    System.out.println("Skipping invalid product");
                }
            }






            // 🔥 fallback if no internet products
            if (options.isEmpty()) {

                int basePrice = 50 + (item.length() * 10);

                options = new ArrayList<>(List.of(

                        new Product(
                                item + " Standard",
                                basePrice + 30,
                                8,
                                "Amazon",
                                getAmazonLink(item, budget),
                                4.0,
                                "https://via.placeholder.com/150"
                        ),

                        new Product(
                                item + " Premium",
                                basePrice + 60,
                                9,
                                "Flipkart",
                                getFlipkartLink(item, budget),
                                4.5,
                                "https://via.placeholder.com/150"
                        )
                ));
            }

            // 🔥 SMART SORT
            if (options.isEmpty()) {
                continue;
            }

            System.out.println("\nITEM : " + item);

            for(Product p : options){

                System.out.println(
                        p.getName()
                                + " | ₹" + p.getPrice()
                                + " | Rating = "
                                + p.getRating()
                );
            }

            options.sort((a, b) -> {

                if (Double.compare(
                        b.getRating(),
                        a.getRating()) != 0) {

                    return Double.compare(
                            b.getRating(),
                            a.getRating());
                }

                return Integer.compare(
                        a.getPrice(),
                        b.getPrice());
            });

            System.out.println("\nSORTED OPTIONS");

            for(Product p : options){

                System.out.println(
                        p.getName()
                                + " | ₹"
                                + p.getPrice()
                                + " | "
                                + p.getRating()
                );
            }

            System.out.println(
                    "\nSELECTED PRODUCT => "
                            + options.get(0).getName()
                            + " ₹"
                            + options.get(0).getPrice()
            );
            allSelections.add(
                    new SelectedOption(
                            item,
                            options,
                            quantity
                    )
            );



            // 🔥 cheapest fallback

        }

        if (allSelections.isEmpty()) {

            Response res = new Response();

            res.setSelectedItems(new ArrayList<>());

            res.setTotalCost(0);

            res.setSuggestion("No products found for the requested items.");

            return res;
        }



        int minimumRequiredBudget = 0;

        for (SelectedOption item : allSelections) {

            int cheapest = Integer.MAX_VALUE;

            for (Product p : item.allOptions) {

                cheapest = Math.min(
                        cheapest,
                        p.getPrice() * item.quantity
                );
            }

            minimumRequiredBudget += cheapest;
        }

        boolean allItemsPossible =
                minimumRequiredBudget <= budget;

        int[] dp = new int[budget + 1];

        Arrays.fill(dp, -1);

        dp[0] = 0;

        Map<Integer,List<Product>> chosen =
                new HashMap<>();

        chosen.put(0,new ArrayList<>());

        for(SelectedOption item : allSelections){

            int[] next =
                    new int[budget + 1];

            Arrays.fill(next, -1);

            Map<Integer,List<Product>> nextChosen =
                    new HashMap<>();

            for(int b=0;b<=budget;b++){

                if(dp[b] == -1)
                    continue;

                for(Product p : item.allOptions){

                    int cost =
                            p.getPrice()
                                    * item.quantity;

                    int nb =
                            b + cost;

                    if(nb > budget)
                        continue;

                    int score =
                            (int)(
                                    p.getRating()
                                            * 1000
                                            / p.getPrice()
                            );

                    if(dp[b] + score >
                            next[nb]){

                        next[nb] =
                                dp[b] + score;

                        List<Product> list =
                                new ArrayList<>(
                                        chosen.get(b)
                                );

                        list.add(p);

                        nextChosen.put(
                                nb,
                                list
                        );
                    }
                }
            }

            dp = next;
            chosen = nextChosen;
        }

        int bestBudget = 0;
        int bestScore = -1;

        for(int b=0;b<=budget;b++){

            if(dp[b] > bestScore){

                bestScore =
                        dp[b];

                bestBudget =
                        b;
            }
        }

        selectedItems =
                chosen.getOrDefault(
                        bestBudget,
                        new ArrayList<>()
                );

        List<Product> missingProducts =
                new ArrayList<>();

        int extraBudgetNeeded = 0;



        for(SelectedOption option : allSelections){

            boolean found = false;

            for(Product p : selectedItems){

                if(option.allOptions.contains(p)){

                    found = true;
                    break;
                }
            }

            if(!found){

                missingProducts.add(
                        option.cheapestProduct
                );

                extraBudgetNeeded +=
                        option.cheapestCost;
            }
        }

        totalCost = bestBudget;






        // 🔥 RESPONSE
        Response res = new Response();

        AnalyticsResponse analytics =
                new AnalyticsResponse();

        res.setSelectedItems(selectedItems);

        res.setMissingProducts(missingProducts);

        analytics.setBudget(
                budget
        );

        analytics.setSpent(
                totalCost
        );

        analytics.setSaved(
                Math.max(
                        0,
                        budget - totalCost
                )
        );

        res.setTotalCost(totalCost);

        // 🤖 REAL AI SUGGESTION
        // 🤖 Budget based suggestion

        double ratingSum = 0;

        for(Product p : selectedItems){

            ratingSum +=
                    p.getRating();
        }

        analytics.setAvgRating(
                selectedItems.isEmpty()
                        ? 0
                        : ratingSum
                        / selectedItems.size()
        );

        analytics.setTotalProducts(
                selectedItems.size()
        );

        analytics.setBudgetUtilization(
                budget == 0
                        ? 0
                        : ((double) totalCost
                        / budget) * 100
        );

        analytics.setMinimumRequiredBudget(
                minimumRequiredBudget
        );

        analytics.setExtraBudgetNeeded(
                Math.max(
                        0,
                        minimumRequiredBudget
                                - budget
                )
        );

        String suggestion;

        if(allItemsPossible){

            suggestion =
                    "All requested items fit within budget ₹"
                            + budget;

        }else{

            suggestion =
                    "All requested items cannot fit within budget ₹"
                            + budget
                            + ".\n\n";

            suggestion +=
                    "Minimum budget required for all items: ₹"
                            + minimumRequiredBudget
                            + ".\n\n";

            suggestion +=
                    "Showing best possible combination within your budget.\n\n";
        }

        try {

            if(!missingProducts.isEmpty()){

                suggestion +=
                        "\nItems not included:\n";

                for(Product p : missingProducts){

                    suggestion +=
                            "- "
                                    + p.getName()
                                    + " (₹"
                                    + p.getPrice()
                                    + ")\n";

                    suggestion +=
                            "Link: "
                                    + p.getLink()
                                    + "\n";
                }

                suggestion +=
                        "\nExtra money needed to include all items: ₹"
                                + (minimumRequiredBudget - totalCost)
                                + "\n";
            }

            String aiSuggestion =
                    aiService.generateSuggestion(
                            selectedItems,
                            budget,
                            totalCost
                    );

            suggestion +=
                    "\n\n"
                            + aiSuggestion;

        } catch (Exception e) {

            System.out.println(
                    "AI generation failed"
            );
        }
        res.setAnalytics(
                analytics
        );

        res.setSuggestion(suggestion);

        return res;
    }

    // 🔗 Amazon link
    private String getAmazonLink(String item, int budget) {

        String query = item.replace(" ", "+");

        return "https://www.amazon.in/s?k="
                + query
                + "&rh=p_36%3A-"
                + (budget * 100);
    }

    // 🔗 Flipkart link
    private String getFlipkartLink(String item, int budget) {

        String query = item.replace(" ", "+");

        return "https://www.flipkart.com/search?q="
                + query
                + "&p[]=facets.price_range.from%3D0&p[]=facets.price_range.to%3D"
                + budget;
    }
}