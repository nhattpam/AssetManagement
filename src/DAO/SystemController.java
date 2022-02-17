
package DAO;

import Core.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhattpam
 */
public class SystemController implements Systems {

    List<Asset> assetInfo;
    List<Employee> employInfo;
    List<Request> requestInfo;
    List<Borrow> borrowInfo;
    DataDAO data = new DataDAO();
    Scanner sc = new Scanner(System.in);

    public SystemController() {
        try {
            assetInfo = data.assetLoadData("Asset.dat");
            employInfo = data.employLoadData("Employee.dat");
            requestInfo = data.requestLoadData("Request.dat");
            borrowInfo = data.borrowLoadData("Borrow.dat");
        } catch (IOException ex) {
            Logger.getLogger(SystemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void login() {
        String employeeID;
        while (true) {
            System.out.print("Enter employee ID: ");
            employeeID = sc.nextLine();
            if (Validation.checkEmployeeID(employeeID)) {
                break;
            }
        }
        System.out.print("Enter employee password:");
        String password = sc.nextLine();
        if (!checkEmployID(employeeID) || !checkPassword(password)) {
            System.out.println("Incorrect id or password");
        } else {
            System.out.println("Successfully");
        }
    }


    public boolean managerLogin() {
        System.out.print("Enter manager account: ");
        String managerAcc = sc.nextLine();
        System.out.print("Enter password:");
        String password = sc.nextLine();
        if (!managerAcc.equals("nhat") || !password.equals("0802")) {
            System.out.println("Incorrect account or password.\n Check again!!!");
            return false;
        } else {
            System.out.println("Successfully");
            return true;
        }
    }

    @Override
    public void searchAssetByName() {
        List<Asset> listName = new ArrayList<>();
        System.out.print("Enter name: ");
        String assetName = sc.nextLine();
        for (int i = 0; i < assetInfo.size(); i++) {
            if (assetInfo.get(i).getName().contains(assetName)) {
                listName.add(assetInfo.get(i));
            }
        }
        if (listName.isEmpty()) {
            System.out.println("Not Found!!");
            return;
        }
        Collections.sort(listName, (Asset o1, Asset o2) -> o1.getName().compareTo(o2.getName()) > 0 ? -1 : 1);
        for (int i = 0; i < listName.size(); i++) {
            System.out.println(listName.get(i));
        }
    }

    @Override
    public void createAsset() {
        while (true) {
            boolean checker = false;
            while (!checker) {
                checker = managerLogin();
            }
            Asset asset = inputAssetInfo();
            assetInfo.add(asset);
            data.SaveAssetToFile(assetInfo);
            System.out.print("Continue create asset(1.Yes or 2.No): ");
            int choose = Validation.inputInt();
            sc.nextLine();
            if (choose != 1) {
                break;
            }
        }
    }

    @Override
    public void updateAssetInfor() {
        boolean checker = false;
        while (!checker) {
            checker = managerLogin();
        }
        String assetID;
        while (true) {
            System.out.println("Enter Asset ID: ");
            assetID = sc.nextLine();
            if (Validation.checkAssetID(assetID)) {
                break;
            }
        }
        int i = searchAssetID(assetID);
        if (i == -1) {
            System.out.println("Asset does not exist");
        } else {
            System.out.println("Enter asset name: ");
            String name = sc.nextLine();
            if (!name.equals("")) {
                assetInfo.get(i).setName(name);
            }
            System.out.println("Enter color: ");
            String color = sc.nextLine();
            if (!color.equals("")) {
                assetInfo.get(i).setColor(color);
            }

            while (true) {
                try {
                    System.out.println("Enter price: ");
                    String price = sc.nextLine();
                    if (!price.equals("")) {
                        assetInfo.get(i).setPrice(Integer.parseInt(price));
                        break;
                    }
                    if (price.equals("")) {
                        break;
                    } else {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only Integer!!");
                    e.getMessage();
                }
            }
            while (true) {
                try {
                    System.out.println("Enter weight: ");
                    String weight = sc.nextLine();
                    if (!weight.equals("")) {
                        assetInfo.get(i).setWeight(Double.parseDouble(weight));
                        break;
                    }
                    if (weight.equals("")) {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only double!!!");
                    e.getMessage();
                }
            }
            while (true) {
                try {
                    System.out.println("Enter quantity: ");
                    String quantity = sc.nextLine();
                    if (!quantity.equals("")) {
                        assetInfo.get(i).setQuantity(Integer.parseInt(quantity));
                        break;
                    }
                    if (quantity.equals("")) {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Only Integer!!!");
                    e.getMessage();
                }
            }

            data.SaveAssetToFile(assetInfo);
            System.out.println(assetInfo.get(i));
        }
    }

    @Override
    public void approveRequest() {
        boolean checker = false;
        while (!checker) {
            checker = managerLogin();
        }
        displayRequest();
        String rID;
        while (true) {
            System.out.println("Select the requestID you want to approve: ");
            rID = sc.nextLine();
            if (Validation.checkRequestID(rID)) {
                break;
            }
        }
        int i = searchRequestID(rID);
        if (i == -1) {
            System.out.println("Not exist");
            return;
        }
        int position = searchAssetID(requestInfo.get(i).getAssetID());

        if (requestInfo.get(i).getQuantity() > assetInfo.get(position).getQuantity()) {
            System.out.println("Number of asset isn't enough to borrow.");
            return;
        }
        Date borrowTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String bID;
        while (true) {
            System.out.print("Enter new borrow ID (Start with B, follow by 3 digits): ");
            bID = sc.nextLine();

            if (Validation.checkBorrowID(bID)) {
                if (!checkBorrowDuplicate(bID, borrowInfo)) {
                    break;
                } else {
                    System.out.println("Duplicated bID!");
                }
            }

        }

        String assetID = requestInfo.get(i).getAssetID();
        int quantity = requestInfo.get(i).getQuantity();
        String borrowDateTime = formatter.format(borrowTime);
        Borrow borrow = new Borrow(bID, assetID, assetID, quantity, borrowDateTime);
        borrowInfo.add(borrow);
        requestInfo.remove(i);
        int assetQuantity = assetInfo.get(position).getQuantity();
        assetInfo.get(position).setQuantity(assetQuantity - quantity);
        data.SaveAssetToFile(assetInfo);
        data.SaveRequestToFile(requestInfo);
        data.SaveBorrowToFile(borrowInfo);
    }

    @Override
    public void showListBorrowAsset() {
        List<Asset> assetList = new ArrayList<>();
        for (int i = 0; i < borrowInfo.size(); i++) {
            String assetID = borrowInfo.get(i).getAssetID();
            boolean check = checkAssetDuplicate(assetID, assetList);
            if (check == false) {
                Asset asset = assetInfo.get(searchAssetID(assetID));
                assetList.add(asset);
            }
        }
        for (int i = 0; i < assetList.size(); i++) {
            System.out.println(assetInfo.get(i).getAssetID() + "    " + assetInfo.get(i).getName() + "    " + assetInfo.get(i).getColor() + "    " + assetInfo.get(i).getPrice() + "    " + assetInfo.get(i).getWeight() + "   " + assetInfo.get(i).getQuantity());
        }
    }

   
    boolean checkAssetDuplicate(String assetID, List<Asset> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAssetID().equals(assetID)) {
                return true;
            }
        }
        return false;
    }

    boolean checkBorrowDuplicate(String bID, List<Borrow> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getbID().equals(bID)) {
                return true;
            }
        }
        return false;
    }

    int searchAssetID(String assetID) {
        for (int i = 0; i < assetInfo.size(); i++) {
            if (assetInfo.get(i).getAssetID().equals(assetID)) {
                return i;
            }
        }
        return -1;
    }

    int searchRequestID(String requestID) {
        for (int i = 0; i < requestInfo.size(); i++) {
            if (requestInfo.get(i).getrID().equals(requestID)) {
                return i;
            }
        }
        return -1;
    }

    boolean checkEmployID(String employID) {
        for (int i = 0; i < employInfo.size(); i++) {
            if (employInfo.get(i).getEmployID().equals(employID)) {
                return true;
            }
        }
        return false;
    }

    boolean checkPassword(String password) {
        for (int i = 0; i < employInfo.size(); i++) {
            if (employInfo.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    void displayRequest() {
        System.out.println("rID  ||  assetID  ||  employeeID  ||  quantity  ||  requestDateTime");
        for (int i = 0; i < requestInfo.size(); i++) {
            System.out.println(requestInfo.get(i).getrID() + "     " + requestInfo.get(i).getAssetID() + "          " + requestInfo.get(i).getEmployeeID() + "           " + requestInfo.get(i).getQuantity() + "          " + requestInfo.get(i).getRequestDateTime());
        }
    }


    Asset inputAssetInfo() {
        String assetID;
        while (true) {
            System.out.println("Enter asset ID: ");
            assetID = sc.nextLine();
            if (searchAssetID(assetID) == -1) {
                break;
            } else {
                System.out.println("AssetID is duplicated!");
            }
        }
        System.out.println("Enter asset name: ");
        String name = sc.nextLine();
        System.out.println("Enter color: ");
        String color = sc.nextLine();
        System.out.println("Enter price: ");
        int price = sc.nextInt();
        System.out.println("Enter weight: ");
        double weight = sc.nextDouble();
        System.out.println("Enter quantity: ");
        int quantity = sc.nextInt();
        Asset asset = new Asset(assetID, name, color, price, weight, quantity);
        return asset;
    }
}
