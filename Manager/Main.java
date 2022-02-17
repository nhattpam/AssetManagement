
package Manager;

import DAO.Validation;
import DAO.SystemController;

public class Main {

    public static void main(String[] args) {
        SystemController systemController = new SystemController();
        System.out.println("Asset Manager");
        System.out.println("1.     Login\n"
                + "2.     Search asset by name\n"
                + "3.     Create new asset\n"
                + "4.     Update asset  information\n"
                + "5.     Approve the request of employee\n"
                + "6.     Show list of borrow asset\n"
                + "7.     Quit");
        boolean choice = true;
        while (choice) {
            System.out.print("Enter from 1 - 7: ");
            int k = Validation.inputInt();
            switch (k) {
                case 1:
                    systemController.login();
                    break;
                case 2:
                    systemController.searchAssetByName();
                    break;
                case 3:
                    systemController.createAsset();
                    break;
                case 4:
                    systemController.updateAssetInfor();
                    break;
                case 5:
                    systemController.approveRequest();
                    break;
                case 6:
                    systemController.showListBorrowAsset();
                    break;
                case 7:
                    System.out.println("Goodbye!");
                    choice = false;
                    break;
            }
        }
    }

}
