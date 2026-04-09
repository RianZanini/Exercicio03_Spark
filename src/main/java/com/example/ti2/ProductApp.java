package com.example.ti2;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;

public class ProductApp {
    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
        dao.init();

        Gson gson = new Gson();
        Spark.port(4567);
        Spark.staticFiles.location("/public");

        Spark.get("/", (req, res) -> {
            res.redirect("/index.html");
            return null;
        });

        Spark.get("/api/products", (req, res) -> {
            res.type("application/json");
            return gson.toJson(dao.findAll());
        });

        Spark.post("/api/products", (req, res) -> {
            res.type("application/json");
            Product product = parseProductFromRequest(req);
            if (product.getName().isEmpty() || product.getPrice() < 0 || product.getQuantity() < 0) {
                res.status(400);
                return gson.toJson(Map.of("error", "Dados inválidos. Verifique o nome, preço e quantidade."));
            }
            dao.save(product);
            return gson.toJson(Map.of("success", true, "product", product));
        });

        Spark.exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.type("application/json");
            response.body(gson.toJson(Map.of("error", exception.getMessage())));
        });
    }

    private static Product parseProductFromRequest(Request req) {
        String name = req.queryParams("name");
        String priceText = req.queryParams("price");
        String quantityText = req.queryParams("quantity");

        double price = 0;
        int quantity = 0;
        if (priceText != null && !priceText.isBlank()) {
            price = Double.parseDouble(priceText.replace(',', '.'));
        }
        if (quantityText != null && !quantityText.isBlank()) {
            quantity = Integer.parseInt(quantityText);
        }
        return new Product(name == null ? "" : name.trim(), price, quantity);
    }
}
