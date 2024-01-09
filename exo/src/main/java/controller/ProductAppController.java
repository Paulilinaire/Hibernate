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

    public static void main() throws Exception {
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
            System.out.println("5. Afficher la totalité des produits");
            System.out.println("6. Filtrer les produits par prix");
            System.out.println("7. Afficher la liste des produits achetés entre deux dates");
            System.out.println("8. Filtrer les produits par stock");
            System.out.println("9. Afficher la valeur du stock des produits d'une marque choisie");
            System.out.println("10. Afficher le prix moyen des produits");
            System.out.println("11. Afficher liste des produits d'une marque choisie");
            System.out.println("12. Supprimer les produits d'une marque choisie de la table produit");
            System.out.println("0. Quitter l'application");
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
                    filterByPrice(scanner);
                    break;
                case 7:
                    displayProductsBetweenDate(scanner);
                    break;
                case 8:
                    filterByStorage(scanner);
                    break;
                case 9:
                    showBrandStorage(scanner);
                    break;
                case 10:
                    showAveragePrice();
                    break;
                case 11:
                    showProductsFromBrand(scanner);
                    break;
                case 12:
                    deleteProductFromBrand(scanner);
                    break;
                case 0:
                    System.out.println("Bye");
                    session.close();
                    sessionFactory.close();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");

            }

        } while (choice != 0);

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
        System.out.println("Product ajouté !");
    }

    private static void displayProduct(Scanner scanner) {
        System.out.println("Saisir l'id du produit à afficher : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product product = productDAO.getProductById(id);
        System.out.println("###########");
        System.out.println(
                "reference : " + product.getReference() + ", " +
                        "marque : " + product.getBrand() + ", " +
                        "prix : " + product.getPrice() + ", " +
                        "date de vente: " + product.getSaleDate() + ", " +
                        "stock " + product.getStorage()
        );
        System.out.println("###########");
    }

    private static void updateProduct(Scanner scanner) {
        System.out.println("Saisir l'id du produit à modifier : ");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Saisir la nouvelle référence du produit : ");
        String newReference = scanner.nextLine();

        System.out.println("Saisir la nouvelle marque du produit : ");
        String newBrand = scanner.nextLine();

        System.out.println("Saisir la nouvelle date de vente du produit (format dd.MM.yyyy) : ");
        String newSaleDateStr = scanner.nextLine();

        LocalDate newSaleDate = LocalDate.parse(
                newSaleDateStr,
                DateTimeFormatter.ofPattern("dd.MM.yyyy")
        );

        System.out.println("Saisir le nouveau prix du produit : ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Saisir la nouvelle quantité en stock du produit : ");
        int newStorage = scanner.nextInt();
        scanner.nextLine();

        Product updatedProduct = new Product(newReference, newBrand, newSaleDate, newPrice, newStorage);

        productDAO.updateProduct(id, updatedProduct);

        System.out.println("Product mis à jour !");
    }


    private static void deleteProduct(Scanner scanner) {
        System.out.println("Saisir l'id du produit à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        productDAO.deleteProduct(id);
        System.out.println("Product supprimé !");

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
                        "reference : " + product.getReference() + ", " +
                                "marque : " + product.getBrand() + ", " +
                                "prix : " + product.getPrice() + ", " +
                                "date de vente: " + product.getSaleDate() + ", " +
                                "stock " + product.getStorage()
                );
                System.out.println("###########");
            }
        }
    }

    private static void filterByPrice(Scanner scanner) {
        System.out.println("Filtre par prix (supérieur à) : ");
        int min = scanner.nextInt();
        scanner.nextLine();

        List<Product> productList = productDAO.filterByPrice(min);
        for (Product pr : productList) {
            System.out.println(pr.getId() + " " + pr.getReference());
        }
    }

    private static void displayProductsBetweenDate(Scanner scanner) {
        System.out.println("Saisir la première date : ");
        String firstSaleDateStr = scanner.nextLine();

        LocalDate firstSaleDate = LocalDate.parse(
                firstSaleDateStr,
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        System.out.println("Saisir la deuxième date : ");
        String secondSaleDateStr = scanner.nextLine();

        LocalDate secondSaleDate = LocalDate.parse(
                secondSaleDateStr,
                DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        List<Product> product = productDAO.filterByDate(firstSaleDate,secondSaleDate);
        for (Product pr: product) {
            System.out.println(pr.getId() + " " + pr.getReference());

        }
    }

    private static void filterByStorage(Scanner scanner) {
        System.out.println("Filtre par stock (inférieur à) : ");
        int max = scanner.nextInt();
        scanner.nextLine();

        List<Product> productList = productDAO.filterByStorage(max);
        for (Product pr : productList) {
            System.out.println(pr.getId() + " " + pr.getReference());
        }
    }

    private static void showBrandStorage(Scanner scanner) {
        System.out.println("Saisir le stock");
    }

    private static void showAveragePrice() {
    }

    private static void showProductsFromBrand(Scanner scanner) {
    }

    private static void deleteProductFromBrand(Scanner scanner) {
    }
}


