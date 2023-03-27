package com.healthapp.project.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.healthapp.project.Entity.MedicationDex;
import com.healthapp.project.Entity.MedicationDexBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
public class MedicationService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MedicationService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public boolean getConnectionAndFillTable() throws Exception {
        String apiUrl = "https://api.fda.gov/drug/drugsfda.json?&limit=1000";

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JsonObject jsonObject = new Gson().fromJson(response.toString(), JsonObject.class);
            JsonArray results = jsonObject.getAsJsonArray("results");

            Class.forName("org.postgresql.Driver");

            for (JsonElement resultElement : results) {
                JsonObject result = resultElement.getAsJsonObject();
                // products may be null
                Optional<JsonArray> maybeProducts = Optional.ofNullable(result.getAsJsonArray("products"));
                if (maybeProducts.isEmpty()) {
                    continue;
                }
                // if we're here that means products are good to go
                JsonArray products = maybeProducts.get();

                Optional<String> maybeBrandName =
                        Optional.ofNullable(
                                products
                                        .getAsJsonArray()
                                        .get(0)
                                        .getAsJsonObject()
                                        .getAsJsonPrimitive("brand_name")
                                        .getAsString());

                Optional<String> maybeStrength =
                        Optional.ofNullable(
                                products
                                        .getAsJsonArray()
                                        .get(0)
                                        .getAsJsonObject()
                                        .getAsJsonArray("active_ingredients")
                                        .getAsJsonArray()
                                        .get(0)
                                        .getAsJsonObject()
                                        .getAsJsonPrimitive("strength")
                                        .getAsString());

                Optional<String> maybe_dosage_form =
                        Optional.ofNullable(
                                products
                                        .getAsJsonArray()
                                        .get(0)
                                        .getAsJsonObject()
                                        .getAsJsonPrimitive("dosage_form")
                                        .getAsString());
                if (maybeBrandName.isEmpty() || maybeStrength.isEmpty() || maybe_dosage_form.isEmpty()) {
                    continue;
                }

                // i should have values for everything by time i'm here
                String brandName = maybeBrandName.get();

                String strength = maybeStrength.get();

                String dosage_form = maybe_dosage_form.get();


                MedicationDex medication =
                        new MedicationDexBuilder()
                                .brand_name(brandName)
                                .strength(strength)
                                .dosage_form(dosage_form)
                                .build();

                try {
                    String statement = "INSERT INTO medications (brand_name, strength, dosage_form) VALUES (?,?,?)";
                    jdbcTemplate.update(statement, brandName, strength, dosage_form);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }

            }
        }
        return true;
    }
}