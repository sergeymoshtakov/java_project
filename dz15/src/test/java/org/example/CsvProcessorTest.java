package org.example;

import com.opencsv.exceptions.CsvException;
import org.example.models.files.CsvProcessor;
import org.example.models.files.Product;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CsvProcessorTest {
    CsvProcessor processor = new CsvProcessor();

    @Test
    public void testReadCsv() throws IOException, CsvException {
        List<Product> products = processor.readCsv("src/test/resources/order_1.csv");
        assertEquals(6, products.size());
        assertEquals("Гречка", products.get(0).getName());
    }

    @Test
    public void testGroupByStore() throws IOException, CsvException {
        List<Product> products = processor.readCsv("src/test/resources/order_1.csv");
        Map<String, List<Product>> grouped = processor.groupByStore(products);
        assertEquals(2, grouped.size());
        assertTrue(grouped.containsKey("АТБ"));
        assertTrue(grouped.containsKey("Сильпо"));
    }

    @Test
    public void testMergeProducts() throws IOException, CsvException {
        List<Product> products = processor.readCsv("src/test/resources/order_1.csv");
        List<Product> merged = processor.mergeProducts(products);

        assertEquals(3, merged.size());

        assertTrue(merged.stream().anyMatch(p -> "Гречка".equals(p.getName())));
        assertTrue(merged.stream().anyMatch(p -> "Сахар".equals(p.getName())));
        assertTrue(merged.stream().anyMatch(p -> "Мука".equals(p.getName())));

        Product buckwheat = merged.stream().filter(p -> "Гречка".equals(p.getName())).findFirst().orElse(null);
        assertNotNull(buckwheat);
        assertEquals((30.25 + 31.25) / 2, buckwheat.getPrice(), 0.01);
        assertEquals(120 + 24, buckwheat.getQuantity());
    }

    @Test
    public void testProcessAndWriteGroupedProducts() throws IOException, CsvException {
        CsvProcessor processor = new CsvProcessor();

        // Шаг 1: Чтение данных из CSV-файлов
        List<Product> products1 = processor.readCsv("src/test/resources/order_1.csv");
        List<Product> products2 = processor.readCsv("src/test/resources/order_2.csv");

        // Проверяем, что файлы были прочитаны корректно
        assertEquals(6, products1.size());
        assertEquals(7, products2.size());

        // Шаг 2: Объединение продуктов из обоих файлов
        List<Product> allProducts = new ArrayList<>();
        allProducts.addAll(products1);
        allProducts.addAll(products2);

        // Шаг 3: Группировка по магазинам
        Map<String, List<Product>> groupedProducts = processor.groupByStore(allProducts);

        // Проверяем, что правильно сгруппированы продукты по магазинам
        assertEquals(2, groupedProducts.size());
        assertTrue(groupedProducts.containsKey("АТБ"));
        assertTrue(groupedProducts.containsKey("Сильпо"));

        // Шаг 4: Объединение продуктов внутри каждого магазина и запись в файлы
        for (Map.Entry<String, List<Product>> entry : groupedProducts.entrySet()) {
            String store = entry.getKey();
            List<Product> storeProducts = entry.getValue();
            List<Product> mergedProducts = processor.mergeProducts(storeProducts);

            // Проверяем, что продукты внутри магазина объединены правильно
            if ("АТБ".equals(store)) {
                assertEquals(4, mergedProducts.size());  // Пример: проверка количества продуктов для АТБ
            } else if ("Сильпо".equals(store)) {
                assertEquals(3, mergedProducts.size());  // Пример: проверка количества продуктов для Сильпо
            }

            processor.writeCsv("src/test/resources/" + store + "_res.csv", mergedProducts);

            assertTrue(Files.exists(Paths.get("src/test/resources/" + store + "_res.csv")));
        }

        System.out.println("Test completed successfully.");
    }
}
