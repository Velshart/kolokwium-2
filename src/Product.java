import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Product {
    private String name;
    private static List<Product> productsList = new ArrayList<>();

    public Product(String name) {
        this.name = name;
    }

    public abstract double getPrice(int year, int month);

    public String getName() {
        return name;
    }

    public static void clearProducts() {
        productsList.clear();
    }

    public static void addProducts(Function<Path, Product> function, Path path) {
        try {
            List<Product> productsToAdd = Files.list(path)
                    .map(function)
                    .toList();

            productsList.addAll(productsToAdd);

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static Product getProducts(String prefix) throws AmbigiousProductException, IndexOutOfBoundsException {
        List<Product> found = productsList.stream()
                .filter(product -> product.getName().startsWith(prefix))
                .toList();

        if(found.size() == 0) {
            throw new IndexOutOfBoundsException("Prefiks nie pasuje do żadnego produktu.");
        } else if (found.size() > 1) {

            List<String> exceptionMessage = found.stream()
                    .map(Product::getName)
                    .toList();

            throw new AmbigiousProductException(exceptionMessage);
        }else {
            return found.get(0);
        }
    }

}
