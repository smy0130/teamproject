	package app;
	
	import model.Product;
	import service.ProductManager;
	import service.Cart;
	import service.MenuManager;
	
	import java.util.Scanner;
	
	public class Main {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        ProductManager productManager = new ProductManager();
	        Cart cart = new Cart(); // Cart 객체 생성
	        MenuManager menuManager = new MenuManager(scanner, productManager, cart);
	
	        // 상품 정보 초기화
	        productManager.addProduct(new Product("티셔츠", 20000, 100));
	        productManager.addProduct(new Product("청바지", 35000, 100));
	        productManager.addProduct(new Product("맨투맨", 30000, 100));
	        productManager.addProduct(new Product("슬랙스", 25000, 100));
	        // ...
	
	        // 사용자 인터페이스 예시
	        while (true) {
	            menuManager.displayMainMenu();
	            int choice = scanner.nextInt();
	
	            if (choice == 7) {
	                System.out.println("구매해주셔서 감사합니다");
	                break;
	            }
	
	            menuManager.runMenu(choice); // 선택한 메뉴 실행
	        }
	
	        scanner.close();
	    }
	}
