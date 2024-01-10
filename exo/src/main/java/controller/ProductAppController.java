package controller;

import impl.CommentDAOImpl;
import impl.ImageDAOImpl;
import impl.OrderDAOImpl;
import impl.ProductDAOImpl;
import model.Comment;
import model.Image;
import model.Order;
import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductAppController {

    private static ProductDAOImpl productDAO;
    private static OrderDAOImpl orderDAO;
    private static CommentDAOImpl commentDAO;
    private static ImageDAOImpl imageDAO;

    public static void main() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        productDAO = new ProductDAOImpl();
        orderDAO = new OrderDAOImpl();
        imageDAO = new ImageDAOImpl();
        commentDAO = new CommentDAOImpl();

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
            System.out.println("12. Supprimer les produits d'une marque choisie");

            System.out.println("13. Ajouter une image à un produit");
            System.out.println("14. Ajouter un commentaire à un produit");
            System.out.println("15. Filtrer les produits par note");
            System.out.println("16. Créer une commande");
            System.out.println("17. Afficher la totalité des commandes");
            System.out.println("18. Afficher uniquement les commandes du jour");
            System.out.println("0. Quitter l'application");

            System.out.println("Choix : ");

            choice = scanner.nextInt();
            scanner.nextLine();

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
                    showStockValueByBrand(scanner);
                    break;
                case 10:
                    showAveragePrice();
                    break;
                case 11:
                    showBrandProduct(scanner);
                    break;
                case 12:
                    deleteBrandProducts(scanner);
                    break;
                case 13:
                    addImageToProduct(scanner);
                    break;
                case 14:
                    addCommentToProduct(scanner);
                    break;
                case 15:
                    showBestProducts(scanner);
                    break;
                case 16:
                    createOrder(scanner);
                    break;
                case 17:
                    displayAllOrders();
                case 18:
                    showOrdersOfTheDay();
                    break;
                case 0:
                    System.out.println("Bye");
                    session.close();
                    sessionFactory.close();
                    scanner.close();
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

        LocalDate saleDate = LocalDate.parse(saleDateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        System.out.println("Saisir le prix du produit : ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Saisir le nombre de produit en stock : ");
        int storage = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(reference, brand, saleDate, price, storage);
        productDAO.addProduct(product);
        System.out.println("Product ajouté avec succés !");
    }

    private static void displayProduct(Scanner scanner) {
        System.out.println("Saisir l'id du produit à afficher : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Product product = productDAO.getProductById(id);
        System.out.println("###########");
        System.out.println("reference : " + product.getReference() + ", " + "marque : " + product.getBrand() + ", " + "prix : " + product.getPrice() + ", " + "date de vente: " + product.getSaleDate() + ", " + "stock " + product.getStorage());
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

        LocalDate newSaleDate = LocalDate.parse(newSaleDateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        System.out.println("Saisir le nouveau prix du produit : ");
        double newPrice = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Saisir la nouvelle quantité en stock du produit : ");
        int newStorage = scanner.nextInt();
        scanner.nextLine();

        Product updatedProduct = new Product(newReference, newBrand, newSaleDate, newPrice, newStorage);

        productDAO.updateProduct(id, updatedProduct);

        System.out.println("Product mis à jour avec succés !");
    }


    private static void deleteProduct(Scanner scanner) {
        System.out.println("Saisir l'id du produit à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        productDAO.deleteProduct(id);
        System.out.println("Product supprimé avec succés !");

    }

    private static void displayAllProducts() {
        List<Product> products = productDAO.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("Aucune produit trouvé.");
        } else {
            System.out.println("=== Liste des produits ===");
            for (Product product : products) {
                System.out.println("reference : " + product.getReference() + ", " + "marque : " + product.getBrand() + ", " + "prix : " + product.getPrice() + ", " + "date de vente: " + product.getSaleDate() + ", " + "stock : " + product.getStorage());
            }
        }
    }

    private static void filterByPrice(Scanner scanner) {
        System.out.println("Filtre par prix (supérieur à) : ");
        int min = scanner.nextInt();
        scanner.nextLine();

        List<Product> productList = productDAO.filterByPrice(min);
        for (Product pr : productList) {
            System.out.println(pr.getId() + ". " + pr.getReference());
        }
    }

    private static void displayProductsBetweenDate(Scanner scanner) {
        System.out.println("Saisir la première date : ");
        String firstSaleDateStr = scanner.nextLine();

        LocalDate firstSaleDate = LocalDate.parse(firstSaleDateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        System.out.println("Saisir la deuxième date : ");
        String secondSaleDateStr = scanner.nextLine();

        LocalDate secondSaleDate = LocalDate.parse(secondSaleDateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        List<Product> product = productDAO.filterByDate(firstSaleDate, secondSaleDate);
        for (Product pr : product) {
            System.out.println(pr.getId() + ". " + pr.getReference());

        }
    }

    private static void filterByStorage(Scanner scanner) {
        System.out.println("Filtre par stock (inférieur à) : ");
        int max = scanner.nextInt();
        scanner.nextLine();

        List<Product> productList = productDAO.filterByStorage(max);
        for (Product pr : productList) {
            System.out.println(pr.getId() + ". " + pr.getReference());
        }
    }

    private static void showStockValueByBrand(Scanner scanner) {
        System.out.println("Saisir la marque : ");
        String brand = scanner.nextLine();

        double brandStorage = productDAO.calculateStockValueByBrand(brand);
        System.out.println("Valeur du stock de la marque " + brand + " : " + brandStorage);
    }

    private static void showAveragePrice() {
        double averagePrice = productDAO.calculateAveragePrice();
        System.out.println("Le prix moyen des produits est : " + averagePrice + "€.");
    }

    private static void showBrandProduct(Scanner scanner) {
        System.out.println("Saisir la marque : ");
        String brand = scanner.nextLine();

        List<Product> productList = productDAO.getBrandProducts(brand);
        for (Product p : productList) {
            System.out.println("Produits : " + p.getReference());
        }
    }

    private static void deleteBrandProducts(Scanner scanner) {
        System.out.println("Saisir la marque : ");
        String brand = scanner.nextLine();

        productDAO.deleteProductsFromSpecificBrand(brand);
        System.out.println("Produits de la marque " + brand + " supprimé avec succés !");

    }


    private static void addImageToProduct(Scanner scanner) {
        try {
            System.out.println("Combien d'image souhaitez-vous ajouter au produit : ");
            int numberOfImages = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Merci de saisir l'id du produit auquel vous souhaitez ajouter l'image : ");
            long productId = scanner.nextLong();
            scanner.nextLine();

            for (int i = 0; i < numberOfImages; i++) {
                System.out.println("Saisissez l'URL de l'image : ");
                String imageUrl = scanner.nextLine();

                Image image = new Image();
                image.setUrl(imageUrl);

                imageDAO.addImage(image, productId);

                System.out.println("Image ajoutée avec succès au produit d'ID : " + productId);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de l'image au produit : " + e.getMessage());
        }
    }


    private static void addCommentToProduct(Scanner scanner) {
        try {
            System.out.println("Merci de saisir l'id du produit auquel vous souhaitez ajouter un commentaire : ");
            long productId = scanner.nextLong();
            scanner.nextLine();

            System.out.println("Saisissez votre commentaire : ");
            String userComment = scanner.nextLine();

            System.out.println("Saisissez la note (sur 5) : ");
            int userGrade = scanner.nextInt();
            scanner.nextLine();

            Comment comment = new Comment();
            comment.setContent(userComment);
            comment.setGrade(userGrade);
            comment.setCommentDate(LocalDate.now());

            commentDAO.addComment(comment, productId);

            System.out.println("Commentaire ajouté avec succès au produit d'ID : " + productId);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du commentaire au produit : " + e.getMessage());
        }
    }


    private static void showBestProducts(Scanner scanner) {
        try {
            System.out.println("Filtre par note (montre les produits avec la note saisie ou plus) : ");
            int grade = scanner.nextInt();
            scanner.nextLine();

            List<Product> productList = commentDAO.filterByGrade(grade);
            for (Product pr : productList) {
                System.out.println(pr.getId() + ". " + pr.getReference());
            }
        } catch (Exception e) {
            System.out.println("Aucun commentaire trouvé ! " + e.getMessage());

        }
    }

    private static void createOrder(Scanner scanner) {
        try {
            List<Product> allProducts = productDAO.getAllProducts();
            if (allProducts.isEmpty()) {
                System.out.println("Aucun produit disponible. Impossible de créer une commande.");
                return;
            }

            Order order = new Order();

            System.out.println("Combien de produits souhaitez-vous commander : ");
            int numberOfProducts = scanner.nextInt();
            scanner.nextLine();

            List<Long> productIds = new ArrayList<>();

            double total = 0.0;

            for (int i = 0; i < numberOfProducts; i++) {
                System.out.println("Merci de saisir l'id du produit que vous souhaitez commander : ");
                long productId = scanner.nextLong();
                scanner.nextLine();

                Product product = productDAO.getProductById(productId);

                if (product != null) {
                    productIds.add(productId);
                    total += product.getPrice();
                } else {
                    System.out.println("Produit avec l'ID " + productId + " introuvable. Ignoré dans la commande.");
                }
            }

            order.setOrderDate(LocalDate.now());
            order.setTotal(total);

            orderDAO.addOrder(order, productIds);
            System.out.println("Commande créée avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la création de la commande : " + e.getMessage());
        }
    }

    private static void displayAllOrders() {
        List<Order> orders = orderDAO.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("Aucune commande trouvée.");
        } else {
            System.out.println("=== Liste des commandes ===");
            for (Order o : orders) {
                System.out.println("Date : " + o.getOrderDate() + ", Prix total : " + o.getTotal());
                System.out.println("Produits : ");

                for (Product product : o.getProducts()) {
                    System.out.println("  - ID : " + product.getId() + ", " + "Référence : " + product.getReference() + ", " + "Marque : " + product.getBrand() + ", " + "Prix : " + product.getPrice());
                }
                System.out.println("=====");
            }
        }
    }


    private static void showOrdersOfTheDay() {
        try {
            List<Order> ordersOfTheDay = orderDAO.getOrdersOfTheDay();
            System.out.println("=== Liste des commandes ===");
            for (Order o : ordersOfTheDay) {
                System.out.println("Date : " + o.getOrderDate() + ", Prix total : " + o.getTotal());
                System.out.println("Produits : ");

                for (Product product : o.getProducts()) {
                    System.out.println("  - ID : " + product.getId() + ", " + "Référence : " + product.getReference() + ", " + "Marque : " + product.getBrand() + ", " + "Prix : " + product.getPrice());
                }
                System.out.println("=====");
            }
        } catch (Exception e) {
            System.out.println("Aucune commande trouvée ! " + e.getMessage());
        }
    }

}


