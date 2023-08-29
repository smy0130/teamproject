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
        productManager.addProduct(new Product("티셔츠", 20000, 100));
        productManager.addProduct(new Product("청바지", 35000, 100));
        productManager.addProduct(new Product("맨투맨", 30000, 100));
        productManager.addProduct(new Product("슬랙스", 25000, 100));
        // ...

        // 사용자 인터페이스 예시
        while (true) {
            System.out.println("===========================================");
            System.out.println("(1) 상품 보기");
            System.out.println("(2) 장바구니에 담기");
            System.out.println("(3) 장바구니 보기");
            System.out.println("(4) 장바구니에서 상품 수량 늘리기/줄이기");
            System.out.println("(5) 주문하기");
            System.out.println("(6) 재고 추가하기");
            System.out.println("(7) 종료");
            System.out.println("===========================================");
            int choice = scanner.nextInt();

            if (choice == 1) {
                ArrayList<Product> products = productManager.getProducts();
                for (int i = 0; i < products.size(); i++) {
                    Product product = products.get(i);
                    System.out.println((i + 1) + ". " + product.getName() + " - " + product.getPrice() + "원" + " - " + "재고: " + product.getStock());
                }
            } else if (choice == 2) {
                while (true) {
                    System.out.println("상품 번호를 입력하세요:");
                    int productNumber = scanner.nextInt();

                    if (productNumber < 1 || productNumber > productManager.getProducts().size()) {
                        System.out.println("유효하지 않은 상품 번호입니다. 다시 입력하세요.");
                        continue; // 잘못된 선택일 경우, 다시 입력을 받기 위해 다음 반복으로 이동
                    }
                    System.out.println("수량을 입력 하세요 : ");
                    int quantity = scanner.nextInt();
                    Product selectedProduct = productManager.getProducts().get(productNumber - 1);
                    int availableStock = selectedProduct.getStock();

                    if (quantity > availableStock) {
                        System.out.println(selectedProduct.getName() + " 제품의 재고가 부족하여 완료할 수 없습니다.");
                        break; // 재고 부족한 상품이므로 루프를 종료하고 다음 동작으로 진행
                    }
                    
                    boolean itemExists = false;
                    for (CartItem item : cart.getItems()) {
                        if (item.getProduct().equals(selectedProduct)) {
                            item.increaseQuantity(quantity);
                            itemExists = true;
                            break;
                        }
                    }

                    if (!itemExists) {
                        cart.addItem(new CartItem(selectedProduct, quantity));
                    }

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
                        System.out.println("변경할 수량을 입력하세요:");
                        int changeAmount = scanner.nextInt();
                        
                        Product product = cartItem.getProduct();
                        if((product.getStock() >= changeAmount) && (changeAmount >= 0)) {
                           cartItem.changetQuantity(changeAmount);
                           System.out.println(product.getName() + changeAmount + "개로 변경되었습니다.");
                        } else if (changeAmount < 0){
                           System.out.println("잘못된 입력입니다.");
                        }
                        else
                           System.out.println("재고가 없습니다.");
                        break;
                    } else {
                        System.out.println("유효하지 않은 상품 번호입니다.");
                        break;
                    }
                }
            } else if (choice == 5) {
                Order order = new Order(cart.getItems()); // 주문 객체 생성
                System.out.println("총 주문 금액: " + order.getTotalPrice() + "원");
                System.out.println("주문이 완료되었습니다");
                order.completeOrder(); // 주문 완료 후 장바구니 비우기
                // 메인 루프 내부            
            	} else if (choice == 6) {
                System.out.println("재고를 늘릴 제품 번호를 입력하세요:");
                int productNumber = scanner.nextInt();

                if (productNumber < 1 || productNumber > productManager.getProducts().size()) {
                    System.out.println("유효하지 않은 제품 번호입니다. 다시 입력하세요.");
                } else {
                    Product selectedProduct = productManager.getProducts().get(productNumber - 1);
                    System.out.println("추가할 재고 수량을 입력하세요:");
                    int addStockAmount = scanner.nextInt();
                    selectedProduct.increaseStock(addStockAmount);
                    System.out.println(selectedProduct.getName() + "의 재고가 추가되었습니다.");
                }

                } else if (choice == 7) {
                        System.out.println("구매해주셔서 감사합니다");
                        break;
                    }

                }

        scanner.close();
    }
}