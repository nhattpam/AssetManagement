
package DAO;

import java.util.Scanner;

/**
 *
 * @author nhattpam
 */
public class Validation {

    static Scanner sc = new Scanner(System.in);

    public static int inputInt(){
        int num;
        while (true) {
            try {
                num = sc.nextInt();
                
                
                return num;
            } catch (Exception e) {
                System.out.print("Only Integer!!.Input again: ");
                sc.nextLine();
            }
        }
    }

    public static boolean checkAssetID(String assetID) {
        String reg = "A\\d{3}";
        if (assetID.matches(reg)) {
            return true;
        } else {
            System.out.println("Asset ID must be start with A and follow by 3 digits.");
            System.out.println("Please Enter again:");
            return false;
        }
    }

    public static boolean checkEmployeeID(String employID) {
        String reg = "E\\d{6}";
        if (employID.matches(reg)) {
            return true;
        } else {
            System.out.println("Employee ID starts with E and follow by 6 digits.");
            System.out.println("Please enter again.");
            return false;
        }
    }

    public static boolean checkRequestID(String requestID) {
        String reg = "R\\d{3}";
        if (requestID.matches(reg)) {
            return true;
        } else {
            System.out.println("requestID starts with R and follow by 3 digits.");
            System.out.println("Please enter again.");
            return false;
        }
    }

    public static boolean checkBorrowID(String borrowID) {
        String reg = "B\\d{3}";
        if (borrowID.matches(reg)) {
            return true;
        } else {
            System.out.println("borrowID starts with B and follow by 3 digits.");
            System.out.println("Please enter again.");
            return false;
        }
    }

}
