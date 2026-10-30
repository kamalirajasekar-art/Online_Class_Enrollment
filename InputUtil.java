package util;

import java.util.Scanner;

public final class InputUtil {
    private InputUtil() {}

    public static String readRequired(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    public static int readInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(value);
                if (number < min || number > max) {
                    System.out.println("Enter a number between " + min + " and " + max + ".");
                    continue;
                }
                return number;
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number. Please enter a whole number.");
            }
        }
    }
}
