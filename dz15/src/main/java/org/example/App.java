package org.example;

import com.opencsv.exceptions.CsvException;
import org.example.models.files.Product;
import org.example.models.files.CsvProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App 
{
    public static void main( String[] args )
    {
        CsvProcessor processor = new CsvProcessor();

        try {
            List<Product> products1 = processor.readCsv("src/main/resources/order_1.csv");
            List<Product> products2 = processor.readCsv("src/main/resources/order_2.csv");
            List<Product> allProducts = new ArrayList<>();
            allProducts.addAll(products1);
            allProducts.addAll(products2);

            Map<String, List<Product>> groupedProducts = processor.groupByStore(allProducts);

            for (Map.Entry<String, List<Product>> entry : groupedProducts.entrySet()) {
                String store = entry.getKey();
                List<Product> storeProducts = entry.getValue();
                List<Product> mergedProducts = processor.mergeProducts(storeProducts);
                processor.writeCsv("src/main/resources/" + store + "_res.csv", mergedProducts);
            }

            System.out.println("Processing complete.");

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
