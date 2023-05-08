import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        NonFoodProduct nfp = NonFoodProduct.fromCsv(Path.of("C:\\Users\\michal\\IdeaProjects\\kolokwium-2\\data\\nonfood\\benzyna.csv"));

        FoodProduct fp = FoodProduct.fromCsv(Path.of("C:\\Users\\michal\\IdeaProjects\\kolokwium-2\\data\\food\\filet_z_morszczuka.csv"));

        //nfp tests
        //System.out.println(nfp.getPrice(2010, 5));

        //should throw an exception
        //System.out.println(nfp.getPrice(2022, 5));

        //fp tests
        //System.out.println(fp.getPrice(2012, 5));
        //System.out.println(fp.getPrice(2012, 5, "MAZOWIECKIE"));

        //krok 3
        //1)
        Product.addProducts(NonFoodProduct::fromCsv, Path.of("C:\\Users\\michal\\IdeaProjects\\kolokwium-2\\data\\nonfood"));
        //2)
      Product.addProducts(FoodProduct::fromCsv, Path.of("C:\\Users\\michal\\IdeaProjects\\kolokwium-2\\data\\food"));

        try {
            //krok 4
            Product p1 = Product.getProducts("Filety z morszczuka mrożone");
            System.out.println(p1.getPrice(2012, 5));

            //Product p2 = Product.getProducts("Mięso"); // AmbigiousProductException
            //Product p3 = Product.getProducts("Komputer"); //  IndexOutOfBoundsException

            Cart cart = new Cart();
            cart.addProduct(Product.getProducts("Benzyna"), 100);
            cart.addProduct(Product.getProducts("Jabłka"), 20);
            cart.addProduct(Product.getProducts("Kiełbasa"), 5);
            cart.addProduct(Product.getProducts("Wizyta u lekarza"), 2);
            cart.addProduct(Product.getProducts("Ziemniaki"), 5);
            System.out.println(cart.getInflation(2020,1, 2022, 3));
        } catch (AmbigiousProductException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}