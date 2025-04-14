import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your credit score: ");
        int creditScore = scanner.nextInt();

        System.out.print("Enter your annual income: ");
        double income = scanner.nextDouble();

        if (creditScore > 700 && income >= 50000) {
            System.out.println("You are eligible for the loan.");
        } else {
            System.out.println("You are not eligible for the loan.");
        }
        scanner.close();
    }
}
