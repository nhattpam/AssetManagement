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
public class Employee {
    String employID;
    String name;
    String birthdate;
    String role;
    String sex;
    String password;

    public Employee() {
    }

    public Employee(String employID, String name, String birthdate, String role, String sex, String password) {
        this.employID = employID;
        this.name = name;
        this.birthdate = birthdate;
        this.role = role;
        this.sex = sex;
        this.password = password;
    }

    public String getEmployID() {
        return employID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "employID: " + employID + ", name: " + name + ", birthdate: " + birthdate + ", role: " + role + ", sex: " + sex + ", password: " + password;
    }
    
}
