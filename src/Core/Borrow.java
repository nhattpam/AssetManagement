/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 *
 * @author DELL
 */
public class Borrow {
    String bID;
    String assetID;
    String employeeID;
    int quantity;
    String borrowDateTime;

    public Borrow() {
    }

    public Borrow(String bID, String assetID, String employeeID, int quantity, String borrowDateTime) {
        this.bID = bID;
        this.assetID = assetID;
        this.employeeID = employeeID;
        this.quantity = quantity;
        this.borrowDateTime = borrowDateTime;
    }

    public String getbID() {
        return bID;
    }

    public void setbID(String bID) {
        this.bID = bID;
    }

    public String getAssetID() {
        return assetID;
    }

    public void setAssetID(String assetID) {
        this.assetID = assetID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBorrowDateTime() {
        return borrowDateTime;
    }

    public void setBorrowDateTime(String borrowDateTime) {
        this.borrowDateTime = borrowDateTime;
    }

    @Override
    public String toString() {
        return "bID: " + bID + ", assetID: " + assetID + ", employeeID: " + employeeID + ", quantity=" + quantity + ", borrowDateTime=" + borrowDateTime + '}';
    }
    
}
