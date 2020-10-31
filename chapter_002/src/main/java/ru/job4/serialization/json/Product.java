package ru.job4.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.*;

import java.util.Arrays;
import java.util.List;

public class Product {
    private final String name;
    private final float weight;
    private final Manufacturer manufacturer;
    private final boolean isArchived;
    private final List<String> tradeMarks;

    public Product(String name, float weight, Manufacturer manufacturer, boolean isArchived, String... tradeMarks) {
        this.name = name;
        this.weight = weight;
        this.manufacturer = manufacturer;
        this.tradeMarks = Arrays.asList(tradeMarks);
        this.isArchived = isArchived;
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public List<String> getTradeMarks() {
        return tradeMarks;
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

    public static void main(String[] args) {
        Product product = new Product("SuperiorHummer", 1.5f,
                new Manufacturer("HummersAndNails", "Russia"),
                false, "Maul", "EasyPeasy", "Builder");
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

        JSONObject productJsonObj = new JSONObject(product);
        System.out.println(productJsonObj.toString());
    }
}
