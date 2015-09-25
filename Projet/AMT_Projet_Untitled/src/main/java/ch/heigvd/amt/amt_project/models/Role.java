/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amt_project.models;

/**
 *
 * @author YounTheory
 */
public class Role {
    private int idRole;
    private String name;
    private Account[] fkAccount;
    
    public Role(String name, Account[] fkAccount)
    {
        this.name = name;
        this.fkAccount = fkAccount;
    }
    /**
     * @return the idRole
     */
    public int getIdRole() {
        return idRole;
    }

    /**
     * @param idRole the idRole to set
     */
    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the fkAccount
     */
    public Account[] getFkAccount() {
        return fkAccount;
    }

    /**
     * @param fkAccount the fkAccount to set
     */
    public void setFkAccount(Account[] fkAccount) {
        this.fkAccount = fkAccount;
    }
    
}
