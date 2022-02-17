
package DAO;

import Core.Employee;
import Core.Borrow;
import Core.Asset;
import Core.Request;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhattpam
 */
public class DataDAO extends Data{

    @Override
    public List<Asset> assetLoadData(String str) throws IOException {
        List<Asset> assetList = new ArrayList();
        File inputFile = new File(str);
        try {
            try (FileReader fr = new FileReader(inputFile);
                    BufferedReader bf = new BufferedReader(fr)) {
                String[] s;
                String assetID, name, color;
                int price;
                double weight;
                int quantity;
                String content;
                while (true) {
                    content = bf.readLine();
                    if (content == null || content.trim().length() < 3) {
                        break;
                    }
                    s = content.split("[|]");
                    assetID = s[0].trim();
                    name = s[1].trim();
                    color = s[2].trim();
                    price = Integer.parseInt(s[3].trim());
                    weight = Double.parseDouble(s[4].trim());
                    quantity = Integer.parseInt(s[5].trim());
                    Asset asset = new Asset(assetID, name, color, price, weight, quantity);
                    assetList.add(asset);

                }
                bf.close();
                fr.close();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return assetList;
    }

    @Override
    public List<Employee> employLoadData(String str) throws IOException {
        List<Employee> employList = new ArrayList();
        File inputFile = new File(str);
        try {
            try (FileReader fr = new FileReader(inputFile);
                    BufferedReader bf = new BufferedReader(fr)) {
                String[] s;
                String employID, name, birthdate,
                        role, sex, password;
                String content;
                while (true) {
                    content = bf.readLine();
                    if (content == null || content.trim().length() < 3) {
                        break;
                    }
                    s = content.split("[|]");
                    employID = s[0].trim();
                    name = s[1].trim();
                    birthdate = s[2].trim();
                    role = s[3].trim();
                    sex = s[4].trim();
                    password = s[5].trim();
                    Employee emp = new Employee(employID, name, birthdate, role, sex, password);
                    employList.add(emp);
                }
                bf.close();
                fr.close();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return employList;
    }

    @Override
    public List<Borrow> borrowLoadData(String str) throws IOException {
        List<Borrow> borrowList = new ArrayList();
        File inputFile = new File(str);
        try {
            try (FileReader fr = new FileReader(inputFile);
                    BufferedReader bf = new BufferedReader(fr)) {
                String[] s;
                String bID, assetID, employeeID,
                        borrowDateTime;
                int quatity;
                String content;
                while (true) {
                    content = bf.readLine();
                    if (content == null || content.trim().length() < 3) {
                        break;
                    }
                    s = content.split("[|]");
                    bID = s[0].trim();
                    assetID = s[1].trim();
                    employeeID = s[2].trim();
                    quatity = Integer.parseInt(s[3].trim());
                    borrowDateTime = s[4].trim();
                    Borrow borrow = new Borrow(bID, assetID, employeeID, quatity, borrowDateTime);
                    borrowList.add(borrow);
                }
                bf.close();
                fr.close();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return borrowList;
    }

    @Override
    public List<Request> requestLoadData(String str) throws IOException {
        List<Request> requestList = new ArrayList();
        File inputFile = new File(str);
        try {
            try (FileReader fr = new FileReader(inputFile);
                    BufferedReader bf = new BufferedReader(fr)) {
                String[] s;
                String rID, assetID, employeeID,
                        requestDateTime;
                int quatity;
                String content;
                while (true) {
                    content = bf.readLine();
                    if (content == null || content.trim().length() < 3) {
                        break;
                    }
                    s = content.split("[|]");
                    rID = s[0].trim();
                    assetID = s[1].trim();
                    employeeID = s[2].trim();
                    quatity = Integer.parseInt(s[3].trim());
                    requestDateTime = s[4].trim();
                    Request request = new Request(rID, assetID, employeeID, quatity, requestDateTime);
                    requestList.add(request);
                }
                bf.close();
                fr.close();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return requestList;
    }

    public void SaveAssetToFile(List<Asset> list) {
        try {
            File fileName = new File("Asset.dat");
            File tmp_file = fileName;
            String tmp = "";
            try (FileWriter fw = new FileWriter(tmp_file); BufferedWriter bw = new BufferedWriter(fw)) {
                
                for (int i = 0; i < list.size(); i++) {
                    tmp += list.get(i).getAssetID()+ " | " + list.get(i).getName()+ " | "
                            +  list.get(i).getColor()+ " | " + list.get(i).getPrice()+ " | " + list.get(i).getWeight()+ " | " + list.get(i).getQuantity()+"\n";
                }
                
                bw.write(tmp);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void SaveRequestToFile(List<Request> list) {
        try {
            File fileName = new File("Request.dat");
            File tmp_file = fileName;
            String tmp = "";
            try (FileWriter fw = new FileWriter(tmp_file); BufferedWriter bw = new BufferedWriter(fw)) {
                
                for (int i = 0; i < list.size(); i++) {
                    tmp += list.get(i).getrID()+ " | " + list.get(i).getAssetID()+ " | "
                            +  list.get(i).getEmployeeID()+ " | " + list.get(i).getQuantity()+ " | " + list.get(i).getRequestDateTime()+"\n";
                }
                
                bw.write(tmp);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void SaveBorrowToFile(List<Borrow> list) {
        try {
            File fileName = new File("Borrow.dat");
            File tmp_file = fileName;
            String tmp = "";
            try (FileWriter fw = new FileWriter(tmp_file); BufferedWriter bw = new BufferedWriter(fw)) {
                
                for (int i = 0; i < list.size(); i++) {
                    tmp += list.get(i).getbID()+ " | " + list.get(i).getAssetID()+ " | "
                            +  list.get(i).getEmployeeID()+ " | " + list.get(i).getQuantity()+ " | " + list.get(i).getBorrowDateTime()+"\n";
                }
                
                bw.write(tmp);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
