package service;

import service.Cart;
import model.CartItem;
import model.Order;
import java.util.ArrayList;

public class OrderManager {
    public void placeOrder(Cart cart) {
        Order order = new Order(cart.getItems()); // 주문 객체 생성
        System.out.println("총 주문 금액: " + order.getTotalPrice() + "원");
        System.out.println("주문이 완료되었습니다");
        order.completeOrder(); // 주문 완료 후 장바구니 비우기
    }
}