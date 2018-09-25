package prs.utility;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * Prompts the user and verifies the input is an integer
	 * @param prompt - String value to display to the user
	 * @return the integer input by the user
	 */
	public static int getInt(String prompt) {
		int i = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                isValid = true;
            } else {
                System.out.println("\nError! Invalid integer value. Try again.\n");
            }
            sc.nextLine(); //discards
        }
        return i;
	}
	
	/**
	 * Prompts the user and verifies the input between the range
	 * also verifies is integer
	 * @param prompt - String value to display to the user
	 * @return the integer input by the user
	 */
	public static int getInt(String prompt, int min, int max) {
		int i = 0;
        boolean isValid = false;
        while (!isValid) {
            i = getInt(prompt);
            if (i <= min) {
                System.out.println(
                        "\nError! Number must be greater than " + min + ".\n");
            } else if (i >= max) {
                System.out.println(
                        "\nError! Number must be less than " + max + ".\n");
            } else {
                isValid = true;
            }
        }
        return i;
	}
	
	/**
	 * Prompts the user and verifies the input is a double
	 * @param prompt - String value to display to the user
	 * @return the double input by the user
	 */
	public static double getDouble(String prompt) {
		double d = 0.0;
        boolean isValid = false;
        while (!isValid) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                d = sc.nextDouble();
                isValid = true;
            } else {
                System.out.println("\nError! Invalid decimal value. Try again.\n");
            }
            sc.nextLine();  // discard any other data entered on the line
        }
        return d;
	}
	
	/**
	 * Prompts the user and verifies the input between the range
	 * also verifies is double
	 * @param prompt - String value to display to the user
	 * @return the double input by the user
	 */
	public static double getDouble(String prompt, double min, double max) {
		double d = 0.0;
        boolean isValid = false;
        while (!isValid) {
            d = getDouble(prompt);
            if (d <= min) {
                System.out.println(
                        "\nError! Number must be greater than " + min + ".\n");
            } else if (d >= max) {
                System.out.println(
                        "\nError! Number must be less than " + max + ".\n");
            } else {
                isValid = true;
            }
        }
        return d;
	}
	
	/**
	 * Prompts the user and verifies that a string is entered
	 * @param prompt - String value to display to the user
	 * @return the string input by the user
	 */
	public static String getString(String prompt) {
		String text = "";
		Boolean isValid = false;
		while (!isValid) {
			System.out.print(prompt);
			text = sc.nextLine();
			if (!text.equals("")) {
				isValid = true;
			} else {
				System.out.println("\nError! Must enter a value. Try again.\n");
			}
		}
		return text;
	}
	
	/**
	 * Prompts the user and verifies that a string is entered
	 * @param prompt - String value to display to the user
	 * @return the string input by the user
	 */
	public static String getString(String prompt, String regex) {
		String s = "";
    	Boolean isValid = false;
		while (!isValid) {
			s = getString(prompt);
			if (!s.matches(regex)) {
				System.out.println("\nError! Entry must be MM/DD/YYYY Try again.\n");
			} else {
				isValid = true;
				System.out.println();
			}
		}
		return s;
	}
	
	/**
	 * Prompts the user and verifies that a string is entered
	 * and matches the specified strings
	 * @param prompt - String value to display to the user
	 * @return the string input by the user
	 */
	public static String getString(String prompt, String s1, String s2) {
		String s = "";
    	Boolean isValid = false;
		while (!isValid) {
			s = getString(prompt);
			if (!s.equalsIgnoreCase(s1) && !s.equalsIgnoreCase(s2)) {
				System.out.println("\nError! Entry must be '" + s1 + "' or '" + s2 + "'. Try again.\n");
			} else {
				isValid = true;
				System.out.println();
			}
		}
		return s;
	}
	
	/**
	 * Prompts the user and verifies that a string is entered
	 * and matches the specified strings
	 * @param prompt - String value to display to the user
	 * @return the string input by the user
	 */
	public static String getString(String prompt, String s1, String s2, String s3) {
		String s = "";
    	Boolean isValid = false;
		while (!isValid) {
			s = getString(prompt);
			if (!s.equalsIgnoreCase(s1) && !s.equalsIgnoreCase(s2) && !s.equalsIgnoreCase(s3)) {
				System.out.println("\nError! Entry must be '" + s1 + "' or '" + s2 + "'. Try again.\n");
			} else {
				isValid = true;
				System.out.println();
			}
		}
		return s;
	}
	
	/**
	 * Prompts the user and verifies that a string is entered
	 * and matches one of the specified strings
	 * @param prompt - String value to display to the user
	 * @return the string input by the user
	 */
	public static String getString(String prompt, ArrayList<String> s1) {
		String s = "";
    	Boolean isValid = false;
		while (!isValid) {
			s = getString(prompt);
			int counter = 0;
			for (String item : s1) {
				if(s.equalsIgnoreCase(item)) {
					counter++;
				}
			}
			if (counter == 0) {
				System.out.println("\nError! Entry must be on the command list. Try again.\n");
			} else {
				isValid = true;
				System.out.println();
			}
		}
		return s;
	}

	/**
	 * Prints string
	 * @param s
	 */
	public static void print(String s) {
		System.out.print(s);		
	}

	/**
	 * Prints a newline
	 */
	public static void println() {
		System.out.println();
	}

	/**
	 * Prints a string and newline
	 * @param s
	 */
	public static void println(String s) {
		System.out.println(s);
	}
}