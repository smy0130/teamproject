package service;

import model.CartItem;
import model.Product;

import java.util.Scanner;

public class CartManager {
    private Scanner scanner;
    private Cart cart;
    private ProductManager productManager;

    public CartManager(Scanner scanner, Cart cart, ProductManager productManager) {
        this.scanner = scanner;
        this.cart = cart;
        this.productManager = productManager;
    }

    // 상품을 장바구니에 추가하는 메서드
    public void addToCart() {
        System.out.println("상품 번호를 입력하세요:");
        int productNumber = scanner.nextInt();

        if (productNumber < 1 || productNumber > productManager.getProducts().size()) {
            System.out.println("유효하지 않은 상품 번호입니다. 다시 입력하세요.");
            return;
        }

        Product selectedProduct = productManager.getProducts().get(productNumber - 1);
        System.out.println("수량을 입력하세요:");
        int quantity = scanner.nextInt();
        int availableStock = selectedProduct.getStock();

        if (quantity > availableStock) {
            System.out.println(selectedProduct.getName() + " 제품의 재고가 부족하여 완료할 수 없습니다.");
            return;
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
    }

    // 장바구니의 아이템을 수정하는 메서드 (수량 증가/감소)
    public void updateCartItemQuantity() {
        System.out.println("수정할 상품 번호를 입력하세요:");
        int itemNumber = scanner.nextInt();

        if (itemNumber > 0 && itemNumber <= cart.getItems().size()) {
            CartItem cartItem = cart.getItems().get(itemNumber - 1);
            System.out.println("변경할 수량을 입력하세요:");
            int changeAmount = scanner.nextInt();

            Product product = cartItem.getProduct();
            if ((product.getStock() >= changeAmount) && (changeAmount >= 0)) {
                cartItem.changeQuantity(changeAmount);
                System.out.println(product.getName() + " " + changeAmount + "개로 변경되었습니다.");
            } else if (changeAmount < 0) {
                System.out.println("잘못된 입력입니다.");
            } else {
                System.out.println("재고가 없습니다.");
            }
        } else {
            System.out.println("유효하지 않은 상품 번호입니다.");
        }
    }

    // 장바구니 보기 메서드
    public void displayCart() {
        System.out.println("장바구니 내역:");
        for (int i = 0; i < cart.getItems().size(); i++) {
            CartItem cartItem = cart.getItems().get(i);
            Product product = cartItem.getProduct();
            System.out.println((i + 1) + ". " + product.getName() + " - " + product.getPrice() + "원 x " + cartItem.getQuantity());
        }
    }

    // 주문하기 메서드
    public void placeOrder() {
        OrderManager orderManager = new OrderManager();
        orderManager.placeOrder(cart);
    }
    
    // 기타 필요한 메서드 추가
}