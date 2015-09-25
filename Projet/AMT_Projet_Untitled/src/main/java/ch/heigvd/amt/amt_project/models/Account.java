/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amt_project.models;

/**
 *
 * @author thsch
 */
public class Account {
    /* Attributs */
    private int idUser;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Role[] fkRole;
    
    public Account(String email, String firstName, String lastName, String password, Role[] fkRole){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.fkRole = fkRole;
    
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fkRole
     */
    public Role[] getFkRole() {
        return fkRole;
    }

    /**
     * @param fkRole the fkRole to set
     */
    public void setFkRole(Role[] fkRole) {
        this.fkRole = fkRole;
    }
    
    
    
}
