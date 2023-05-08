import java.util.*;

public class Cart {
    private final Map<Product, Integer> products = new HashMap<>();
    public void addProduct(Product product, int amount) {
        products.put(product, amount);
    }
    public double getPrice(int year, int month) {
        List<Double> prices = products.keySet().stream()
                .map(product -> product.getPrice(year, month))
                .toList();

        int [] amounts = products.values().stream()
                .mapToInt(Integer::intValue)
                .toArray();

        int index = 0;
        for (Double price : prices) {
            price *= amounts[index];
            index++;
        }
        return prices.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double getInflation(int year1, int month1, int year2, int month2) {
        double price1 = getPrice(year1, month1);
        double price2 = getPrice(year2, month2);

        int months = ((year2-year1) * 12 + (month2 - month1));

        return (price2 - price1) / price1 * 100 / months * 12;
    }
}
