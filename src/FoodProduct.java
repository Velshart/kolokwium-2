import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FoodProduct extends Product {
    private final Map<String, Double[]> prices;

    public FoodProduct(String name, Map<String, Double[]> prices) {
        super(name);
        this.prices = prices;
    }

    public static FoodProduct fromCsv(Path path) {
        String name;
        Map<String, Double[]> prices = new HashMap<>();

        try {
            Scanner scanner = new Scanner(path);
            name = scanner.nextLine();

            scanner.nextLine(); //pomijamy nazwy kolumn

            while(scanner.hasNext()) {
                String[] readLine = scanner.nextLine().split(";");
                String provinceName = readLine[0];

                Double [] readPrices = Arrays.stream(readLine)
                        .skip(1)
                        .map(value -> value.replace(",", "."))
                        .map(Double::valueOf)
                        .toArray(Double[]::new);

                prices.put(provinceName, readPrices);
            }
            scanner.close();

            return new FoodProduct(name, prices);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public double getPrice(int year, int month) {
        int sum = 0;
        int elementsCount = 0;

        for (Double[] value : prices.values()) {
            sum += value[(year - 2010) * 12 + (month - 1)];
            elementsCount++;
        }

        return (double) sum /elementsCount;
    }

    public double getPrice(int year, int month, String province) {
        String provinceFormatted = province.toUpperCase();

        if(prices.containsKey(provinceFormatted)) {
            Double[] pricesForProvince = prices.get(provinceFormatted);

            return pricesForProvince[(year - 2010) * 12 + (month - 1)];

        } else {
            throw new IndexOutOfBoundsException("Niepoprawne dane.");
        }
    }
}
