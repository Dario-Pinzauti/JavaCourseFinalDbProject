package FInalProjectJdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static Sql sql = null;
	static List<Plate> menu = null; 
	
	public static void main(String[] args) {
		System.out.println("Hello");
		sql = new Sql();
		menu = sql.getMenu();
		try (Scanner scanner = new Scanner(System.in)) {
		while(true) {
				System.out.println("1 - show menù");
				System.out.println("2 - insert order");
				System.out.println("3 - exit");
				int nextInt = scanner.nextInt();
				
				switch (nextInt) {
				case 1: {
					printMenu();
					break;
				}
				case 2: {
					insertOrder(scanner);
					break;
				}
				case 3: {
					return;
					
				}
				default:
					throw new IllegalArgumentException("Unexpected value: " + nextInt);
				}
				
				
			}
		}
			
					
		
	}

	// aggiungere errore
	private static void printMenu() {
		
		for (Plate plate : menu) {
			System.out.println(plate.getId() + " - " + plate.getDescription());
		}
	}
	
	private static Plate findPlateById(Integer id) {
		return menu.stream().filter(t -> t.getId() == id).findFirst().get();
	}

	private static void insertOrder(Scanner scanner) {
		ArrayList<Plate> order = new ArrayList<Plate>();
		while(true) {
			System.out.println("chose a plate");
			printMenu();
			int exit = menu.size() +1;
			System.out.println(exit + " - to close order");
			
			int nextInt = scanner.nextInt();
			if(exit ==  nextInt) {
				break;
			}
			order.add(findPlateById(nextInt));
			
		
		}
		
		// save data on db 
		sql.saveOrder(order);
		
		System.out.println(" the bill is "+ order.stream().map(t -> t.getPrice()).reduce(0, (a,b)-> a+b));
		
		
				
	}

}
