/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amt_project.models;

import java.util.Date;

/**
 *
 * @author YounTheory
 */
public class ListApplication {
    private String idApp;
    private int idUser;
    private Date date;

    public ListApplication(String idApp, int idUser, Date date)
    {
        this.idApp = idApp;
        this.idUser = idUser;
        this.date = date;
    }
    
    /**
     * @return the idApp
     */
    public String getFkApp() {
        return idApp;
    }

    /**
     * @param idApp the idApp to set
     */
    public void setFkApp(String idApp) {
        this.idApp = idApp;
    }

    /**
     * @return the idUser
     */
    public int getFkUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setFkUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
