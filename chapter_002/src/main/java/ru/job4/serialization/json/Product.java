package ru.job4.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class Product {
    private String name;
    private float weight;
    private Manufacturer manufacturer;
    private boolean isArchived;
    private List<String> tradeMarks;

    public Product(String name, float weight, Manufacturer manufacturer, boolean isArchived, String... tradeMarks) {
        this.name = name;
        this.weight = weight;
        this.manufacturer = manufacturer;
        this.tradeMarks = Arrays.asList(tradeMarks);
        this.isArchived = isArchived;
    }

    @Override
    public String toString() {
        return "Product{"
                + "name='" + name + '\''
                + ", weight=" + weight
                + ", manufacturer=" + manufacturer.toString()
                + ", isArchived=" + isArchived
                + ", tradeMarks=" + tradeMarks.toString()
                + '}';
    }

    private static class Manufacturer {
        private String name;
        private String country;

        public Manufacturer(String name, String country) {
            this.name = name;
            this.country = country;
        }

        @Override
        public String toString() {
            return "Manufacturer{"
                    + "name\\country='" + name + "\\"
                    + country + '\''
                    + '}';
        }
    }

    public static void main(String[] args) {
        Product product = new Product("SuperiorHummer", 1.5f,
                new Manufacturer("HummersAndNails", "Russia"), false, "Maul", "EasyPeasy", "Builder");
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(product));

        final String productJson = "{"
                                        + "\"name\":\"SuperiorHummer\","
                                        + "\"weight\":1.5,"
                                        + "\"manufacturer\":{"
                                            + "\"name\":\"HummersAndNails\","
                                            + "\"country\":\"Russia\"},"
                                        + "\"isArchived\":false,"
                                        + "\"tradeMarks\":[\"Maul\",\"EasyPeasy\",\"Builder\"]"
                                    + "}";
        final Product productMod = gson.fromJson(productJson, Product.class);
        System.out.println(productMod);
    }
}
