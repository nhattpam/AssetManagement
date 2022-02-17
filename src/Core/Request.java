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
public class Request {
    String rID;
    String assetID;
    String employeeID;
    int quantity;
    String requestDateTime;

    public Request() {
    }

    public Request(String rID, String assetID, String employeeID, int quantity, String requestDateTime) {
        this.rID = rID;
        this.assetID = assetID;
        this.employeeID = employeeID;
        this.quantity = quantity;
        this.requestDateTime = requestDateTime;
    }

    public String getrID() {
        return rID;
    }

    public void setrID(String rID) {
        this.rID = rID;
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

    public String getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(String requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    @Override
    public String toString() {
        return "rID: " + rID + ", assetID: " + assetID + ", employeeID: " + employeeID + ", quantity: " + quantity + ", requestDateTime: " + requestDateTime;
    }
    
}
