package ministore;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int userChoice=0;
        ArrayList<CartItem> cart = new ArrayList<>();

        ArrayList <Product> products= new ArrayList<>();
        products.add(new Product("Bread", 10));
        products.add(new Product("Biscuit", 5));
        products.add(new Product("Cookies", 15));
        products.add(new Product("Lemonade", 30));
        products.add(new Product("Zesto", 20));
        products.add(new Product("Ice cream", 10));

        String greet = String.format("%s%n", "----Welcome to MiniStore!----");
        System.out.print(greet);

        while (userChoice != 5) {
            getMenu();
            try {
                System.out.print("Select number [1-5]: ");
                userChoice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please input a number only.");
                return;
            }

            try {
                switch (userChoice) {
                    case 1:
                        System.out.println();
                        System.out.println("Products: ");
                        /*for (int i= 0; i< products.size(); i++) {
                            System.out.printf("%d. %s%n", i+1, products.get(i));
                        }*/

                        for (Product p: products){
                            System.out.printf("  %-12s = ₱%.2f%n", p.getName(), p.getPrice());
                        }
                        continue;
                    case 2:
                        try{
                            System.out.print("Product name: ");
                            String prodName= sc.nextLine().trim();
                            boolean isFound = false;

                            for (Product p : products) {
                                if (prodName.equalsIgnoreCase(p.getName())) {
                                    isFound=true;
                                    prodName = prodName.substring(0,1).toUpperCase() +
                                    prodName.substring(1).toLowerCase();
                                    break;
                                }
                            }
                            if (!isFound) {
                                System.out.println("Product do not exist. Try again.");
                                return;
                            } else {
                                try{
                                    System.out.print("Quantity: ");
                                    int quantity= sc.nextInt();
                                    sc.nextLine();
                                    if (quantity <= 0) return;

                                    //Check if already in the cart
                                    boolean foundInCart = false;
                                    for (CartItem c: cart) {
                                        if (c.getName().equalsIgnoreCase(prodName)) {
                                            c.setQuantity(c.getQuantity() + quantity);
                                            foundInCart= true;
                                            System.out.printf("✅%s added to the cart.%n", prodName);
                                            break;
                                        }
                                    }
                                    if (!foundInCart){
                                        CartItem cartItem = new CartItem(prodName, quantity);
                                        cart.add(cartItem);
                                        System.out.printf("✅%s added to the cart.%n", prodName);
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Please input a number only.");
                                    return;
                                }
                            }
                        } catch (InputMismatchException e){
                            System.out.println("Please input a number only.");
                            return;
                        }
                        continue;
                    case 3:
                        System.out.println();
                        System.out.printf("Cart: %d%n", cart.size());
                        for (CartItem c: cart) {
                            for (Product p: products){
                                if (c.getName().equalsIgnoreCase(p.getName())){
                                    double subTotal = p.getPrice() * c.getQuantity();
                                    String print = String.format("%-13s x%-3d = ₱%.2f", c.getName(), c.getQuantity(), subTotal);
                                    System.out.println(print);
                                }
                            }
                        }
                        continue;
                    case 4:
                        System.out.println();
                        System.out.println("Items:");
                        for (CartItem c: cart) {
                            for (Product p: products){
                                if (c.getName().equalsIgnoreCase(p.getName())){
                                    double subTotal = p.getPrice() * c.getQuantity();
                                    String print = String.format("%-13sx%-3d= ₱%.2f", c.getName(), c.getQuantity(), subTotal);
                                    System.out.println(print);
                                }
                            }
                        }
                        //Calculate total
                        double subTotal= 0;
                        double total= 0;
                        for (CartItem c: cart) {
                            for (Product p: products){
                                if (c.getName().equalsIgnoreCase(p.getName())){
                                    subTotal = p.getPrice() * c.getQuantity();
                                }
                            }
                            total = total + subTotal;
                        }
                        System.out.printf("----------------------------%nTotal: ₱%.2f%n", total);
                        System.out.println("Thank you for your purchase!");
                        cart.clear();
                        break;
                    case 5:
                        break;
                }
                if (userChoice > 5 || userChoice <= 0) {
                    System.out.println("Enter a number from 1-5 only.");
                    return;
                }
            } catch (InputMismatchException e){
                System.out.println("Please enter a number only");
                return;
            }
        }
    }
    public static void getMenu() {
        String menu = String.format("%n%s%n%s%n%s%n%s%n%s",
                "1. View Products",
                "2. Add Product to Cart",
                "3. View Cart",
                "4. Checkout",
                "5. Exit");
        System.out.println(menu);
    }
}