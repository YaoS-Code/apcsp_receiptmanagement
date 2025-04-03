import java.util.Scanner;


public class Management {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to Management System");
        System.out.println("----------------------------");
        System.out.println("1. Record a Receipt");
        System.out.println("2. Search a Receipt");
        System.out.println("3. List Receipts");
        System.out.println("4. Other");
        System.out.println("5. Exit");
        System.out.println("----------------------------");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Receipt Recorded!");
                break;
            case 2:
                System.out.print("Receipt Searched!");
                break;
                case 3:
                    System.out.print("Receipt Listed!");
                    break;
                case 4:
                    System.out.print("Receipt Other!");
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    menu();
        }

    }
}
