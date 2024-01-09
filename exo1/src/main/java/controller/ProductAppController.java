package controller;

import impl.ProductDAOImpl;
import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ProductAppController {

    private static ProductDAOImpl productDAO;

    public static void main() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        productDAO = new ProductDAOImpl();

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("#### ProductApp ####");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Afficher les infos du produit");
            System.out.println("3. Modifier un produit");
            System.out.println("4. Supprimer un produit");
            System.out.println("5. Quitter l'application");
            System.out.println("Choix : ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    displayProduct(scanner);
                    break;
                case 3:
                    updateProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    displayAllProducts();
                    break;
                case 6:
                    displayProductsAbove100();
                    break;
                case 7:
                    displayProductsBetweenDate(scanner);
                    break;
                case 8:
                    System.out.println("Bye");
                    session.close();
                    sessionFactory.close();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");

            }

        } while (choice != 8);

    }



    private static void addProduct(Scanner scanner) {
        System.out.println("Saisir le nom du produit : ");
        String reference = scanner.nextLine();

        System.out.println("Saisir la marque du produit : ");
        String brand = scanner.nextLine();

        System.out.println("Saisir la date de vente du produit : ");
        String saleDateStr = scanner.nextLine();

        LocalDate saleDate = LocalDate.parse(
                saleDateStr,
                DateTimeFormatter.ofPattern("dd.MM.yyyy")
        );

        System.out.println("Saisir le prix du produit : ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Saisir le nombre de produit en stock : ");
        int storage = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(reference, brand, saleDate, price, storage);
        productDAO.addProduct(product);
        System.out.println("Produit ajouté !");
    }

    private static void displayProduct(Scanner scanner) {
        System.out.println("Saisir l'id du produit à afficher : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product product = productDAO.getProductById(id);
        System.out.println("###########");
        System.out.println(
                        product.getReference() + ", " +
                        product.getBrand() + ", " +
                        product.getPrice() + ", " +
                        product.getSaleDate() + ", " +
                        product.getStorage()
        );
        System.out.println("###########");
    }

    private static void updateProduct(Scanner scanner) {
        System.out.println("Saisir l'id du produit à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine();


        productDAO.updateProduct(id);

    }

    private static void deleteProduct(Scanner scanner) {
        System.out.println("Saisir l'id du produit à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        productDAO.deleteProduct(id);
        System.out.println("Produit supprimé !");

    }

    private static void displayAllProducts() {
        List<Product> products = productDAO.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("Aucune produit trouvé.");
        } else {
            System.out.println("=== Liste des produits ===");
            for (Product product : products) {
                System.out.println("###########");
                System.out.println(
                        product.getId() +
                                ", " +
                                product.getReference() + ", " +
                                product.getBrand() + ", " +
                                product.getPrice() + ", " +
                                product.getSaleDate() + ", " +
                                product.getStorage()
                );
                System.out.println("###########");
            }
        }
    }
    private static void displayProductsBetweenDate(Scanner scanner) {
    }

    private static void displayProductsAbove100() {
    }

}

