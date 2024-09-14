package org.example.models.files;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvProcessor {

    public List<Product> readCsv(String filePath) throws IOException, CsvException {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(filePath))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {
            List<String[]> lines = reader.readAll();
            return lines.stream()
                    .skip(1)
                    .filter(line -> line.length == 4)
                    .map(line -> new Product(
                            line[0],
                            line[1],
                            Double.parseDouble(line[2].replace(",", ".")),
                            Integer.parseInt(line[3])
                    ))
                    .collect(Collectors.toList());
        }
    }

    public void writeCsv(String filePath, List<Product> products) throws IOException {
        try (ICSVWriter writer = new CSVWriterBuilder(new FileWriter(filePath))
                .withSeparator(';')
                .build()) {

            writer.writeNext(new String[]{"НАИМЕНОВАНИЕ", "ЦЕНА", "ШТ"});

            products.forEach(product -> writer.writeNext(new String[]{
                    product.getName(),
                    String.format("%.2f", product.getPrice()).replace('.', ','),
                    String.valueOf(product.getQuantity())
            }));
        }
    }

    public Map<String, List<Product>> groupByStore(List<Product> products) {
        return products.stream().collect(Collectors.groupingBy(Product::getStore));
    }

    public List<Product> mergeProducts(List<Product> products) {
        return products.stream()
                .collect(Collectors.groupingBy(Product::getName))
                .entrySet().stream()
                .map(entry -> {
                    String name = entry.getKey();
                    List<Product> items = entry.getValue();
                    double averagePrice = items.stream()
                            .mapToDouble(Product::getPrice)
                            .average()
                            .orElse(0.0);
                    int totalQuantity = items.stream()
                            .mapToInt(Product::getQuantity)
                            .sum();
                    return new Product("", name, averagePrice, totalQuantity);
                })
                .collect(Collectors.toList());
    }
}
