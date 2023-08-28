package app;

import model.Product;
import model.CartItem;
import model.Order;
import service.ProductManager;
import service.Cart;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManager productManager = new ProductManager();
        Cart cart = new Cart();

        // 상품 정보 초기화
        productManager.addProduct(new Product("티셔츠", 20000));
        productManager.addProduct(new Product("청바지", 35000));
        productManager.addProduct(new Product("맨투맨", 30000));
        productManager.addProduct(new Product("슬랙스", 25000));
        // ...

        // 사용자 인터페이스 예시
        while (true) {
            System.out.println("===========================================");
            System.out.println("(1) 상품 보기");
            System.out.println("(2) 장바구니에 담기");
            System.out.println("(3) 장바구니 보기");
            System.out.println("(4) 장바구니에서 상품 수량 늘리기/줄이기");
            System.out.println("(5) 주문하기");
            System.out.println("(6) 종료");
            System.out.println("===========================================");
            int choice = scanner.nextInt();

            if (choice == 1) {
                ArrayList<Product> products = productManager.getProducts();
                for (int i = 0; i < products.size(); i++) {
                    Product product = products.get(i);
                    System.out.println((i + 1) + ". " + product.getName() + " - " + product.getPrice() + "원");
                }
            } else if (choice == 2) {
                while (true) {
                    System.out.println("상품 번호와 수량을 입력하세요:");
                    int productNumber = scanner.nextInt();

                    if (productNumber < 1 || productNumber > productManager.getProducts().size()) {
                        System.out.println("유효하지 않은 상품 번호입니다. 다시 입력하세요.");
                        continue; // 잘못된 선택일 경우, 다시 입력을 받기 위해 다음 반복으로 이동
                    }

                    int quantity = scanner.nextInt();
                    Product selectedProduct = productManager.getProducts().get(productNumber - 1);
                    cart.addItem(new CartItem(selectedProduct, quantity));
                    System.out.println("장바구니에 상품이 담겼습니다.");
                    break; // 올바른 선택일 경우, 루프를 종료하고 다음 동작으로 진행
                }
            } else if (choice == 3) {
                System.out.println("장바구니 내역:");
                ArrayList<CartItem> cartItems = cart.getItems();
                for (int i = 0; i < cartItems.size(); i++) {
                    CartItem cartItem = cartItems.get(i);
                    Product product = cartItem.getProduct();
                    System.out.println((i + 1) + ". " + product.getName() + " - " + product.getPrice() + "원 x " + cartItem.getQuantity());
                }
            } else if (choice == 4) {
                while (true) {
                    System.out.println("수정할 상품 번호를 입력하세요:");
                    int itemNumber = scanner.nextInt();
                    if (itemNumber > 0 && itemNumber <= cart.getItems().size()) {
                        CartItem cartItem = cart.getItems().get(itemNumber - 1);
                        System.out.println("1. 수량 늘리기");
                        System.out.println("2. 수량 줄이기");
                        int quantityChoice = scanner.nextInt();
                        if (quantityChoice == 1) {
                            System.out.println("늘릴 수량을 입력하세요:");
                            int increaseAmount = scanner.nextInt();
                            cartItem.increaseQuantity(increaseAmount);
                            System.out.println("수량이 늘려졌습니다.");
                        } else if (quantityChoice == 2) {
                            System.out.println("줄일 수량을 입력하세요:");
                            int decreaseAmount = scanner.nextInt();
                            cartItem.decreaseQuantity(decreaseAmount);
                        } else {
                            System.out.println("유효하지 않은 선택입니다. 다시 입력하세요.");
                        }
                        break; // 잘못된 선택이 없을 경우 반복문 종료
                    } else {
                        System.out.println("유효하지 않은 상품 번호입니다. 다시 입력하세요.");
                    }
                }
            } else if (choice == 5) {
                Order order = new Order(cart.getItems());
                System.out.println("총 주문 금액: " + order.getTotalPrice() + "원");
            } else if (choice == 6) {
                System.out.println("구매해주셔서 감사합니다");
                break;
            }
        }

        scanner.close();
    }
}
