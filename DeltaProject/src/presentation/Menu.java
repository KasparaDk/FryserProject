package presentation;

import java.util.Scanner;



public class Menu {



	public void start() {
		
		try (Scanner sc = new Scanner(System.in)) {
			showMenu();
			String menuChoice = sc.nextLine();
			while (!menuChoice.equals("x")) {
				processMenuChoice(menuChoice, sc);
				showMenu();
				menuChoice = sc.nextLine();
			}
		}
	}
	//TEST
	private void showMenu() {
		System.out.println("Produkt test show Menu");
		System.out.println("1 random");
		System.out.println("2 Tilføj test");
		System.out.println("3 Alle liste");
		System.out.println("4 Updatere");
		System.out.println("5 Slet");
		System.out.println("6 Tjek dato");
		System.out.println("7 Get date");
		System.out.println("8 Days between");
	}

	private void processMenuChoice(String menuChoice, Scanner sc) {
		switch (menuChoice) {
		case "1":
//			System.out.println("  1:  Tilføj et Produkt");
//			CreateProduct createProduct = new CreateProduct();
//			createProduct.start(sc);

			break;
		case "2":
			System.out.println("Test add");
			TestProduct testProduct = new TestProduct();
			testProduct.add();
			break;
		case "3":
			System.out.println("Test Se alle liste");
			TestProduct testProduct2 = new TestProduct();
			testProduct2.getAll();
			break;
		case "4":
			System.out.println("Test Update");
			TestProduct testProduct3 = new TestProduct();
			testProduct3.update();
			break;
		case "5":
			System.out.println("Slet");
			TestProduct testProduct4 = new TestProduct();
			testProduct4.slet();
			break;
		case "6":
			System.out.println("tjek dato");
			TestProduct testProduct5 = new TestProduct();
			testProduct5.tjek();
			break;
		case "7":
			System.out.println("get date");
			TestProduct testProduct6 = new TestProduct();
			testProduct6.getDate();
			break;
		case "8":
			System.out.println("Days between");
			TestProduct testProduct7 = new TestProduct();
			testProduct7.daysBetween();
			break;
		case "x":
			System.out.println("  x: Afslut");

			break;
		default:
			showMenu();
			break;
		}

	}
}
