package ru.kpfu.itis.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.kpfu.itis.dto.ProductDto;
import ru.kpfu.itis.exceptions.JsonValidationException;
import ru.kpfu.itis.exceptions.NotValidProductException;
import ru.kpfu.itis.utilities.ManufacturerUtils;

import java.io.*;
import java.net.*;
import java.util.*;

public class RestProductService {

    // API endpoint
    private static final String PRODUCT_URL = "http://localhost:3000/product";

    private static final String CHARSET = "UTF-8";

    // Add new product
    public static void add(ProductDto product) throws NotValidProductException, IOException {

        try {

            check(product);

            HttpURLConnection con = (HttpURLConnection) ((new URL(PRODUCT_URL).openConnection()));

            String query = String.format("name=%s&price=%.3f&weight=%.3f&manufacturer=%s&category=%s",
                    URLEncoder.encode(product.getName(), CHARSET),
                    product.getPrice(),
                    product.getWeight(),
                    URLEncoder.encode(product.getManufacturer().getCountry(), CHARSET),
                    URLEncoder.encode(product.getCategory(), CHARSET)
            );

            con.setDoOutput(true);

            con.setRequestProperty("Accept-Charset", CHARSET);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHARSET);

            try (OutputStream out = con.getOutputStream()) {
                out.write(query.getBytes());
                System.out.println("Success");
            }

            int resp = con.getResponseCode();

            System.out.println("Response cocde: " + resp);

        } catch (NotValidProductException e) {
            e.printStackTrace();
            throw e;
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedEncodingException("Cannot recognize encoding");
        } catch (ProtocolException e) {
            e.printStackTrace();
            throw new ProtocolException("No such protocol, protocol must be POST,DELETE,PATCH,GET etc.");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new MalformedURLException("URL is not valid");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Cannot write information to server");
        }

    }

    private static List<JsonElement> getElements(String json) {

        JsonArray productArray = (new JsonParser()).parse(json).getAsJsonArray();

        List<JsonElement> elementList = new ArrayList<>();

        productArray.iterator().forEachRemaining(elementList::add);

        return elementList;
    }


    private static String readAll() throws IOException {
        StringBuilder data = new StringBuilder();
        try {
            HttpURLConnection con = (HttpURLConnection) ((new URL(PRODUCT_URL).openConnection()));
            con.setRequestMethod("GET");

            con.setDoInput(true);
            String s;
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                while ((s = in.readLine()) != null) {
                    data.append(s);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new MalformedURLException("URL is not valid");
        } catch (ProtocolException e) {
            e.printStackTrace();
            throw new ProtocolException("No such protocol, it must be POST,GET,PATCH,DELETE etc.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Cannot read from server");
        }
        return data.toString();
    }


    private static List<ProductDto> getAll() throws IOException, JsonValidationException {

        List<ProductDto> products = new LinkedList<>();

        String data = readAll();

        for (JsonElement e : getElements(data)) {
            JsonObject obj = e.getAsJsonObject();
            Iterator<Map.Entry<String, JsonElement>> elements = obj.entrySet().iterator();
            int i = 0;
            ProductDto current = new ProductDto();
            while (elements.hasNext()) {
                elements.next().getValue(); // index
                switch (i++) {
                    case 0:
                        current.setName(elements.next().getValue().getAsString());
                        break;
                    case 1:
                        current.setPrice(elements.next().getValue().getAsDouble());
                        break;
                    case 2:
                        current.setWeight(elements.next().getValue().getAsDouble());
                        break;
                    case 3:
                        current.setManufacturer(ManufacturerUtils.getByCountry(elements.next().getValue().getAsString()));
                        break;
                    case 4:
                        current.setCategory(elements.next().getValue().getAsString());
                        break;
                    case 5:
                        current.setId(elements.next().getValue().getAsInt());
                        break;
                    default:
                        throw new JsonValidationException("cannot validate json database, check it!");
                }
            }
            products.add(current);
        }

        return products;
    }

    public static String[][] getTable() {

        List<ProductDto> products = new ArrayList<>();

        try {
            products = getAll();
        } catch (IOException | JsonValidationException e) {
            e.printStackTrace();
        }

        String[][] result = new String[products.size()][5];

        int i = 0;

        for (ProductDto p : products) {
            result[i][0] = p.getName();
            result[i][1] = String.valueOf(p.getPrice());
            result[i][2] = String.valueOf(p.getWeight());
            result[i][3] = p.getManufacturer().getCountry();
            result[i][4] = p.getCategory();//найти в категорияях по id name
            i++;
        }

        return result;
    }


    private static void check(ProductDto product) throws NotValidProductException {

        final double EPS = 1E-9;

        if (Objects.isNull(product)) {
            throw new NullPointerException("Product must not be null!");
        }

        if (Objects.isNull(product.getName()) || "".equals(product.getName())) {
            throw new NotValidProductException("Product name must not be empty");
        }

        if (Objects.isNull(product.getCategory()) || "".equals(product.getCategory())) {
            throw new NotValidProductException("Category is not valid");
        }

        if (product.getPrice() <= EPS || product.getWeight() <= EPS) {
            throw new NotValidProductException("Product weight and price must be more than zero");
        }
    }
}
